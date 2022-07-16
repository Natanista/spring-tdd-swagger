package com.example.springboottddswagger.controller;

import com.example.springboottddswagger.model.BookingModel;
import com.example.springboottddswagger.repository.BookingRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookingControllerTest {

    private final String BASE_URL = "/api/v1/bookings";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookingRepository bookingRepository;

    @Test
    @DisplayName("get Bookings should return body and 200")
    void findAllBookings() throws Exception {
        List<BookingModel> bookingModels = new ArrayList<>();
        LocalDate checkIn = LocalDate.parse("2022-08-01");
        LocalDate checkOut = LocalDate.parse("2022-08-30");
        BookingModel bookingModel = new BookingModel(1L, "Natan", checkIn, checkOut, 10);
        bookingModels.add(bookingModel);
        when(bookingRepository.findAll()).thenReturn(bookingModels);
        bookingRepository.save(bookingModel);
        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("post Bookings should return 201")
    void postBooking() throws Exception {
        LocalDate checkIn = LocalDate.parse("2022-08-01");
        LocalDate checkOut = LocalDate.parse("2022-08-30");
        BookingModel bookingModel = new BookingModel(1L, "Natan", checkIn, checkOut, 10);

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookingModel)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("get should return no content if dont return body")
    void getWithoutBOokings() throws Exception {
        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isNoContent());
    }
}