const urlParams = new URLSearchParams(window.location.search);
const movieId = urlParams.get('movieId');
let showingId = urlParams.get('showingId');

if (!movieId) {
    window.location.href = 'index.html';
}

let selectedSeats = [];
let theaterId = null;
let movieData = null;
let currentShowing = null;

function formatDateTime(dateTimeStr) {
    const date = new Date(dateTimeStr);
    return date.toLocaleDateString('da-DK', {
            weekday: 'long',
            day: 'numeric',
            month: 'long'
        }) + ' kl. ' +
        date.toLocaleTimeString('da-DK', {
            hour: '2-digit',
            minute: '2-digit'
        });
}

async function loadBookingData() {
    try {
        const movieResp = await fetch(`/kino/movies/${movieId}`);
        movieData = await movieResp.json();

        document.getElementById('booking-title').textContent =
            `Reserver billetter: ${movieData.title}`;

        const showingsResp = await fetch(`/kino/showings/movie/${movieId}`);
        const showings = await showingsResp.json();

        if (!showingId) {
            renderShowingSelection(showings);
            return;
        }

        currentShowing = showings.find(s => s.showingId == showingId);

        if (!currentShowing) {
            renderShowingSelection(showings);
            return;
        }

        await initializeBooking(currentShowing);

    } catch (error) {
        console.error(error);
    }
}

function renderShowingSelection(showings) {
    document.getElementById('showing-selection').style.display = 'block';
    document.getElementById('booking-content').style.display = 'none';

    const list = document.getElementById('showings-list');
    list.innerHTML = '';

    showings.forEach(s => {
        const btn = document.createElement('button');
        btn.className = 'btn';
        btn.textContent = formatDateTime(s.startTime);

        btn.onclick = () => {
            showingId = s.showingId;
            initializeBooking(s);
        };

        list.appendChild(btn);
    });
}

async function initializeBooking(showing) {
    currentShowing = showing;
    theaterId = showing.theaterId;

    document.getElementById('booking-content').style.display = 'block';

    const seatsResp = await fetch(`/kino/seats/theater/${theaterId}`);
    const allSeats = await seatsResp.json();

    const reservedResp = await fetch(
        `/kino/reservations/showing/${showing.showingId}/reserved-seats`
    );
    const reservedSeatIds = await reservedResp.json();

    renderSeatGrid(allSeats, reservedSeatIds);
}

function renderSeatGrid(allSeats, reservedSeatIds) {
    const grid = document.getElementById('seat-grid');
    grid.innerHTML = '';

    allSeats.forEach(seat => {
        const seatDiv = document.createElement('div');
        seatDiv.className = 'seat';

        if (!reservedSeatIds.includes(seat.seatId)) {
            seatDiv.addEventListener('click', () =>
                toggleSeat(seat, seatDiv)
            );
        }

        grid.appendChild(seatDiv);
    });
}

function toggleSeat(seat, element) {
    const index = selectedSeats.findIndex(s =>
        s.seatId === seat.seatId
    );

    if (index > -1) {
        selectedSeats.splice(index, 1);
        element.classList.remove('selected');
    } else {
        selectedSeats.push(seat);
        element.classList.add('selected');
    }
}

loadBookingData();