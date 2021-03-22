package com.cts.flight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.flight.entity.BookingRecord;
import com.cts.flight.service.BookingService;

@RestController
@CrossOrigin
@RequestMapping("/api/booking")
public class BookingRestController {

	@Autowired
	private BookingService bookingService;

	@PostMapping("/{numberofPassengers}")
	public ResponseEntity<Object> bookFlight(@RequestBody BookingRecord bookingRecord, @PathVariable int numberofPassengers) {
		
		
		BookingRecord bk= bookingService.bookFlight(bookingRecord.getFlightNumber(), bookingRecord.getOrigin(),
				bookingRecord.getDestination(), bookingRecord.getFlightDate(), numberofPassengers,
				bookingRecord.getPassenger());
		
		if(bk==null) {
			return new ResponseEntity<Object>("Not enough Passenger Information. Provide "+numberofPassengers+" Information",HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<Object>(bk,HttpStatus.OK);
	}

	@GetMapping("/{bookingId}")
	public BookingRecord getBookingDetails(@PathVariable int bookingId) {
		return bookingService.getBookingInfo(bookingId);
	}

}
