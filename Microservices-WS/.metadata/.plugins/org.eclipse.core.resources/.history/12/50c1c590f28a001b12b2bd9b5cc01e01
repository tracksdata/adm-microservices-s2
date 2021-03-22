package com.cts.flight.controller;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import com.cts.flight.service.BookingService;

@Controller
public class Receiver {
	
	
	@Autowired
	private BookingService bookingService;
	
	@Bean
	public Queue q1() {
		return new Queue("checkinQ",false);
	}
	
	@RabbitListener(queues = "checkinQ")
	public void updateBookingStatus(int bookingId) {
		// update status of booking from CONFIRMED to CHECKEDIN
		bookingService.updateBookingStatus("CHECKED-IN",bookingId);
		
	}

}
