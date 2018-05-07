package com.moath.expedia.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.moath.expedia.bean.HotelOffer;

public class HotelOffersServiceTest {

	@Test
	public void testComposeServiceURI_testEmptyFilters() throws Exception {

		Map<String, String> emptyFilters = new LinkedHashMap<>();
		URI uri = new HotelOffersService().composeURI(emptyFilters);
		Assert.assertEquals(new URI(HotelOffersService.BASE_URL), uri);
	}

	@Test
	public void testComposeServiceURI_testWithFilters() throws Exception {

		HotelOffersService hotelOffersService = new HotelOffersService();
		StringBuilder sb = new StringBuilder(HotelOffersService.BASE_URL);
		sb.append("&destinationName=America&minTripStartDate=2018-03-12");
		Map<String, String> filters = new LinkedHashMap<>();
		filters.put("destinationName", "America");
		filters.put("minTripStartDate", "2018-03-12");
		Assert.assertEquals(sb.toString(), hotelOffersService.composeURI(filters).toString());
	}

	@Test
	public void testParseResponse_testHotelNameField() throws Exception {

		String hotelInfoString = "{\"offerInfo\":{\"siteID\":\"1\",\"language\":\"en_US\",\"currency\":\"USD\",\"userSelectedCurrency\":\"USD\"},\"userInfo\":{\"persona\":{\"personaType\":\"OTHERS\"},\"userId\":\"foo\"},\"offers\":{\"Hotel\":[{\"offerDateRange\":{\"travelStartDate\":[2018,5,18],\"travelEndDate\":[2018,5,23],\"lengthOfStay\":5},\"destination\":{\"regionID\":\"178286\",\"associatedMultiCityRegionId\":\"178286\",\"longName\":\"Miami (and vicinity), Florida, United States of America\",\"shortName\":\"Miami\",\"country\":\"United States of America\",\"province\":\"Florida\",\"city\":\"Miami\",\"tla\":\"FLL\",\"nonLocalizedCity\":\"Miami\"},\"hotelInfo\":{\"hotelId\":\"14882350\",\"hotelName\":\"Private Ocean Condos at Marenas Beach\",\"localizedHotelName\":\"Private Ocean Condos at Marenas Beach\",\"hotelDestination\":\"Sunny Isles Beach\",\"hotelDestinationRegionID\":\"10405\",\"hotelLongDestination\":\"Sunny Isles Beach,FL,USA\",\"hotelStreetAddress\":\"18683 Collins Ave\",\"hotelCity\":\"Sunny Isles Beach\",\"hotelProvince\":\"FL\",\"hotelCountryCode\":\"USA\",\"hotelLatitude\":25.94906,\"hotelLongitude\":-80.120711,\"hotelStarRating\":\"4.0\",\"hotelGuestReviewRating\":3.03,\"hotelReviewTotal\":33,\"hotelImageUrl\":\"https://images.trvl-media.com/hotels/15000000/14890000/14882400/14882350/14882350_17_t.jpg\",\"vipAccess\":false,\"isOfficialRating\":false},\"hotelPricingInfo\":{\"averagePriceValue\":201.17,\"totalPriceValue\":1005.85,\"crossOutPriceValue\":804.6,\"originalPricePerNight\":804.6,\"currency\":\"USD\",\"percentSavings\":75.0,\"drr\":false},\"hotelUrls\":{\"hotelInfositeUrl\":\"https%3A%2F%2Fwww.expedia.com%2Fgo%2Fhotel%2Finfo%2F14882350%2F2018-05-18%2F2018-05-23\",\"hotelSearchResultUrl\":\"https%3A%2F%2Fwww.expedia.com%2Fgo%2Fhotel%2Fsearch%2FDestination%2F2018-05-18%2F2018-05-23%3FSearchType%3DDestination%26CityName%3DSunny+Isles+Beach%26RegionId%3D178286%26Selected%3D14882350\"}}]}}\"";
		InputStream hotelOffersInfoInputStream = new ByteArrayInputStream(hotelInfoString.getBytes());
		HotelOffersService hotelOffersService = new HotelOffersService();
		List<HotelOffer> hotelOffersList = hotelOffersService.parseResponse(hotelOffersInfoInputStream);
		Assert.assertEquals("Private Ocean Condos at Marenas Beach", hotelOffersList.get(0).getHotelName());
	}

	@Test
	public void testParseResponse_sixHotelOffers() throws Exception {

		String fourOffersString = "{\"offerInfo\":{\"siteID\":\"1\",\"language\":\"en_US\",\"currency\":\"USD\",\"userSelectedCurrency\":\"USD\"},\"userInfo\":{\"persona\":{\"personaType\":\"OTHERS\"},\"userId\":\"foo\"},\"offers\":{\"Hotel\":[{\"offerDateRange\":{\"travelStartDate\":[2018,5,18],\"travelEndDate\":[2018,5,23],\"lengthOfStay\":5},\"destination\":{\"regionID\":\"178286\",\"associatedMultiCityRegionId\":\"178286\",\"longName\":\"Miami (and vicinity), Florida, United States of America\",\"shortName\":\"Miami\",\"country\":\"United States of America\",\"province\":\"Florida\",\"city\":\"Miami\",\"tla\":\"FLL\",\"nonLocalizedCity\":\"Miami\"},\"hotelInfo\":{\"hotelId\":\"14882350\",\"hotelName\":\"Private Ocean Condos at Marenas Beach\",\"localizedHotelName\":\"Private Ocean Condos at Marenas Beach\",\"hotelDestination\":\"Sunny Isles Beach\",\"hotelDestinationRegionID\":\"10405\",\"hotelLongDestination\":\"Sunny Isles Beach,FL,USA\",\"hotelStreetAddress\":\"18683 Collins Ave\",\"hotelCity\":\"Sunny Isles Beach\",\"hotelProvince\":\"FL\",\"hotelCountryCode\":\"USA\",\"hotelLatitude\":25.94906,\"hotelLongitude\":-80.120711,\"hotelStarRating\":\"4.0\",\"hotelGuestReviewRating\":3.03,\"hotelReviewTotal\":33,\"hotelImageUrl\":\"https://images.trvl-media.com/hotels/15000000/14890000/14882400/14882350/14882350_17_t.jpg\",\"vipAccess\":false,\"isOfficialRating\":false},\"hotelPricingInfo\":{\"averagePriceValue\":201.17,\"totalPriceValue\":1005.85,\"crossOutPriceValue\":804.6,\"originalPricePerNight\":804.6,\"currency\":\"USD\",\"percentSavings\":75.0,\"drr\":false},\"hotelUrls\":{\"hotelInfositeUrl\":\"https%3A%2F%2Fwww.expedia.com%2Fgo%2Fhotel%2Finfo%2F14882350%2F2018-05-18%2F2018-05-23\",\"hotelSearchResultUrl\":\"https%3A%2F%2Fwww.expedia.com%2Fgo%2Fhotel%2Fsearch%2FDestination%2F2018-05-18%2F2018-05-23%3FSearchType%3DDestination%26CityName%3DSunny+Isles+Beach%26RegionId%3D178286%26Selected%3D14882350\"}},{\"offerDateRange\":{\"travelStartDate\":[2018,5,22],\"travelEndDate\":[2018,5,26],\"lengthOfStay\":4},\"destination\":{\"regionID\":\"178305\",\"associatedMultiCityRegionId\":\"178305\",\"longName\":\"San Francisco (and vicinity), California, United States Of America\",\"shortName\":\"San Francisco\",\"country\":\"United States of America\",\"province\":\"California\",\"city\":\"San Francisco\",\"tla\":\"SFO\",\"nonLocalizedCity\":\"San Francisco\"},\"hotelInfo\":{\"hotelId\":\"12671293\",\"hotelName\":\"Calista Organic Hotel\",\"localizedHotelName\":\"Calista Organic Hotel\",\"hotelDestination\":\"San Francisco\",\"hotelDestinationRegionID\":\"3132\",\"hotelLongDestination\":\"San Francisco,CA,USA\",\"hotelStreetAddress\":\"1485 Bush Street\",\"hotelCity\":\"San Francisco\",\"hotelProvince\":\"CA\",\"hotelCountryCode\":\"USA\",\"hotelLatitude\":37.788547,\"hotelLongitude\":-122.421629,\"hotelStarRating\":\"3.0\",\"hotelGuestReviewRating\":3.64,\"hotelReviewTotal\":238,\"hotelImageUrl\":\"https://images.trvl-media.com/hotels/13000000/12680000/12671300/12671293/0a945c93_t.jpg\",\"vipAccess\":false,\"isOfficialRating\":false},\"hotelPricingInfo\":{\"averagePriceValue\":169.34,\"totalPriceValue\":677.36,\"crossOutPriceValue\":546.25,\"originalPricePerNight\":546.25,\"currency\":\"USD\",\"percentSavings\":69.0,\"drr\":false},\"hotelUrls\":{\"hotelInfositeUrl\":\"https%3A%2F%2Fwww.expedia.com%2Fgo%2Fhotel%2Finfo%2F12671293%2F2018-05-22%2F2018-05-26\",\"hotelSearchResultUrl\":\"https%3A%2F%2Fwww.expedia.com%2Fgo%2Fhotel%2Fsearch%2FDestination%2F2018-05-22%2F2018-05-26%3FSearchType%3DDestination%26CityName%3DSan+Francisco%26RegionId%3D178305%26Selected%3D12671293\"}},{\"offerDateRange\":{\"travelStartDate\":[2018,10,20],\"travelEndDate\":[2018,10,21],\"lengthOfStay\":1},\"destination\":{\"regionID\":\"178280\",\"associatedMultiCityRegionId\":\"178280\",\"longName\":\"Los Angeles (and vicinity), California, United States Of America\",\"shortName\":\"Los Angeles\",\"country\":\"United States of America\",\"province\":\"California\",\"city\":\"Los Angeles\",\"tla\":\"QLA\",\"nonLocalizedCity\":\"Los Angeles\"},\"hotelInfo\":{\"hotelId\":\"13100360\",\"hotelName\":\"El Royale Gardens Near Universal Studios\",\"localizedHotelName\":\"El Royale Gardens Near Universal Studios\",\"hotelDestination\":\"Studio City\",\"hotelDestinationRegionID\":\"9758\",\"hotelLongDestination\":\"Studio City,CA,USA\",\"hotelStreetAddress\":\"11117 Ventura Blvd\",\"hotelCity\":\"Studio City\",\"hotelProvince\":\"CA\",\"hotelCountryCode\":\"USA\",\"hotelLatitude\":34.141384,\"hotelLongitude\":-118.372485,\"hotelStarRating\":\"2.5\",\"hotelGuestReviewRating\":4.74,\"hotelReviewTotal\":140,\"hotelImageUrl\":\"https://images.trvl-media.com/hotels/14000000/13110000/13100400/13100360/b066f4fe_t.jpg\",\"vipAccess\":false,\"isOfficialRating\":false},\"hotelPricingInfo\":{\"averagePriceValue\":142.35,\"totalPriceValue\":142.35,\"crossOutPriceValue\":365.0,\"originalPricePerNight\":365.0,\"currency\":\"USD\",\"percentSavings\":61.0,\"drr\":false},\"hotelUrls\":{\"hotelInfositeUrl\":\"https%3A%2F%2Fwww.expedia.com%2Fgo%2Fhotel%2Finfo%2F13100360%2F2018-10-20%2F2018-10-21\",\"hotelSearchResultUrl\":\"https%3A%2F%2Fwww.expedia.com%2Fgo%2Fhotel%2Fsearch%2FDestination%2F2018-10-20%2F2018-10-21%3FSearchType%3DDestination%26CityName%3DStudio+City%26RegionId%3D178280%26Selected%3D13100360\"}},{\"offerDateRange\":{\"travelStartDate\":[2018,7,4],\"travelEndDate\":[2018,7,8],\"lengthOfStay\":4},\"destination\":{\"regionID\":\"178293\",\"associatedMultiCityRegionId\":\"178293\",\"longName\":\"New York (and vicinity), New York, United States of America\",\"shortName\":\"New York\",\"country\":\"United States of America\",\"province\":\"New York\",\"city\":\"New York\",\"tla\":\"NYC\",\"nonLocalizedCity\":\"New York\"},\"hotelInfo\":{\"hotelId\":\"25906\",\"hotelName\":\"Salisbury Hotel\",\"localizedHotelName\":\"Salisbury Hotel\",\"hotelDestination\":\"New York\",\"hotelDestinationRegionID\":\"2621\",\"hotelLongDestination\":\"New York,NY,USA\",\"hotelStreetAddress\":\"123 W 57th St\",\"hotelCity\":\"New York\",\"hotelProvince\":\"NY\",\"hotelCountryCode\":\"USA\",\"hotelLatitude\":40.764841,\"hotelLongitude\":-73.978404,\"hotelStarRating\":\"3.0\",\"hotelGuestReviewRating\":4.09,\"hotelReviewTotal\":16746,\"hotelImageUrl\":\"https://images.trvl-media.com/hotels/1000000/30000/26000/25906/4be9c969_t.jpg\",\"vipAccess\":true,\"isOfficialRating\":false},\"hotelPricingInfo\":{\"averagePriceValue\":198.5,\"totalPriceValue\":794.0,\"crossOutPriceValue\":397.0,\"originalPricePerNight\":397.0,\"currency\":\"USD\",\"percentSavings\":50.0,\"drr\":false},\"hotelUrls\":{\"hotelInfositeUrl\":\"https%3A%2F%2Fwww.expedia.com%2Fgo%2Fhotel%2Finfo%2F25906%2F2018-07-04%2F2018-07-08\",\"hotelSearchResultUrl\":\"https%3A%2F%2Fwww.expedia.com%2Fgo%2Fhotel%2Fsearch%2FDestination%2F2018-07-04%2F2018-07-08%3FSearchType%3DDestination%26CityName%3DNew+York%26RegionId%3D178293%26Selected%3D25906\"}},{\"offerDateRange\":{\"travelStartDate\":[2018,5,6],\"travelEndDate\":[2018,5,11],\"lengthOfStay\":5},\"destination\":{\"regionID\":\"178276\",\"associatedMultiCityRegionId\":\"178276\",\"longName\":\"Las Vegas (and vicinity), Nevada, United States of America\",\"shortName\":\"Las Vegas\",\"country\":\"United States of America\",\"province\":\"Nevada\",\"city\":\"Las Vegas\",\"tla\":\"LAS\",\"nonLocalizedCity\":\"Las Vegas\"},\"hotelInfo\":{\"hotelId\":\"16291091\",\"hotelName\":\"Palms Place by Airpads\",\"localizedHotelName\":\"Palms Place by Airpads\",\"hotelDestination\":\"Las Vegas\",\"hotelDestinationRegionID\":\"2008\",\"hotelLongDestination\":\"Las Vegas,NV,USA\",\"hotelStreetAddress\":\"4381 W. Flamingo Road\",\"hotelCity\":\"Las Vegas\",\"hotelProvince\":\"NV\",\"hotelCountryCode\":\"USA\",\"hotelLatitude\":36.114491,\"hotelLongitude\":-115.195263,\"hotelStarRating\":\"4.0\",\"hotelGuestReviewRating\":3.3,\"hotelReviewTotal\":132,\"hotelImageUrl\":\"https://images.trvl-media.com/hotels/17000000/16300000/16291100/16291091/ab188c6d_t.jpg\",\"vipAccess\":false,\"isOfficialRating\":false},\"hotelPricingInfo\":{\"averagePriceValue\":59.95,\"totalPriceValue\":299.75,\"crossOutPriceValue\":109.0,\"originalPricePerNight\":109.0,\"currency\":\"USD\",\"percentSavings\":45.0,\"drr\":false},\"hotelUrls\":{\"hotelInfositeUrl\":\"https%3A%2F%2Fwww.expedia.com%2Fgo%2Fhotel%2Finfo%2F16291091%2F2018-05-06%2F2018-05-11\",\"hotelSearchResultUrl\":\"https%3A%2F%2Fwww.expedia.com%2Fgo%2Fhotel%2Fsearch%2FDestination%2F2018-05-06%2F2018-05-11%3FSearchType%3DDestination%26CityName%3DLas+Vegas%26RegionId%3D178276%26Selected%3D16291091\"}},{\"offerDateRange\":{\"travelStartDate\":[2018,5,21],\"travelEndDate\":[2018,5,23],\"lengthOfStay\":2},\"destination\":{\"regionID\":\"178248\",\"associatedMultiCityRegionId\":\"178248\",\"longName\":\"Chicago (and vicinity), Illinois, United States of America\",\"shortName\":\"Chicago\",\"country\":\"United States of America\",\"province\":\"Illinois\",\"city\":\"Chicago\",\"tla\":\"CHI\",\"nonLocalizedCity\":\"Chicago\"},\"hotelInfo\":{\"hotelId\":\"2327\",\"hotelName\":\"Red Roof Inn Chicago - Lansing\",\"localizedHotelName\":\"Red Roof Inn Chicago - Lansing\",\"hotelDestination\":\"Lansing\",\"hotelDestinationRegionID\":\"8534\",\"hotelLongDestination\":\"Lansing,IL,USA\",\"hotelStreetAddress\":\"2450 East 173rd Street\",\"hotelCity\":\"Lansing\",\"hotelProvince\":\"IL\",\"hotelCountryCode\":\"USA\",\"hotelLatitude\":41.582889,\"hotelLongitude\":-87.556489,\"hotelStarRating\":\"2.0\",\"hotelGuestReviewRating\":2.61,\"hotelReviewTotal\":978,\"hotelImageUrl\":\"https://images.trvl-media.com/hotels/1000000/10000/2400/2327/5b15f35d_t.jpg\",\"vipAccess\":false,\"isOfficialRating\":false},\"hotelPricingInfo\":{\"averagePriceValue\":38.99,\"totalPriceValue\":77.98,\"crossOutPriceValue\":64.99,\"originalPricePerNight\":64.99,\"currency\":\"USD\",\"percentSavings\":40.01,\"drr\":false},\"hotelUrls\":{\"hotelInfositeUrl\":\"https%3A%2F%2Fwww.expedia.com%2Fgo%2Fhotel%2Finfo%2F2327%2F2018-05-21%2F2018-05-23\",\"hotelSearchResultUrl\":\"https%3A%2F%2Fwww.expedia.com%2Fgo%2Fhotel%2Fsearch%2FDestination%2F2018-05-21%2F2018-05-23%3FSearchType%3DDestination%26CityName%3DLansing%26RegionId%3D178248%26Selected%3D2327\"}}]}}";
		InputStream hotelOffersInfoInputStream = new ByteArrayInputStream(fourOffersString.getBytes());
		HotelOffersService hotelOffersService = new HotelOffersService();
		List<HotelOffer> hotelOffersList = hotelOffersService.parseResponse(hotelOffersInfoInputStream);
		Assert.assertEquals(6, hotelOffersList.size());
	}

	@Test
	public void testSendServiceRequest_testWithNoFilters() throws Exception {
		
		Map<String, String> filters = new LinkedHashMap<>();
		HotelOffersService hotelOffersService = new HotelOffersService();
		URI serviceURI = hotelOffersService.composeURI(filters);
		InputStream responseStream = hotelOffersService.sendServiceRequest(serviceURI);
		Assert.assertTrue(responseStream.available() > 0);
	}

	@Test
	public void testGetHotelOffers_testWithEmptyFilters() throws Exception {

		Map<String, String> filters = new LinkedHashMap<>();
		HotelOffersService hotelOffersService = new HotelOffersService();
		Assert.assertTrue(!(hotelOffersService.getHotelOffers(filters).isEmpty()));
	}

	@Test
	public void testGetHotelOffers_testWithFilters() throws Exception {

		Map<String, String> filters = new LinkedHashMap<>();
		filters.put("destinationName", "Amman");
		HotelOffersService hotelOffersService = new HotelOffersService();
		Assert.assertTrue(!(hotelOffersService.getHotelOffers(filters).isEmpty()));
	}

}
