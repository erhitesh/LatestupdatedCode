package com.restUtils;

import org.testng.Assert;

import com.restAssuredData.ResponseCode;

import io.restassured.response.Response;


public class HelperMethod {

	public static void checkStatusIs200(Response response) {
		Assert.assertEquals(response.getStatusCode(), Integer.parseInt(ResponseCode.requestSuccessfull), "Status Code failed for '200'");
	}
	
	public static void checkStatusIs201(Response response) {
		Assert.assertEquals(response.getStatusCode(), Integer.parseInt(ResponseCode.requestCreated), "Status Code failed for '201'.");
	}
	
	public static void checkStatusIs400(Response response) {
		Assert.assertEquals(response.getStatusCode(), Integer.parseInt(ResponseCode.badRequest), "Status Code failed for '400'.");
	}
	
	public static void checkStatusIs401(Response response) {
		Assert.assertEquals(response.getStatusCode(), Integer.parseInt(ResponseCode.unAuthorizedRequest), "Status Code failed for '401'.");
	}

}
