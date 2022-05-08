package com.bharath.patterns.movietickets.bo;

public class Ticket {

	private int id;
	private String movieName;
	private String screenNo;
	private String seatNo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getScreenNo() {
		return screenNo;
	}

	public void setScreenNo(String screenNo) {
		this.screenNo = screenNo;
	}

	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

}
