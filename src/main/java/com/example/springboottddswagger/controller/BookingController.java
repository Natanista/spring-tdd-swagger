package com.example.springboottddswagger.controller;

import com.example.springboottddswagger.model.BookingModel;
import com.example.springboottddswagger.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<BookingModel>> findAllBookings(){
        return bookingService.findAll();
    }

    @PostMapping
    public ResponseEntity<BookingModel> postBooking(
            @RequestBody BookingModel bookingModel
    ){
        bookingService.saveBooking(bookingModel);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
