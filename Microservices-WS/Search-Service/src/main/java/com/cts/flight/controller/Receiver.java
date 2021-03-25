package com.cts.flight.controller;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import com.cts.flight.service.SearchService;

@Controller
public class Receiver {

	@Autowired
	private SearchService searchService;

	@Bean
	public Queue quequ1() {
		return new Queue("inventoryQ", false);
	}

	@RabbitListener(queues = "inventoryQ")
	public void updateInventory(Map<String, Object> flightDetails) {
		System.out
				.println(">>>>>> Search Microservice Updating Inventory Details given by booking MicroService <<<<<<<");
		searchService.updateInventory((String) flightDetails.get("FLIGHT_NUMBER"),
				(String) flightDetails.get("ORIGIN"), (String) flightDetails.get("DESTINATION"),
				(LocalDate) flightDetails.get("FLIGHT_DATE"), (Integer) flightDetails.get("NEW_INVENTORY"));
		System.out.println(">>>>>>> Inventory Updation Completed <<<<<<<<");
	}

}
