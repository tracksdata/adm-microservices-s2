package com.cts.flight.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.flight.dao.FlightDao;
import com.cts.flight.entity.Flight;
import com.cts.flight.entity.Inventory;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private FlightDao flightDao;

	@Override
	public void updateInventory(String flightNumber, String origin, String destination, LocalDate flightDate,
			int newInventory) {

		Flight flight = flightDao.findFlightByFlightNumberAndOriginAndDestinationAndFlightDate(flightNumber, origin,
				destination, flightDate);
		Inventory inventory = flight.getInventory();
		inventory.setCount(inventory.getCount() - newInventory);
		flight.setInventory(inventory);
		flightDao.save(flight);

	}

	@Override
	public List<Flight> findFlights(String origin, String destination, LocalDate flightDate, int numberofPassengers) {

		List<Flight> flights = flightDao.findFlightByOriginAndDestinationAndFlightDate(origin, destination, flightDate);

		System.out.println("Before Filter: Size: " + flights.size());

		List<Flight> filteredFlights = flights.stream()
				.filter(flight -> flight.getInventory().getCount() >= numberofPassengers).collect(Collectors.toList());

		System.out.println("After Filter: Size: " + filteredFlights.size());

		return filteredFlights;
	}

	public Flight findFlightByFlightNumberAndOriginAndDestinationAndFlightDate(String flightNumber, String origin,
			String destination, LocalDate flightDate) {
		return flightDao.findFlightByFlightNumberAndOriginAndDestinationAndFlightDate(flightNumber, origin, destination,
				flightDate);

	}

	@Override
	public Flight findFlight(int id) {
		// TODO Auto-generated method stub
		return flightDao.findById(id).orElse(null);
	}

}
