package com.example.springboottddswagger.service;

import com.example.springboottddswagger.model.BookingModel;
import com.example.springboottddswagger.repository.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class BookingServiceTest {
    @Autowired
    private BookingService bookingService;

    @MockBean
    private BookingRepository bookingRepository;

    @BeforeEach
    public void setup(){
        LocalDate checkIn = LocalDate.parse("2022-08-01");
        LocalDate checkOut = LocalDate.parse("2022-08-30");
        BookingModel bookingModel = new BookingModel("1", "Natan", checkIn, checkOut, 10);
    when(bookingRepository.findByReserveName(bookingModel.getReserveName())).thenReturn(Optional.of(bookingModel));
    }

    @Test
    @DisplayName("should calculate the days of reserve correctly")
    public void shouldCalculateDaysOfReserve(){
        Integer daysOfReserve = bookingService.calculateDaysOfReserve("Natan");
        assertEquals(29, daysOfReserve);
    }

}