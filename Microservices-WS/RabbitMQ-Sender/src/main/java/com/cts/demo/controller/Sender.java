package com.cts.demo.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Sender {
	
	 private Map<String, Object> data=new HashMap<>();

	@Autowired
	private RabbitMessagingTemplate rt;

	@Bean
	public Queue myQueue1() {
		System.out.println(">>>>>>>> Creating QUEUE <<<<<<<");
		return new Queue("TestQ-1", false);
	}

	@GetMapping("/send")
	public Map<String, Object> sendMessage() {
		
		data.put("FLIGHT_NUMBER", "FK-246");
		data.put("FLIGHT_DATE", LocalDate.of(2021, 03, 18));
		data.put("FLIGHT_FARE", 400000);
		data.put("PASSENGER_NAME", "Praveen Reddy");
		data.put("EMAIL_ADDRESS", "praveen.somireddy@gmail.com");
		data.put("MOBILE_NUMBER", 8643276543L);
		
		
		rt.convertAndSend("TestQ-1", data);
		return data;
	}

}
