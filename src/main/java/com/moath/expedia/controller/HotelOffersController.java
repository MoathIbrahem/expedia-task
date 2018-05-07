package com.moath.expedia.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.moath.expedia.bean.HotelOffer;
import com.moath.expedia.service.HotelOffersService;
import com.moath.expedia.service.ServiceException;

/**
 * This class is a controller for hotel offers service
 * 
 * @author Moath
 *
 */
@WebServlet("/hotelOffers")
public class HotelOffersController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private HotelOffersService hotelOffersService = new HotelOffersService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			Map<String, String> offerFilters = getOfferFilters(request);
			request.setAttribute("filters", offerFilters);
			List<HotelOffer> offersList = hotelOffersService.getHotelOffers(offerFilters);
			request.setAttribute("offers", offersList);
		} catch (ServiceException e) {
			request.setAttribute("message", e.getMessage());
		}
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * This class gets the offers filters from the http request parameters
	 * 
	 * @param request
	 * @return
	 */
	private Map<String, String> getOfferFilters(HttpServletRequest request) {

		List<String> paramsList = new ArrayList<>();
		paramsList.add("destinationName");
		paramsList.add("travelStartDate");
		paramsList.add("travelEndDate");
		paramsList.add("lengthOfStay");
		paramsList.add("minStarRating");
		paramsList.add("maxStarRating");
		paramsList.add("minTotalRate");
		paramsList.add("maxTotalRate");
		paramsList.add("minGuestRate");
		paramsList.add("maxGuestRate");

		Map<String, String> offerFilters = new LinkedHashMap<>();
		for (String param : paramsList)
			if (request.getParameter(param) != null && !request.getParameter(param).isEmpty()) {
				offerFilters.put(param, request.getParameter(param));
			}

		return offerFilters;
	}

}
