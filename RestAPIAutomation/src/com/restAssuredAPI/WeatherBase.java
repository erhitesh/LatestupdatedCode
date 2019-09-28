package com.restAssuredAPI;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import com.restAssuredData.PropertyOperations;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class WeatherBase {
	
	public static RequestSpecification httpRequest;
	public static Response response;
	public Logger logger;
	
	@BeforeClass
	public void setUp() {
		logger = Logger.getLogger("RestAssuredApiTesting");
		PropertyConfigurator.configure("log4j.properties");
		// Log Level
		logger.setLevel(Level.DEBUG);
		RestAssured.baseURI = PropertyOperations.getEnvironmentData("Weather_URL");
		httpRequest = RestAssured.given();
	}
}
