package com.cts.flight.controller;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.cts.flight.service.BookingService;

@Controller
public class Receiver {

	@Autowired
	private BookingService bookingService;

	@RabbitListener(queues = "checkinQ")
	public void updateBookingStatus(int bookingId) {
		System.out.println(">>> BOOKING Microservice Updating booking status <<<");
		bookingService.updateStatus("Checked-IN", bookingId);
		System.out.println(">>> Booking Status updation is complete <<<");
	}

}
