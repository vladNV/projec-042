package com.example.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

class Result {

    static <T> ResponseEntity<T> response(HttpStatus status) {
        return new ResponseEntity<>(status);
    }

    static <T> ResponseEntity<T> response(T entity, HttpStatus status) {
        return new ResponseEntity<>(entity, status);
    }

    static <T> ResponseEntity<List<T>> response(List<T> entity, HttpStatus status) {
        return new ResponseEntity<>(entity, status);
    }

}
