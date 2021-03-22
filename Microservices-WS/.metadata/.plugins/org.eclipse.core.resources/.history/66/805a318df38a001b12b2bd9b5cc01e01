package com.cts.flight.service;

import java.time.LocalDate;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.cts.flight.entity.BookingRecord;
import com.cts.flight.entity.Passenger;

public interface BookingService {

	RestTemplate restTemplate();

	BookingRecord bookFlight(String flightNumber, String origin, String destination, LocalDate flightDate,
			int numberofPassengers, Passenger passenger);

	void updateBookingStatus(String status, int bookingId);

	BookingRecord getBookingInfo(int bookingId);

}