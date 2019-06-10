package com.frame;

public class Frame {
	
	private boolean isStrike;
	private boolean isKnock;
	private int totalPins;
	private int totalPoints;
	private int []tries;
	private int totalTries;
	
	public Frame(int size){
		isStrike = false;
		isKnock = false;
		totalPins = 0;
		totalPoints = 0;
		tries = new int[size];
	}

	public boolean isStrike() {
		return isStrike;
	}

	public void setStrike(boolean isStrike) {
		this.isStrike = isStrike;
	}

	public boolean isKnock() {
		return isKnock;
	}

	public void setKnock(boolean isKnock) {
		this.isKnock = isKnock;
	}

	public int getTotalPins() {
		return totalPins;
	}

	public void setTotalPins(int totalPins) {
		this.totalPins = totalPins;
	}

	public int getPoints() {
		return totalPoints;
	}

	public void setPoints(int points) {
		this.totalPoints = points;
	}

	public int getTries(int no) {
		return tries[no];
	}

	public void setTries(int no,int pins) {
		this.tries[no] = pins;
	}

	public int getTotalTries() {
		return totalTries;
	}

	public void setTotalTries(int totalTries) {
		this.totalTries = totalTries;
	}
	
}
