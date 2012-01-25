package com.runninghusky.spacetracker.map.example;

public class Coordinate {
	private double longitude;
	private double latitude;

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public Coordinate() {

	}

	public Coordinate(double lon, double lat) {
		this.longitude = lon;
		this.latitude = lat;
	}

	@Override
	public String toString() {
		return "Coordinate [latitude=" + latitude + ", longitude=" + longitude
				+ "]";
	}
}
