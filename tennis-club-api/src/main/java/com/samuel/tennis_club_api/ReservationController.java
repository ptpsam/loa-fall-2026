package com.samuel.tennis_club_api;

import org.springframework.web.bind.annotation.RestController;

import com.samuel.tennis_club_api.Reservation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;


import java.util.Optional;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
public class ReservationController {
    private final ReservationRepository reservationRepository;
    private final ReservationService reservationService;

    public ReservationController(ReservationRepository reservationRepository, ReservationService reservationService) {
        this.reservationRepository = reservationRepository;
        this.reservationService = reservationService;
    }

    @GetMapping("/reservations")
    public List<Reservation> getReservations() {
        return reservationService.getReservations();
    }

    @GetMapping("/reservations/{id}")
    public ResponseEntity<Reservation> getReservation(@PathVariable Integer id) {
        Reservation reservation = reservationService.getReservation(id);
        return ResponseEntity.ok(reservation);
    }

    @PostMapping("/reservations")
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation body) {
        Reservation reservation = reservationService.createReservation(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservation);
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Integer id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/reservations/{id}")
    public ResponseEntity<Reservation> putReservation(@PathVariable Integer id, @RequestBody Reservation body) {
        Reservation reservation = reservationService.updateReservation(id, body);
        return ResponseEntity.ok(reservation);
    }
}
