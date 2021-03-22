package com.cts.flight.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.flight.dao.BookingDao;
import com.cts.flight.dao.CheckinDao;
import com.cts.flight.dao.FlightDao;
import com.cts.flight.entity.BookingRecord;
import com.cts.flight.entity.CheckIn;
import com.cts.flight.entity.Flight;
import com.cts.flight.entity.Passenger;
import com.cts.flight.model.SearchQuery;

@RestController
@CrossOrigin
@RequestMapping("/api/pss")
public class FlightRestController {

	@Autowired
	private FlightDao dao;

	@Autowired
	private BookingDao bookingDao;
	
	@Autowired
	private CheckinDao checkinDao;

	@GetMapping
	public List<Flight> findAllFlights() {
		return dao.findAll();
	}

	
	// get booking details for a given booking ID:
	
	@GetMapping("/{bookingId}")
	public BookingRecord getBookingDetails(@PathVariable int bookingId) {
		
		return bookingDao.findById(bookingId).orElse(null);
		
	}
	
	
	@PostMapping
	public List<Flight> findFlights(@RequestBody SearchQuery qry) {

		List<Flight> flights = dao.findFlightByOriginAndDestinationAndFlightDate(qry.getOrigin(), qry.getDestination(),
				qry.getFlightDate());

	

		flights = flights.stream().filter(flight -> flight.getInventory().getCount() >= qry.getNumberofPassengers())
				.collect(Collectors.toList());


		return flights;
	}

	@GetMapping("/{origin}/{destination}/{flightDate}/{numberofPassengers}")
	public List<Flight> findFlightsV1(@PathVariable("origin") String origin,
			@PathVariable("destination") String destination, 
			@PathVariable("flightDate") @DateTimeFormat(iso = ISO.DATE) LocalDate flightDate,
			@PathVariable("numberofPassengers") int numberofPassengers) {

		List<Flight> flights = dao.findFlightByOriginAndDestinationAndFlightDate(origin, destination, flightDate);

		flights = flights.stream().filter(flight -> flight.getInventory().getCount() >= numberofPassengers)
				.collect(Collectors.toList());
		
		return flights;
	}
	
	
	//Booking flight
	
	@PostMapping("/{id}/{numberofPassengers}")
	public BookingRecord bookFlight(@RequestBody Passenger passenger,@PathVariable("id") int id,@PathVariable("numberofPassengers")int numberofPassengers) {
		Flight flight =dao.findById(id).orElse(null);
		BookingRecord book=new BookingRecord();
		book.setOrigin(flight.getOrigin());
		book.setDestination(flight.getDestination());
		book.setFlightDate(flight.getFlightDate());
		book.setBookingDate(LocalDateTime.now());
		book.setFlightNumber(flight.getFlightNumber());
		book.setFlightTime(flight.getFlightTime());
		book.setPassenger(passenger);
		book.setFlightInfo(flight.getFlightInfo());
		book.setFare(flight.getFare().getFare()*numberofPassengers);
		
		book.setStatus("CONFIRMED");
		return bookingDao.save(book);
	}
	
	@PostMapping("/{bookingId}")
	public ResponseEntity<Object> checkin(@PathVariable("bookingId")int bookingId) {
		
		if(checkinDao.findByBookingId(bookingId)!=null) {
			return new ResponseEntity<Object>("Booking ID "+bookingId+" is already checkedin. Can not recheckin",HttpStatus.OK);
		}
		
		CheckIn checkin=new CheckIn();
		checkin.setBookingRecord(bookingDao.findById(bookingId).orElse(null));
		checkin.setCheckinTime(LocalDateTime.now());
		checkin.setSeatNumber("A2");
		
		
		return new ResponseEntity<Object>(checkinDao.save(checkin),HttpStatus.OK);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
