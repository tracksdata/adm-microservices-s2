package com.cts.flight.service;

import java.time.LocalDate;
import java.util.List;

import com.cts.flight.entity.Flight;

public interface SearchService {

	void updateInventory(String flightNumber, String origin, String destination, LocalDate flightDate,
			int newInventory);

	List<Flight> findFlights(String origin, String destination, LocalDate flightDate, int numberofPassengers);

	Flight findFlight(int id);

	Flight findFlightByFlightNumberAndOriginAndDestinationAndFlightDate(String flightNumber, String origin,
			String destination, LocalDate flightDate);

}