package com.cts.flight.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class BookingRecord {
	@Id
	@GeneratedValue
	private int bookingId;
	private String flightNumber;
	private String origin;
	private String destination;
	private double fare;
	private LocalDate flightDate;
	private LocalTime flightTime;
	private LocalDateTime bookingDate;
	private String status;

	public BookingRecord() {
		// TODO Auto-generated constructor stub
	}

	public BookingRecord(String flightNumber, String origin, String destination, double fare, LocalDate flightDate,
			LocalTime flightTime, LocalDateTime bookingDate, String status) {
		super();
		this.flightNumber = flightNumber;
		this.origin = origin;
		this.destination = destination;
		this.fare = fare;
		this.flightDate = flightDate;
		this.flightTime = flightTime;
		this.bookingDate = bookingDate;
		this.status = status;
		
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
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

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}

	public LocalDate getFlightDate() {
		return flightDate;
	}

	public void setFlightDate(LocalDate flightDate) {
		this.flightDate = flightDate;
	}

	public LocalTime getFlightTime() {
		return flightTime;
	}

	public void setFlightTime(LocalTime flightTime) {
		this.flightTime = flightTime;
	}

	public LocalDateTime getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDateTime bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
}
