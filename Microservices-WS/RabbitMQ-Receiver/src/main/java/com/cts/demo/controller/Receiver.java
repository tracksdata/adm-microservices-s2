package com.cts.demo.controller;

import java.util.Map;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Receiver {
	
	
	
	@Bean
	public Queue myQueue1() {
		System.out.println(">>>>>>>> TestQ-1 initialed... <<<<<<<");
		return new Queue("TestQ-1", false);
	}

	
	
	@RabbitListener(queues = "TestQ-1")
	public void receiveMessage(Map<String, Object> flightObj) {
		System.out.println(">>>>>>> Reading message.... <<<<<<<<");
		
		System.out.println("Flight Number: "+flightObj.get("FLIGHT_NUMBER"));
		System.out.println("Flight Date: "+flightObj.get("FLIGHT_DATE"));
		System.out.println("Flight Fare: "+flightObj.get("FLIGHT_FARE"));
		System.out.println("Passenger Name: "+flightObj.get("PASSENGER_NAME"));
		System.out.println("Mobile: "+flightObj.get("MOBILE_NUMBER"));
		System.out.println(">>>>>>> Reading completed <<<<<<<");
		
	}

}
