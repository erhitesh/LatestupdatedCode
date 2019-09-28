package com.restAssuredTestCases;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class AuthTC {

	@SuppressWarnings("unchecked")
	@Test
	public void getWeatherDetail() {

		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		RequestSpecification httpRequest = RestAssured.given();

		// Body Data
		JSONObject obj = new JSONObject();
		obj.put("username", "admin");
		obj.put("password", "password123");
		// Add Header
		httpRequest.header("Content-Type", "application/json");

		// Add jsonData to body
		httpRequest.body(obj.toJSONString());
		Response response = httpRequest.request(Method.POST, "/auth");
		System.out.println("Response " + response);
		String token = response.jsonPath().get("token");
		System.out.println("Auth Token  " + token);

	}
}
