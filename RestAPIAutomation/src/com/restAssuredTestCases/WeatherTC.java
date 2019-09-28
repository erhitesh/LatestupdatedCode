package com.restAssuredTestCases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.restAssuredAPI.TestBase;
import com.restAssuredData.DataFormatTypes;
import com.restAssuredData.ResourcesTypes;
import com.restAssuredData.ResponseBodyData;
import com.restUtils.ExtentManager;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

public class WeatherTC extends TestBase {

	@BeforeClass
	public void getCityInfo() {
		logger.info("************************* Started getCityInfo ************************");

		response = httpRequest.contentType(ContentType.JSON).accept(ContentType.JSON).param("city", "/Hyderabad").when()
				.get(ResourcesTypes.placeResources).then().extract().response();

		JsonPath path = (JsonPath) ResponseBodyData.getResponseData(response, DataFormatTypes.JSON);
		System.out.println("City Humidity ... " + path.get("Humidity"));
	}
	
	@Test(priority = 10)
	public void checkResponseBody() {
		logger.info("******************  Checking Response Body **************");
		String responseBody = response.getBody().asString();
		logger.info("Response Body ==>" + responseBody);
		Assert.assertTrue(responseBody != null);
		ExtentManager.logs(Status.PASS, "Response Body Check Successfully!");
	}

	@Test(priority = 20)
	public void checkStatusCode() {
		logger.info("******************  Checking Status Code **************");
		int statusCode = response.getStatusCode();
		logger.info("Status Code ==>" + statusCode);
		Assert.assertEquals(statusCode, 200, "Error Message both status code not matched...");
		ExtentManager.logs(Status.PASS, "Status Code 200");
	}

	@Test(priority = 30)
	public void checkResponseTime() {
		logger.info("******************  Checking Response Time **************");
		long responseTime = response.getTime();
		logger.info("Response Time is ==>" + responseTime);

		if (responseTime > 2000) {
			logger.warn("Response Time is greater than 2000");
		}

		Assert.assertTrue(responseTime < 2500);
	}

	@Test(priority = 40)
	public void checkStatusLine() {
		logger.info("******************  Checking Status Line **************");
		String statusLine = response.getStatusLine();
		logger.info("Status Line is ==>" + statusLine);

		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}

	@Test(priority = 50)
	public void checkContentType() {
		logger.info("******************  Checking Content Type **************");
		String contentType = response.getHeader("Content-Type");
		logger.info("Content Type is ==>" + contentType);

		Assert.assertEquals(contentType, "application/json");
	}

	@Test(priority = 60)
	public void checkContentEncoding() {
		logger.info("******************  Checking Content Encoding **************");
		String contentEncoding = response.getHeader("Content-Encoding");
		logger.info("Content Encoding is ==>" + contentEncoding);

		Assert.assertEquals(contentEncoding, "gzip");
	}

	@Test(priority = 70)
	public void checkServerTytpe() {
		logger.info("******************  Checking Server Type **************");
		String serverType = response.getHeader("Server");
		logger.info("Server type is  ==>" + serverType);

		Assert.assertEquals(serverType, "nginx");
	}
}
