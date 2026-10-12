package com.samuel.tennis_club_api;

import java.time.LocalTime;
import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    boolean existsByCourtAndDateAndStartTime(Court court, LocalDate date, LocalTime startTime);

    boolean existsByUserAndDateAndStartTime(User user, LocalDate date, LocalTime startTime);
}
