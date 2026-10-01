package com.samuel.tennis_club_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;;

@SpringBootApplication
@EntityScan("com.samuel.tennis_club_api")
public class TennisClubApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(TennisClubApiApplication.class, args);
	}
}
