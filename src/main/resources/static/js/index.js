const OMDB_API_KEY = '8e71e427';

const showingsCache = {};

function hideAll() {
    document.getElementById("createMovieForm").style.display = "none";
    document.getElementById("movies-container").style.display = "none";
    document.getElementById("active-filter").style.display = "none";
    document.getElementById("showingsAdminPanel").style.display = "none";
}

function showCreateMovie() {
    hideAll();
    document.getElementById("createMovieForm").style.display = "block";
}

function showAllMovies() {
    hideAll();
    document.getElementById("movies-container").style.display = "block";
    loadMovies();
}

function showShowingsAdmin() {
    hideAll();
    document.getElementById("showingsAdminPanel").style.display = "block";
}

function bookMovie(movieId) {
    const user = localStorage.getItem('user');
    if (!user) {
        alert('Du skal logge ind!');
        window.location.href = 'login.html';
        return;
    }
    window.location.href = `booking.html?movieId=${movieId}`;
}

function checkUserLogin() {
    const user = localStorage.getItem('user');
    if (!user) return;

    const userData = JSON.parse(user);

    document.getElementById('loginLink').style.display = 'none';
    document.getElementById('registerLink').style.display = 'none';
    document.getElementById('logoutLink').style.display = 'inline';
    document.getElementById('userName').style.display = 'inline';
    document.getElementById('userName').textContent =
        `Logget ind som: ${userData.name}`;

    if (userData.role === "ADMIN") {
        document.getElementById("adminPanel").style.display = "block";
    }

    document.getElementById('logoutLink').addEventListener('click', (e) => {
        e.preventDefault();
        localStorage.removeItem('user');
        location.reload();
    });
}

async function loadMovies() {
    const response = await fetch('/kino/movies');
    const movies = await response.json();

    const container = document.getElementById('movies-container');
    container.innerHTML = '';

    movies.forEach(movie => {
        const card = document.createElement('div');
        card.className = 'movie-card';

        card.innerHTML = `
            <h2>${movie.title}</h2>
            <p>${movie.releaseYear}</p>
            <button class="btn" onclick="bookMovie(${movie.movieId})">
                Bestil nu
            </button>
        `;

        container.appendChild(card);
    });
}

checkUserLogin();
loadMovies();