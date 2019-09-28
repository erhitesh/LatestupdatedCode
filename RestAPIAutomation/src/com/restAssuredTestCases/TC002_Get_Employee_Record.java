package com.restAssuredTestCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.restAssuredAPI.TestBase;
import com.restAssuredData.PropertyOperations;

import io.restassured.http.Method;

/**
 * @author Hitesh Bhardwaj
 *
 */
public class TC002_Get_Employee_Record extends TestBase {

	@BeforeClass
	public void getSingleEmployee() {
		logger.info("************************* Started TC002_Get_Single_Employee ************************");

		empId = PropertyOperations.getPropertyValue("Configuration/Employee", "empId");
		response = httpRequest.request(Method.GET, "/employee/" + empId);	
	}

	@Test(priority = 10)
	public void checkResponseBody() {
		logger.info("******************  Checking Response Body **************");
		String responseBody = response.getBody().asString();
		logger.info("Response Body ==>" + responseBody);
		Assert.assertTrue(responseBody != null);
	}

	@Test(priority = 20)
	public void checkStatusCode() {
		logger.info("******************  Checking Status Code **************");
		int statusCode = response.getStatusCode();
		logger.info("Status Code ==>" + statusCode);
		Assert.assertEquals(statusCode, 200, "Error Message both status code not matched...");
	}

	@Test(priority = 30)
	public void checkResponseTime() {
		logger.info("******************  Checking Response Time **************");
		long responseTime = response.getTime();
		logger.info("Response Time is ==>" + responseTime);

		if (responseTime > 2000) {
			logger.warn("Response Time is greater than 2000");
		}

		Assert.assertTrue(responseTime > 3000);
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

		Assert.assertEquals(contentType, "text/html; charset=UTF-8");
	}

	@Test(priority = 70)
	public void checkServerTytpe() {
		logger.info("******************  Checking Server Type **************");
		String serverType = response.getHeader("Server");
		logger.info("Server type is  ==>" + serverType);

		Assert.assertEquals(serverType, "nginx/1.14.1");
	}

	@Test(priority = 100)
	public void checkContentLength() {
		logger.info("******************  Checking Content Length **************");
		String contentLength = response.getHeader("Content-Length");
		logger.info("Content Length is ==>" + contentLength);

		if (Integer.parseInt(contentLength) == 5) {
			logger.warn("Content Length is less than 5");
		}

		Assert.assertTrue(Integer.parseInt(contentLength) == 5);
	}

	@AfterClass
	public void tearDown() {
		logger.info("************************* Finished TC001_Get_All_Employees **********************");
	}
}
