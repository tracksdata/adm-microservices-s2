package com.cts.flight.controller;

import java.util.Map;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

@Controller
public class Sender {
	
	@Autowired
	private RabbitTemplate rt;
	
	@Bean
	public Queue q11() {
		return new Queue("InventoryQ",false);
	}
	
	@Bean
	public Queue q22() {
		return new Queue("emailQ",false);
	}
	
	
	
	
	
	public void sendBookingDetails(Map<String, Object> bookingDetails) {
		rt.convertAndSend("InventoryQ",bookingDetails);
		
	}
	
	public void sendEmail(Object bookingInfo) {
		rt.convertAndSend("emailQ",bookingInfo);
	}
	
	

}
