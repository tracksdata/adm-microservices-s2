package com.cts.flight.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.flight.dao.FlightDao;
import com.cts.flight.entity.Fare;

@Service
public class FareServiceImpl implements FareService {
	
	@Autowired
	private FlightDao flightDao;
	
	@Override
	public Fare getFare(String flightNumber,String origin,String destination,LocalDate flightDate) {
		return flightDao.findFlightByFlightNumberAndOriginAndDestinationAndFlightDate(flightNumber,origin, destination, flightDate).getFare();
	}
	
	

}
