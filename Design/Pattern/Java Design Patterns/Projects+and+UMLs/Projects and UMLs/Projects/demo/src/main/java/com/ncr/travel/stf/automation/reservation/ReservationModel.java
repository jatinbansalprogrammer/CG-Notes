package com.ncr.travel.stf.automation.reservation;

import java.io.Serializable;
import java.util.List;

/**
 * The Reservation Model created using SHARES. This Model contains all the
 * information regarding the reservation created
 * 
 * @author sr250201
 *
 */
public class ReservationModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String locator;
	private List<PassengerInfo> passengersInfo;
	private List<FlightInfo> flightsInfo;

	/**
	 * @return the locator
	 */
	public String getLocator() {
		return locator;
	}

	/**
	 * @param locator
	 *            the locator to set
	 */
	public void setLocator(String locator) {
		this.locator = locator;
	}

	public List<PassengerInfo> getPassengersInfo() {
		return passengersInfo;
	}

	public void setPassengersInfo(List<PassengerInfo> passengersInfo) {
		this.passengersInfo = passengersInfo;
	}

	public List<FlightInfo> getFlightsInfo() {
		return flightsInfo;
	}

	public void setFlightsInfo(List<FlightInfo> flightsInfo) {
		this.flightsInfo = flightsInfo;
	}

}
