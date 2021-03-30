package com.cts.flight.service;

import com.cts.flight.entity.BookingRecord;

public interface BookingService {

	BookingRecord bookFlight(SearchQuery query);

	BookingRecord getBookingInfo(int id);

	public void updateStatus(String status,int bookingId);
}