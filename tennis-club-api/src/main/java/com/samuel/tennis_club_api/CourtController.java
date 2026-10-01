package com.samuel.tennis_club_api;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Optional;
import java.util.List;

import org.springframework.http.ResponseEntity;

@RestController
public class CourtController {
    private final CourtRepository courtRepository;

    public HelloController(CourtRepository courtRepository) {
        this.courtRepository = courtRepository;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, Samuel!";
    }

    @GetMapping("/courts/{id}") 
    public ResponseEntity<Court> getCourt(@PathVariable Integer id) {
        Optional<Court> court = courtRepository.findById(id);
        if (court.isEmpty()) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(court.get());
    }

    @GetMapping("/courts")
    public List<Court> getCourts() {
        return courtRepository.findAll();
    }

    @PostMapping("/courts")
    public ResponseEntity<Court> createCourt(@RequestBody Court body) {
        return ResponseEntity.ok(courtRepository.save(body));
    }

    @DeleteMapping("/courts/{id}")
    public ResponseEntity<Void> deleteCourt(@PathVariable Integer id) {
        Optional<Court> court = courtRepository.findById(id);
        if (court.isEmpty()) return ResponseEntity.notFound().build();
        else {
            courtRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/courts/{id}")
    public ResponseEntity<Court> putCourt(@PathVariable Integer id, @RequestBody Court body) {
        Optional<Court> court = courtRepository.findById(id);
        if (court.isEmpty()) return ResponseEntity.notFound().build();
        else {
            court.get().setName(body.getName());
            court.get().setType(body.getType());
            courtRepository.save(court.get());
            return ResponseEntity.ok(court.get());
        }
    }
}