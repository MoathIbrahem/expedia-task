<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.moath.expedia.bean.HotelOffer"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Expedia Offers</title>

<style>
table tr td {
	padding: 5px 10px;
	color: #003366;
}

input[type=text] {
	margin: 5px;
	border: 1px solid #ccc;
	border-radius: 6px;
	box-sizing: border-box;
}

input[type=number] {
	margin: 5px;
	border: 1px solid #ccc;
	border-radius: 6px;
	box-sizing: border-box;
}

.grid-container {
	display: flex;
	direction: row;
	flex-wrap: wrap;
	justify-content: center;
}

.grid-cell {
	margin: 5px;
	border-radius: 10px;
	border-style: groove;
	background: silver;
	color: #003366;
	border-style: groove;
	width: 30rem;
}
</style>


</head>
<body>

	<form action="hotelOffers" method="get">
		<h1 style="text-align: center;font-weight: 400; text-decoration: underline;">Hotel Offers Search</h1>
		<table style="width: 700px; margin: 20px auto; border-collapse: collapse; border: 1px solid gray;">
			<tr>
				<td>Location :</td>
				<td>
					<input type="text" name="destinationName" value="${requestScope.filters.destinationName}" />
				</td>
			</tr>

			<tr>
				<td>Length Of Stay :</td>
				<td>
					<input type="text" name="lengthOfStay" value="${requestScope.filters.lengthOfStay}" />
				</td>
			</tr>

			<tr>
				<td>Trip Start Date :</td>
				<td>
					<input type="text" name="travelStartDate" value="${requestScope.filters.travelStartDate}" placeholder="YYYY-MM-DD" pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))" />
				</td>
				<td>Trip End Date :</td>
				<td>
					<input type="text" name="travelEndDate" value="${requestScope.filters.travelEndDate}" placeholder="YYYY-MM-DD" pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))" />
				</td>

			</tr>

			<tr>
				<td>Minimum Star Rating :</td>
				<td>
					<input type="text" name="minStarRating" value="${requestScope.filters.minStarRating}" />
				</td>
				<td>Maximum Star Rating :</td>
				<td>
					<input type="text" name="maxStarRating" value="${requestScope.filters.maxStarRating}" />
				</td>
			</tr>

			<tr>
				<td>Minimum Total Rate :</td>
				<td>
					<input type="text" name="minTotalRate" value="${requestScope.filters.minTotalRate}" />
				</td>
				<td>Maximum Total Rate :</td>
				<td>
					<input type="text" name="maxTotalRate" value="${requestScope.filters.maxTotalRate}" />
				</td>
			</tr>


			<tr>
				<td>Minimum Guest Rate :</td>
				<td>
					<input type="text" name="minGuestRate" value="${requestScope.filters.minGuestRate}" />
				</td>
				<td>Maximum Guest Rate :</td>
				<td>
					<input type="text" name="maxGuestRate" value="${requestScope.filters.maxGuestRate}" />
				</td>
			</tr>


			<tr>
				<td align="center" colspan="4">
					<input type="submit" value="Search" style="width: 50%; font-size: 15px; background-color: purple; padding: 14px 40px;">
				</td>
			</tr>

		</table>


	</form>

	<%
		List<HotelOffer> offers = (List<HotelOffer>) request.getAttribute("offers");
		if (offers != null) {
	%>
	<div class="grid-container">
		<%
			for (HotelOffer hotelOffer : offers) {
		%>

		<div class="grid-cell">

			<table>
				<tr>
					<td valign="top" style="text-align: center;">
						<img alt="<%=hotelOffer.getHotelName()%>" src="<%=hotelOffer.getHotelImageUrl()%>" />
						<br />
						<a style="color: #003366; white-space: nowrap;" href="#" onclick="window.open(decodeURIComponent('<%=hotelOffer.getHotelInfositeUrl()%>'))"> More Details</a>
					</td>
					<td>
						<div>
							<strong>Hotel Name:</strong>
							<%=hotelOffer.getHotelName()%>
						</div>
						<div>
							<strong>Original Price Per Night:</strong>
							<%=hotelOffer.getOriginalPricePerNight()%>
						</div>
						<div>
							<strong>Guest Review Rating:</strong>
							<%=hotelOffer.getHotelGuestReviewRating()%>/5.0
						</div>
						<div>
							<strong>Hotel Review Total:</strong>
							<%=hotelOffer.getHotelReviewTotal()%>
						</div>
						<div>
							<strong>Hotel Location:</strong>
							<%=hotelOffer.getCountry() + " - " + hotelOffer.getProvince()%>
						</div>
					</td>
				</tr>

			</table>
		</div>

		<%
			}
		%>
	</div>
	<%
		}
	%>
	<%
		if (offers != null && offers.isEmpty()) {
	%>
	<h3 style="text-align: center; color: #003366">No data found</h3>
	<%
		}
	%>

</body>
</html>
