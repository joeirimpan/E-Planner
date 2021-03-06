package com.planit.model;

import java.util.ArrayList;

public class UserConstraints {
	private String destinationName;
	private String ratingSelected;//1,2,3,4
	private boolean parkingSelected;//true or false
	private String board;//allInclusive,breakfast,fullboard,halfboard
	private ArrayList<String> activities;//hiking,surfing,skiing,shopping,sightseeing
	private boolean sPoolSelected;
	private boolean fitnessRoomSelected;
	private String transport;//Bus,Plane,Train,Auto
	
	public UserConstraints(){
		this.destinationName = "";
		this.board = "";
		this.activities = new ArrayList<String>();
		this.transport = "";
	}
	
	public String getDestinationName() {
		return destinationName;
	}
	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}
	public String getRatingSelected() {
		return ratingSelected;
	}
	public void setRatingSelected(String ratingSelected) {
		this.ratingSelected = ratingSelected;
	}
	public boolean isParkingSelected() {
		return parkingSelected;
	}
	public void setParkingSelected(boolean parkingSelected) {
		this.parkingSelected = parkingSelected;
	}
	public String getBoard() {
		return board;
	}
	public void setBoard(String board) {
		this.board = board;
	}
	public ArrayList<String> getActivities() {
		return activities;
	}
	
	public void setActivities(ArrayList<String> activities) {
		this.activities = activities;
	}

	public boolean issPoolSelected() {
		return sPoolSelected;
	}
	public void setsPoolSelected(boolean sPoolSelected) {
		this.sPoolSelected = sPoolSelected;
	}
	public boolean isFitnessRoomSelected() {
		return fitnessRoomSelected;
	}
	public void setFitnessRoomSelected(boolean fitnessRoomSelected) {
		this.fitnessRoomSelected = fitnessRoomSelected;
	}

	public String getTransport() {
		return transport;
	}

	public void setTransport(String transport) {
		this.transport = transport;
	}
	
	
	
}
