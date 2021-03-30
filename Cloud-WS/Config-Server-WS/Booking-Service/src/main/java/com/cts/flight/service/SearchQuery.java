package com.cts.flight.service;

import java.time.LocalDate;

import com.cts.flight.entity.Passenger;

public class SearchQuery {

	private String flightNumber;
	private String origin;
	private String destination;
	private LocalDate flightDate;
	private int numberofPassengers;
	
	private Passenger passenger;
	
	

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public LocalDate getFlightDate() {
		return flightDate;
	}

	public void setFlightDate(LocalDate flightDate) {
		this.flightDate = flightDate;
	}

	public int getNumberofPassengers() {
		return numberofPassengers;
	}

	public void setNumberofPassengers(int numberofPassengers) {
		this.numberofPassengers = numberofPassengers;
	}

}
