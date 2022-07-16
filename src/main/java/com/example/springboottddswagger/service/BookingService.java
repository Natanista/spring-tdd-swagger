package com.example.springboottddswagger.service;

import com.example.springboottddswagger.model.BookingModel;
import com.example.springboottddswagger.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;
    public Integer calculateDaysOfReserve(String name) {
        Optional<BookingModel> optionalBookingModel = bookingRepository.findByReserveName(name);
        return Period.between(optionalBookingModel.get().getCheckIn(), optionalBookingModel.get().getCheckOut()).getDays();
    }

    public ResponseEntity<List<BookingModel>> findAll() {
        if(bookingRepository.findAll().isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(bookingRepository.findAll());
    }

    public void saveBooking(BookingModel bookingModel) {f
        bookingRepository.save(bookingModel);
    }
}
