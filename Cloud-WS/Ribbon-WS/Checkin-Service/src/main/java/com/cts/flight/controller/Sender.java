package com.cts.flight.controller;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class Sender {
	
	@Autowired
	private RabbitMessagingTemplate rmt;
	
	public Queue queue1() {
		return new Queue("checkinQ",false);
	}
	
	public void sendStatus(Object bookingId) {
		rmt.convertAndSend("checkinQ",bookingId);
	}

}
