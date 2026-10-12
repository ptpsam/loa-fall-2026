package com.samuel.tennis_club_api;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservation(Integer id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if (reservation.isEmpty()) throw new ReservationNotFoundException("The desired reservation has not been found.");
        else return reservation.get();
    }

    public Reservation createReservation(Reservation body) {
        if (reservationRepository.existsByCourtAndDateAndStartTime(body.getCourt(), body.getDate(), body.getStartTime())) throw new ReservationConflictException("This court is already reserved at this time.");
        if (reservationRepository.existsByUserAndDateAndStartTime(body.getUser(), body.getDate(), body.getStartTime())) throw new ReservationConflictException("You already have a reservation at this time.");
        return reservationRepository.save(body);
    }

    public Reservation updateReservation(Integer id, Reservation body) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if (reservation.isEmpty()) throw new ReservationNotFoundException("The desired reservation has not been found.");
        else {
            Reservation existingReservation = reservation.get();
            existingReservation.setUser(body.getUser());
            existingReservation.setCourt(body.getCourt());
            existingReservation.setDate(body.getDate());
            existingReservation.setStartTime(body.getStartTime());
            reservationRepository.save(existingReservation);
            return existingReservation;
        }
    }

    public void deleteReservation(Integer id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if (reservation.isEmpty()) throw new ReservationNotFoundException("The desired reservation has not been found.");
        reservationRepository.deleteById(id);
    } 
}
