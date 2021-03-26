package com.cts.flight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.flight.entity.CheckIn;
import com.cts.flight.service.CheckinService;

@RestController
@CrossOrigin
@RequestMapping("/api/checkin")
public class CheckinRestController {

	@Autowired
	private CheckinService checkinService;

	@PostMapping("/{bookingId}")
	public CheckIn checkin(@PathVariable int bookingId) {
		return checkinService.checkin(bookingId);
	}

	@GetMapping("/{bookingId}")
	public CheckIn getByBookingId(@PathVariable int bookingId) {
		return checkinService.getCheckinInfo(bookingId);
	}

	@GetMapping("byCheckinId/{checkinId}")
	public CheckIn getByCheckinId(@PathVariable int checkinId) {
		return checkinService.getCheckinByCheckinId(checkinId);
	}

}
