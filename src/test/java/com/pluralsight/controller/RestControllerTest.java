package com.pluralsight.controller;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestTemplate;

import com.pluralsight.model.Ride;
import com.pluralsight.util.ErrorHandler;

import org.junit.Test;

public class RestControllerTest {

	@Test(timeout = 10000)
	public void testCreateRide() {
		RestTemplate restTemplate = new RestTemplate();
		Ride rideObj = new Ride();
		rideObj.setName("Germany Grand prix One");
		rideObj.setDuration(72);
		Ride retRide = restTemplate.postForObject("http://localhost:8080/ride_tracker/ride", rideObj, Ride.class);
		System.out.println(retRide.getId() + "Ride name: " + retRide.getName());

	}

	@Test(timeout = 10000)
	public void testGetRides() {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<List<Ride>> ridesResponse = restTemplate.exchange("http://localhost:8080/ride_tracker/rides",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Ride>>() {
				});
		List<Ride> rides = ridesResponse.getBody();

		for (Ride ride : rides) {
			System.out.println(ride.getId() + "Ride name: " + ride.getName());
		}
	}
	
	
	@Test(timeout = 10000)
	public void testGetRide() {
		RestTemplate restTemplate = new RestTemplate();
		Ride ride= restTemplate.getForObject("http://localhost:8080/ride_tracker/ride/3", Ride.class);
		System.out.println(ride.getId() + "Ride name: " + ride.getName());
	}
	
	
	@Test(timeout = 10000)
	public void testUpdateRide() {
		RestTemplate restTemplate = new RestTemplate();
		Ride ride= restTemplate.getForObject("http://localhost:8080/ride_tracker/ride/3", Ride.class);
		ride.setDuration(100);
		restTemplate.put("http://localhost:8080/ride_tracker/ride", ride);
		System.out.println(ride.getId() + "Ride name: " + ride.getName()+"###"+ride.getDuration());
	}
	
	
	@Test(timeout = 10000)
	public void testDeleteRide() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete("http://localhost:8080/ride_tracker/ride/5", Object.class);
	}
	
	@Test(timeout = 10000)
	public void testException() {
		RestTemplate restTemplate = new RestTemplate();
		 restTemplate.getForObject("http://localhost:8080/ride_tracker/test", Object.class);
	}
	
	
	
}
