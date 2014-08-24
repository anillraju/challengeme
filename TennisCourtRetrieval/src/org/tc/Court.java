package org.tc;

import com.mongodb.BasicDBObject;

public class Court{
	private String map;
	private CourtDetails courtDetails;
	private Address address;

	public Court(String map, CourtDetails courtDetails, Address address) {
		this.map = map;
		this.courtDetails = courtDetails;
		this.address = address;
	}

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}

	public CourtDetails getCourtDetails() {
		return courtDetails;
	}

	public void setCourtDetails(CourtDetails courtDetails) {
		this.courtDetails = courtDetails;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Court [map=" + map + ", courtDetails=" + courtDetails
				+ ", address=" + address + "]";
	}

}