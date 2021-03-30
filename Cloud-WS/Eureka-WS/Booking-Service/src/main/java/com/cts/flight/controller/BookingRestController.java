package com.cts.flight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.flight.entity.BookingRecord;
import com.cts.flight.service.BookingService;
import com.cts.flight.service.SearchQuery;

@RestController
@CrossOrigin
@RequestMapping("/api/book")
@RefreshScope
public class BookingRestController {
	
	@Value("${coupon.code}")
	private String couponCode;

	
	@Autowired
	private BookingService bookingService;
	
	@PostMapping
	public BookingRecord bookFlight(@RequestBody SearchQuery sq) {
		
		System.out.println(">>>>> Coupon Discount Applied with Coupon code::::: "+couponCode+" <<<<<<");
		return bookingService.bookFlight(sq);
	}
	
	@GetMapping("/{id}")
	public BookingRecord gettBookingInfo(@PathVariable int id) {
		return bookingService.getBookingInfo(id);
	}

}
