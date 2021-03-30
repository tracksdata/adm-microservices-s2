package com.cts.flight.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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
@RefreshScope
public class SearchRestController {

	@Value("${originairports.shutdown}")
	private String originAirportShutdownList;

	@Autowired
	private SearchService searchService;

	@GetMapping("/{origin}/{destination}/{flightDate}/{numberofPassengers}")
	public List<Flight> searchFlights(@PathVariable String origin, @PathVariable String destination,
			@PathVariable @DateTimeFormat(iso = ISO.DATE) LocalDate flightDate, @PathVariable int numberofPassengers) {
		System.out.println("-------------------------------------------");
		System.out.println(">>>>>> SEARCH-SERVICE-5 INVOKED <<<<<");
		System.out.println("-------------------------------------------");
		if (Arrays.asList(originAirportShutdownList.split(",")).contains(origin)) {
			System.out.println(
					">>>>>>>>>>> The origin airport " + originAirportShutdownList + " is in shutdown state <<<<<<<<");
			return new ArrayList<Flight>();
		}

		return searchService.findFlights(origin, destination, flightDate, numberofPassengers);

	}

	@GetMapping("/findFlight/{flightNumber}/{origin}/{destination}/{flightDate}")
	public Flight findFlightByFlightNumberAndOriginAndDestinationAndFlightDate(@PathVariable String flightNumber,
			@PathVariable String origin, @PathVariable String destination,
			@PathVariable @DateTimeFormat(iso = ISO.DATE) LocalDate flightDate) {
		
		System.out.println("-------------------------------------------");
		System.out.println(">>>>>> SEARCH-SERVICE-5 INVOKED <<<<<");
		System.out.println("-------------------------------------------");

		if (Arrays.asList(originAirportShutdownList.split(",")).contains(origin)) {
			System.out.println(
					">>>>>>>>>>> The origin airport " + originAirportShutdownList + " is in shutdown state <<<<<<<<");
			return null;
		}

		return searchService.findFlightByFlightNumberAndOriginAndDestinationAndFlightDate(flightNumber, origin,
				destination, flightDate);

	}

	@GetMapping("/{id}")
	public Flight getFlight(@PathVariable int id) {
		
		System.out.println("-------------------------------------------");
		System.out.println(">>>>>> SEARCH-SERVICE-5 INVOKED <<<<<");
		System.out.println("-------------------------------------------");

		return searchService.findFlight(id);
	}

}
