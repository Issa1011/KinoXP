package com.kinoxp.service;

import com.kinoxp.dto.PriceRequest;
import com.kinoxp.dto.ReservationDTO;
import com.kinoxp.model.movie.Movie;
import com.kinoxp.model.reservation.Reservation;
import com.kinoxp.model.reservation.Status;
import com.kinoxp.model.showing.Showing;
import com.kinoxp.model.user.User;
import com.kinoxp.repository.ReservationRepository;
import com.kinoxp.repository.ShowingRepository;
import com.kinoxp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final ShowingRepository showingRepository;


    public ReservationService(ReservationRepository reservationRepository,
                              UserRepository userRepository,
                              ShowingRepository showingRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.showingRepository = showingRepository;
    }


    //  Hent alle reservationer
    public List<ReservationDTO> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream()
                .map(this::convertToDTO)
                .toList();
    }

    // Hent en reservation by id
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    //  Slet reservation by id
    public void deleteReservation(Long reservationId) {
        if (!reservationRepository.existsById(reservationId)) {
            throw new RuntimeException("Reservation not found");
        }
        reservationRepository.deleteById(reservationId);
    }

    //  Opret reservation
    public ReservationDTO createReservation(ReservationDTO reservationDTO) {
        User user = userRepository.findById(reservationDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Showing showing = showingRepository.findById(reservationDTO.getShowingId())
                .orElseThrow(() -> new RuntimeException("Showing not found"));

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setShowing(showing);
        reservation.setRowNumber(reservationDTO.getRowNumber());
        reservation.setTotalPrice(reservationDTO.getTotalPrice());
        reservation.setCreated(LocalDateTime.now());
        reservation.setStatus(Status.CONFIRMED);

        Reservation savedReservation = reservationRepository.save(reservation);
        return convertToDTO(savedReservation);
    }

    // DTO-konvertering
    public ReservationDTO convertToDTO(Reservation reservation) {
        ReservationDTO dto = new ReservationDTO();
        dto.setUserId(reservation.getUser().getUserID());
        dto.setShowingId(reservation.getShowing().getShowingId());
        dto.setRowNumber(reservation.getRowNumber());
        dto.setTotalPrice(reservation.getTotalPrice());
        dto.setMovieTitle(reservation.getShowing().getMovie().getTitle());
        dto.setNumberOfTickets(reservation.getTickets().size());
        return dto;
    }

    // US 3.2, 3.6, 3.7: Beregn totalpris inkl. standardpris, langfilm, rowFee og rabat
    private static final double STANDARD_PRICE = 130.0;
    private static final double LONG_FILM_FEE = 20.0;
    private static final double ROW_FEE = 25.0;
    private static final double RABAT_HVIS_MERE_END_10 = 0.07;

    // Beregn pris ud fra Movie, antal billetter og række
    public double calculateTotalPrice(Movie movie, int numberOfTickets, int rowNumber) {
        double pricePerTicket = STANDARD_PRICE;

        // Langfilm-gebyr (US 3.6)
        if (movie.getDurationInMinutes() > 170) {
            pricePerTicket += LONG_FILM_FEE;
        }

        // Row fee for premium rækker (US 3.7)
        if (rowNumber > 7) {
            pricePerTicket += ROW_FEE;
        }

        // Totalpris uden rabat
        double totalPrice = pricePerTicket * numberOfTickets;

        // Mængderabat (US 3.2)
        if (numberOfTickets > 10) {
            totalPrice *= (1 - RABAT_HVIS_MERE_END_10);
        }

        return totalPrice;
    }

    //  beregn pris direkte fra DTO
//    public double calculatePriceFromDTO(ReservationDTO reservationDTO) {
//        Showing showing = showingRepository.findById(reservationDTO.getShowingId())
//                .orElseThrow(() -> new RuntimeException("Showing not found"));
//        Movie movie = showing.getMovie();
//
//        return calculateTotalPrice(movie, reservationDTO.getNumberOfTickets(), reservationDTO.getRowNumber());
//    }

    public double calculatePriceFromRequest(PriceRequest request) {
        // Hent Showing og Movie
        Showing showing = showingRepository.findById(request.getShowingId())
                .orElseThrow(() -> new RuntimeException("Showing not found"));

        Movie movie = showing.getMovie();

        // Beregn totalpris inkl. langfilmgebyr, rækkegebyr og rabat
        return calculateTotalPrice(
                movie,
                request.getNumberOfTickets(),
                request.getRowNumber()
        );
    }
}