package com.cts.flight.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.flight.entity.Fare;
import com.cts.flight.service.FareService;

@RestController
@CrossOrigin
@RequestMapping("/api/fare")
@RefreshScope
public class FareRestController {

	@Value("${fare.discount}")
	private String fareDiscount;

	@Autowired
	private FareService fareService;

	@GetMapping("/{flightNumber}/{origin}/{destination}/{flightDate}")
	public Fare getFare(@PathVariable String flightNumber, @PathVariable String origin,
			@PathVariable String destination, @PathVariable @DateTimeFormat(iso = ISO.DATE) LocalDate flightDate) {
		System.out.println(">>>>>> Fare Discount:::: " + fareDiscount + " <<<<<<<<");
		return fareService.getFare(flightNumber, origin, destination, flightDate);

	}

}
