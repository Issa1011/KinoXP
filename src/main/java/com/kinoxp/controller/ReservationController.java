package com.kinoxp.controller;

import com.kinoxp.dto.ReservationDTO;
import com.kinoxp.model.reservation.Reservation;
import com.kinoxp.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<ReservationDTO>> getAllReservations() {
        List<ReservationDTO> reservations = reservationService.getAllReservations();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<ReservationDTO> getReservation(@PathVariable int id) {
//        Reservation reservation = reservationService.getReservationById(id);
//
//        if (reservation != null) {
//            return ResponseEntity.notFound().build();
//        }
//
//
//    }



//    @GetMapping()
//    public List<Reservation> getAllReservations() {
//        return reservationService.getAllReservation();
//    }

    //TODO lav metoden for når opretter en reservation.
    @PostMapping("/create")
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO reservationDTO){
    ReservationDTO savedReservation = reservationService.createReservation(reservationDTO);
    return new ResponseEntity<>(savedReservation, HttpStatus.CREATED);
    }


    // TODO: lav metoden der beregner prisen.
//    @PostMapping("/price")
//    public double CalculatePrice(@RequestBody PriceRequest request){
//
//    }


//    //TODO: Hent reservation via id
//    @GetMapping("{id]")
//    public Reservation getReservationById(@PathVariable int id) {
//
//    }


    // TODO: Hent reservation
//    @GetMapping("{id}")

    //TODO: Update
//    @PutMapping

    // TODO: Slet reservation
//    @DeleteMapping



}
