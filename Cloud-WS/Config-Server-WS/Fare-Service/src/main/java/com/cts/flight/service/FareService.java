package com.cts.flight.service;

import java.time.LocalDate;

import com.cts.flight.entity.Fare;

public interface FareService {

	Fare getFare(String flightNumber, String origin, String destination, LocalDate flightDate);

}