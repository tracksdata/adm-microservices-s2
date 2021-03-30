package com.cts.flight.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.flight.entity.Flight;
import com.cts.flight.service.SearchService;

@RestController
@CrossOrigin
@RequestMapping("/api/search")
public class SearchRestController {
	
	
	@Autowired
	private SearchService searchService;
	
	
	@GetMapping("/{origin}/{destination}/{flightDate}/{numberofPassengers}")
	public List<Flight> searchFlights(
			@PathVariable String origin,
			@PathVariable String destination,
			@PathVariable @DateTimeFormat(iso = ISO.DATE) LocalDate flightDate,
			@PathVariable int numberofPassengers
			){
		
		return searchService.findFlights(origin, destination, flightDate, numberofPassengers);
		
	}
	
	@GetMapping("/findFlight/{flightNumber}/{origin}/{destination}/{flightDate}")
	public Flight findFlightByFlightNumberAndOriginAndDestinationAndFlightDate(
			@PathVariable String flightNumber,
			@PathVariable String origin,
			@PathVariable String destination,
			@PathVariable @DateTimeFormat(iso = ISO.DATE) LocalDate flightDate) {
		return searchService.findFlightByFlightNumberAndOriginAndDestinationAndFlightDate(flightNumber, origin, destination, flightDate);
	}
	
	@GetMapping("/{id}")
	public Flight getFlight(@PathVariable int id) {
		return searchService.findFlight(id);
	}

}
