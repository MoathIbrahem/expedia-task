package com.moath.expedia.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.client.utils.URIBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.moath.expedia.bean.HotelOffer;

/**
 * This class is a service class responsible of getting hotel offers from
 * Expedia Service.
 * 
 * @author Moath
 *
 */
public class HotelOffersService {

	static final String BASE_URL = "https://offersvc.expedia.com/offers/v2/getOffers?scenario=deal-finder&page=foo&uid=foo&productType=Hotel";
	static HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

	/**
	 * This method build the URL and send the request to return a list of hotel
	 * offers.
	 * 
	 * @param filters
	 * @return
	 * @throws ServiceException
	 */
	public List<HotelOffer> getHotelOffers(Map<String, String> filters) throws ServiceException {

		try {
			URI serviceURI = composeURI(filters);
			InputStream responseStream = sendServiceRequest(serviceURI);
			return parseResponse(responseStream);
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
			throw new ServiceException("Failed to get offers due to:" + e.getMessage(), e);
		}
	}

	/**
	 * This method takes a map of query parameters to build Expedia's service
	 * URL
	 * 
	 * @param filters
	 * @return
	 * @throws URISyntaxException
	 */
	URI composeURI(Map<String, String> filters) throws URISyntaxException {

		URIBuilder uriBuilder = new URIBuilder(BASE_URL);
		for (Entry<String, String> entry : filters.entrySet()) {
			uriBuilder.addParameter(entry.getKey(), entry.getValue());
		}
		return uriBuilder.build();
	}

	/**
	 * This method takes URI to send the request and return an input stream
	 * contains the offers.
	 * 
	 * @param serviceURI
	 * @return
	 * @throws IOException
	 */
	InputStream sendServiceRequest(URI serviceURI) throws IOException {

		HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory();
		HttpRequest request = requestFactory.buildGetRequest(new GenericUrl(serviceURI));
		return request.execute().getContent();
	}

	/**
	 * This method takes an input stream contains hotel offers information, and
	 * fill these information in hotel offer beans
	 * 
	 * @param responseStream
	 * @return A list of HotelOffer
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	List<HotelOffer> parseResponse(InputStream responseStream)
			throws JsonParseException, JsonMappingException, IOException {

		List<HotelOffer> hotels = new ArrayList<HotelOffer>();
		ObjectNode responseJson = new ObjectMapper().readValue(responseStream, ObjectNode.class);
		JsonNode offersNode = responseJson.get("offers").get("Hotel");
		if (offersNode == null || !(offersNode.elements().hasNext())) {
			return hotels;
		}

		for (JsonNode offer : offersNode) {
			HotelOffer hotelBean = new HotelOffer();
			hotelBean.setHotelName(offer.findPath("hotelInfo").findPath("hotelName").asText());
			hotelBean.setHotelImageUrl(offer.findPath("hotelInfo").findPath("hotelImageUrl").asText());
			hotelBean.setCountry(offer.findPath("hotelInfo").findPath("hotelCity").asText());
			hotelBean.setProvince(offer.findPath("hotelInfo").findPath("hotelProvince").asText());
			hotelBean.setHotelGuestReviewRating(
					offer.findPath("hotelInfo").findPath("hotelGuestReviewRating").asDouble());
			hotelBean.setHotelReviewTotal(offer.findPath("hotelInfo").findPath("hotelReviewTotal").asInt());
			hotelBean.setOriginalPricePerNight(
					offer.findPath("hotelPricingInfo").findPath("originalPricePerNight").asDouble());
			hotelBean.setHotelInfositeUrl(offer.findPath("hotelUrls").findPath("hotelInfositeUrl").asText());
			hotels.add(hotelBean);
		}
		return hotels;
	}
}
