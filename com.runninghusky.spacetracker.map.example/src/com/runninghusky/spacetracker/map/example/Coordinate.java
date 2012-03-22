package com.runninghusky.spacetracker.map.example;

/**
 * The Class Coordinate.
 */
public class Coordinate {

	/** The longitude. */
	private double longitude;

	/** The latitude. */
	private double latitude;

	/**
	 * Gets the longitude.
	 * 
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * Sets the longitude.
	 * 
	 * @param longitude
	 *            the new longitude
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * Gets the latitude.
	 * 
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * Sets the latitude.
	 * 
	 * @param latitude
	 *            the new latitude
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * Instantiates a new coordinate.
	 */
	public Coordinate() {

	}

	/**
	 * Instantiates a new coordinate.
	 * 
	 * @param longitude
	 *            the longitude
	 * @param latitude
	 *            the latitude
	 */
	public Coordinate(double lon, double lat) {
		this.longitude = lon;
		this.latitude = lat;
	}

	/**
	 * Overrides the toString method
	 */
	@Override
	public String toString() {
		return "Coordinate [latitude=" + latitude + ", longitude=" + longitude
				+ "]";
	}
}
