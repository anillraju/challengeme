package org.tc;


public class CourtDetails {

	private String locationType;
	private String totalCourts;
	private String indoorCourts;
	private String fees;
	private String courtType;
	private String lights;
	private String amenities;

	public CourtDetails(String locationType, String totalCourts,
			String indoorCourts, String fees, String courtType, String lights,
			String amenities) {
		super();
		this.locationType = locationType;
		this.totalCourts = totalCourts;
		this.indoorCourts = indoorCourts;
		this.fees = fees;
		this.courtType = courtType;
		this.lights = lights;
		this.amenities = amenities;
	}

	@Override
	public String toString() {
		return "CourtDetails [locationType=" + locationType + ", totalCourts="
				+ totalCourts + ", indoorCourts=" + indoorCourts + ", fees="
				+ fees + ", courtType=" + courtType + ", lights=" + lights
				+ ", amenities=" + amenities + "]";
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public String getTotalCourts() {
		return totalCourts;
	}

	public void setTotalCourts(String totalCourts) {
		this.totalCourts = totalCourts;
	}

	public String getIndoorCourts() {
		return indoorCourts;
	}

	public void setIndoorCourts(String indoorCourts) {
		this.indoorCourts = indoorCourts;
	}

	public String getFees() {
		return fees;
	}

	public void setFees(String fees) {
		this.fees = fees;
	}

	public String getCourtType() {
		return courtType;
	}

	public void setCourtType(String courtType) {
		this.courtType = courtType;
	}

	public String getLights() {
		return lights;
	}

	public void setLights(String lights) {
		this.lights = lights;
	}

	public String getAmenities() {
		return amenities;
	}

	public void setAmenities(String amenities) {
		this.amenities = amenities;
	}

}