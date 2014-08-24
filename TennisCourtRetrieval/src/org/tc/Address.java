package org.tc;

import com.mongodb.BasicDBObject;

public class Address {

	private 	String name;
	private String streetAddress;
	private String addressLocality;
	private String addressRegion;
	private String postalCode;
	private String nationality;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getAddressLocality() {
		return addressLocality;
	}

	public void setAddressLocality(String addressLocality) {
		this.addressLocality = addressLocality;
	}

	public String getAddressRegion() {
		return addressRegion;
	}

	public void setAddressRegion(String addressRegion) {
		this.addressRegion = addressRegion;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Address(String name, String streetAddress, String addressLocality,
			String addressRegion, String postalCode, String nationality) {
		this.name = name;
		this.streetAddress = streetAddress;
		this.addressLocality = addressLocality;
		this.addressRegion = addressRegion;
		this.postalCode = postalCode;
		this.nationality = nationality;
	}

	@Override
	public String toString() {
		return "Address [name=" + name + ", streetAddress=" + streetAddress
				+ ", addressLocality=" + addressLocality + ", addressRegion="
				+ addressRegion + ", postalCode=" + postalCode
				+ ", nationality=" + nationality + "]";
	}

}