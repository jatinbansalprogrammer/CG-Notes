package com.ncr.travel.stf.automation.reservation;

import java.io.Serializable;

/**
 * All the information related to a passenger is propagated using this class
 * Passenger data model
 * 
 * @author sr250201
 *
 */
public class PassengerInfo implements Serializable {

	private String firstName;
	private String lastName;
	private String surName;
	private String loyaltyNumber;
	private String loyaltyProgram;
	private boolean accompaniedWithInfant;
	private boolean accompaniedWithChild;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getLoyaltyNumber() {
		return loyaltyNumber;
	}

	public void setLoyaltyNumber(String loyaltyNumber) {
		this.loyaltyNumber = loyaltyNumber;
	}

	public String getLoyaltyProgram() {
		return loyaltyProgram;
	}

	public void setLoyaltyProgram(String loyaltyProgram) {
		this.loyaltyProgram = loyaltyProgram;
	}

	public boolean isAccompaniedWithInfant() {
		return accompaniedWithInfant;
	}

	public void setAccompaniedWithInfant(boolean accompaniedWithInfant) {
		this.accompaniedWithInfant = accompaniedWithInfant;
	}

	public boolean isAccompaniedWithChild() {
		return accompaniedWithChild;
	}

	public void setAccompaniedWithChild(boolean accompaniedWithChild) {
		this.accompaniedWithChild = accompaniedWithChild;
	}

}
