package com.cts.configserver.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@CrossOrigin
@RefreshScope
public class TestRestCOntroller {
	
	@Value("${name}")
	private String personName;
	
	@GetMapping
	public String test() {
		return "Hello "+personName;
	}

}
