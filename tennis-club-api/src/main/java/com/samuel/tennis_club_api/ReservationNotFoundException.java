package com.samuel.tennis_club_api;

public class ReservationNotFoundException extends RuntimeException {
    public ReservationNotFoundException(String message){
        super(message);
    }
}
