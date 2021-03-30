package com.cts.flight.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.flight.entity.Flight;

public interface FlightDao extends JpaRepository<Flight, Integer> {
	
	/*
	 * 
	 * @Query("select flight.origin, flight.destination, flight.date, inventory.count from Flight flight, Inventory inventory
		where flight.source := source and flight.destination := destination and flight.date := date and inventory.count < invCount)
	 * 
	 * 
	 */
	

	Flight findFlightByFlightNumberAndOriginAndDestinationAndFlightDate(String flightNumber, String origin,
			String destination, LocalDate flightDate);

	List<Flight> findFlightByOriginAndDestinationAndFlightDate(String origin, String destination, LocalDate flightDate);

}
