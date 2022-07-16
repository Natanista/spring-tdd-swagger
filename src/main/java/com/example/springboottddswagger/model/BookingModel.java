package com.example.springboottddswagger.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

import static javax.persistence.GenerationType.AUTO;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class BookingModel {
    @Id
    @GeneratedValue(strategy = AUTO)
    private String id;
    private String reserveName;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Integer numberGuests;
}
