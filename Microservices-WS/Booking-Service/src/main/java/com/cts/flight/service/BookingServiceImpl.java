package com.cts.flight.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cts.flight.controller.Sender;
import com.cts.flight.dao.BookingDao;
import com.cts.flight.entity.BookingRecord;
import com.cts.flight.entity.CoPassenger;
import com.cts.flight.entity.Fare;
import com.cts.flight.entity.Flight;
import com.cts.flight.entity.FlightInfo;
import com.cts.flight.entity.Passenger;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingDao bookingDao;
	
	@Autowired
	private Sender sender;
	

	@Autowired
	private RestTemplate restTemplate;

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	private String searchUrl = "http://localhost:8082/api/search";
	private String fareUrl = "http://localhost:8081/api/fare";

	
	
	@Override
	public BookingRecord bookFlight(SearchQuery query) {

		System.out.println("==================================================");
		System.out.println("Passenger name: "+query.getPassenger().getFirstName());
		if(query.getPassenger().getCoPassengers().size()!=0) {
			for(CoPassenger cp:query.getPassenger().getCoPassengers()) {
				System.out.println("First name: "+cp.getFirstName());
				System.out.println("Last Name: "+cp.getLastName());
				System.out.println("Gender: "+cp.getGender());
			}
		}
		System.out.println("==================================================");

		
		BookingRecord bookingRecord=null;
		
		Fare fare = restTemplate.getForObject(fareUrl + "/" + query.getFlightNumber() + "/"
				+ query.getOrigin() + "/" + query.getDestination() + "/" + query.getFlightDate(),
				Fare.class);

		Flight flight = restTemplate.getForObject(searchUrl + "/findFlight/" + query.getFlightNumber() + "/"
				+ query.getOrigin() + "/" + query.getDestination() + "/" + query.getFlightDate(), Flight.class);

		
		
		if(flight.getInventory().getCount()<query.getNumberofPassengers()) {
			System.out.println(">>>>>>>> No more Seats Avialble <<<<<<<<<");
			return null;
		}
		
		if(flight!=null) {
			System.out.println(">>>>>>>>> FLIGHT INFO <<<<<<<<");
			
			bookingRecord= new BookingRecord(flight.getFlightNumber(), flight.getOrigin(), flight.getDestination(), flight.getFare().getFare()*query.getNumberofPassengers(), flight.getFlightDate(),
					flight.getFlightTime(), LocalDateTime.now(), "Confirmed", flight.getFlightInfo(),
					query.getPassenger());
			
			System.out.println("Booking Record: "+bookingRecord);
			if(query.getPassenger().getCoPassengers().size()==query.getNumberofPassengers()-1) {
				
				bookingDao.save(bookingRecord);
			}else {
				System.out.println("Passenger count is not enogh. Booking is nott done..");
			}
		}
		
		Map<String, Object> bookingDetails=new HashMap<>();
		bookingDetails.put("FLIGHT_NUMBER", flight.getFlightNumber());
		bookingDetails.put("FLIGHT_DATE", flight.getFlightDate());
		bookingDetails.put("ORIGIN", flight.getOrigin());
		bookingDetails.put("DESTINATION", flight.getDestination());
		bookingDetails.put("NEW_INVENTORY", query.getNumberofPassengers());

		sender.sendInventoryData(bookingDetails);
		return bookingRecord;

	}
	
	
	@Override
	public BookingRecord getBookingInfo(int id) {
		return bookingDao.findById(id).orElse(null);
	}
	
	
	
	
	
	
	
	
	
	
	
}
