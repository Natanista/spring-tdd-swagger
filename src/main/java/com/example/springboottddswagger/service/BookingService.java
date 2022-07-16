package com.example.springboottddswagger.service;

import com.example.springboottddswagger.model.BookingModel;
import com.example.springboottddswagger.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;
    public Integer calculateDaysOfReserve(String name) {
        Optional<BookingModel> optionalBookingModel = bookingRepository.findByReserveName(name);
        return Period.between(optionalBookingModel.get().getCheckIn(), optionalBookingModel.get().getCheckOut()).getDays();
    }
}
