package com.moath.expedia.bean;

/**
 * This bean holds hotel offer information.
 * 
 * @author Moath
 *
 */
public class HotelOffer {

	private String hotelName;
	private String hotelImageUrl;
	private int hotelReviewTotal;
	private double originalPricePerNight;
	private String province;
	private String country;
	private double hotelGuestReviewRating;
	private String hotelInfositeUrl;

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getHotelImageUrl() {
		return hotelImageUrl;
	}

	public void setHotelImageUrl(String hotelImageUrl) {
		this.hotelImageUrl = hotelImageUrl;
	}

	public int getHotelReviewTotal() {
		return hotelReviewTotal;
	}

	public void setHotelReviewTotal(int hotelReviewTotal) {
		this.hotelReviewTotal = hotelReviewTotal;
	}

	public double getOriginalPricePerNight() {
		return originalPricePerNight;
	}

	public void setOriginalPricePerNight(double originalPricePerNight) {
		this.originalPricePerNight = originalPricePerNight;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String city) {
		this.country = city;
	}

	public double getHotelGuestReviewRating() {
		return hotelGuestReviewRating;
	}

	public void setHotelGuestReviewRating(double d) {
		this.hotelGuestReviewRating = d;
	}

	public String getHotelInfositeUrl() {
		return hotelInfositeUrl;
	}

	public void setHotelInfositeUrl(String hotelInfositeUrl) {
		this.hotelInfositeUrl = hotelInfositeUrl;
	}

	@Override
	public String toString() {
		return "HotelOffer [hotelName=" + hotelName + ", hotelImageUrl=" + hotelImageUrl + ", hotelReviewTotal="
				+ hotelReviewTotal + ", originalPricePerNight=" + originalPricePerNight + ", province=" + province
				+ ", country=" + country + ", hotelGuestReviewRating=" + hotelGuestReviewRating + ", hotelInfositeUrl="
				+ hotelInfositeUrl + "]";
	}
}
