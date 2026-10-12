package com.samuel.tennis_club_api;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.samuel.tennis_club_api.ReservationNotFoundException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;

import org.springframework.validation.FieldError;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ReservationConflictException.class)
    public ResponseEntity<Void> handleReservationConflict(ReservationConflictException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @ExceptionHandler(ReservationNotFoundException.class)
    public ResponseEntity<Void> handleReservationNotFound(ReservationNotFoundException e){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Void> handleUserNotFound(UserNotFoundException e){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationError>> handleMethodArgumentNotValid(MethodArgumentNotValidException e){
        List<FieldError> errorList = e.getBindingResult().getFieldErrors();
        List<ValidationError> response = new ArrayList<ValidationError>();
        for (int i=0; i<errorList.size(); i++){
            FieldError error = errorList.get(i);
            ValidationError currentError = new ValidationError(error.getField(), error.getDefaultMessage());
            response.add(currentError);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
