package com.samuel.tennis_club_api;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "court_id")
    private Court court;
    private LocalDate date;
    @Column(name = "start_time")
    private LocalTime startTime;

    public Reservation() {
    }

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Court getCourt() {
        return court;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCourt(Court court) {
        this.court = court;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setStartTime(LocalTime time) {
        this.startTime = time;
    }
}
