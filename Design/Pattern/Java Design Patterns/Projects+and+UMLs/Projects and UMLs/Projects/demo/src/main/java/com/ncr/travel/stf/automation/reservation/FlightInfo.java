package com.ncr.travel.stf.automation.reservation;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.xml.datatype.XMLGregorianCalendar;

/**
 * @author sr250201 Flight information data-model All the information regarding
 *         the flight selected to create a PNR is propagated using this class
 */
public class FlightInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4597995435327020201L;
	private String flightNumber;
	private String operatingAirlines;
	private String departureCity;
	private String arrivalCity;
	private String classOfService;
	private ZonedDateTime estimatedDepartureTime;

	public ZonedDateTime getEstimatedDepartureTime() {
		return estimatedDepartureTime;
	}

	public void setEstimatedDepartureTime(ZonedDateTime estimatedDepartureTime) {
		this.estimatedDepartureTime = estimatedDepartureTime;
	}

	public ZonedDateTime getEstimatedArrivalTime() {
		return estimatedArrivalTime;
	}

	public void setEstimatedArrivalTime(ZonedDateTime estimatedArrivalTime) {
		this.estimatedArrivalTime = estimatedArrivalTime;
	}

	public ZonedDateTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(ZonedDateTime departureTime) {
		this.departureTime = departureTime;
	}

	public ZonedDateTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(ZonedDateTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	private ZonedDateTime estimatedArrivalTime;
	private ZonedDateTime departureTime;
	private ZonedDateTime arrivalTime;

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getOperatingAirlines() {
		return operatingAirlines;
	}

	public void setOperatingAirlines(String operatingAirlines) {
		this.operatingAirlines = operatingAirlines;
	}

	public String getDepartureCity() {
		return departureCity;
	}

	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity;
	}
	
	public String getArrivalCity() {
		return arrivalCity;
	}

	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}

	public String getClassOfService() {
		return classOfService;
	}

	public void setClassOfService(String classOfService) {
		this.classOfService = classOfService;
	}

}
