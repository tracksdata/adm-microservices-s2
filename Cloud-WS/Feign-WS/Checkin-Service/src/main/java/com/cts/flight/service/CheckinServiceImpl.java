package com.cts.flight.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cts.flight.controller.Sender;
import com.cts.flight.dao.CheckinDao;
import com.cts.flight.entity.BookingRecord;
import com.cts.flight.entity.CheckIn;

@Service
public class CheckinServiceImpl implements CheckinService {

	@Autowired
	private CheckinDao checkinDao;

	@Autowired
	private RestTemplate rt;

	@Autowired
	private Sender sender;

	private String bookingUrl = "http://localhost:8083/api/book";

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Override
	public CheckIn checkin(int bookingId) {

		CheckIn checkin = null;

		BookingRecord bookingRecord = rt.getForObject(bookingUrl + "/" + bookingId, BookingRecord.class);

		if (bookingRecord != null) {
			checkin = new CheckIn();
			checkin.setBookingRecord(bookingRecord);
			checkin.setCheckinTime(LocalDateTime.now());
			checkin.setSeatNumber("B3");

			checkinDao.save(checkin);

			// Send Checkin Confirmation to booking microservice through rabbit MQ

			sender.sendStatus(bookingId);

		}

		return checkin;
	}

	// get checkin information by bookingId

	@Override
	public CheckIn getCheckinInfo(int bookingId) {
		CheckIn checkin = checkinDao.findByBookingId(bookingId);
		if (checkin == null) {
			return null;
		}

		return checkin;
	}

	@Override
	public CheckIn getCheckinByCheckinId(int checkinId) {
		return checkinDao.findById(checkinId).orElse(null);
	}

}
