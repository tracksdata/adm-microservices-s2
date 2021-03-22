package com.cts.flight.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.flight.entity.Flight;

public interface FlightDao extends JpaRepository<Flight, Integer> {

	public Flight findFlightByFlightNumberAndOriginAndDestinationAndFlightDateAndFlightTime(String flightNumber,
			String origin, String destination, LocalDate flightDate, LocalTime flightTime);

	public Flight findFlightByFlightNumberAndFlightDate(String flightNumber, LocalDate flightDate);
	Flight findByFlightNumberAndFlightDateAndOriginAndDestination(String flightNumber,LocalDate flightDate,String origin,String destination);

	
	public List<Flight> findFlightByOriginAndDestinationAndFlightDate(String origin,String destination,LocalDate flightDate);

}
