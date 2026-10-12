package com.samuel.tennis_club_api;

public class ReservationConflictException extends RuntimeException {
    public ReservationConflictException(String message){
        super(message);
    }
}
