package com.cts.flight.controller;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitMessageOperations;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

@Controller
public class Sender {
	
	@Autowired
	private RabbitMessagingTemplate template;
	
	@Bean
	public Queue queue1() {
		return new Queue("inventoryQ",false);
	}
	
	@Bean
	public Queue queue2() {
		return new Queue("checkinQ",false);
	}
	
	public void sendInventoryData(Object map) {
		System.out.println(">>>> Sending New Invemtory details to inventoryQ <<<<<");
		template.convertAndSend("inventoryQ",map);
	}
	
	
	
	
	
	

}
