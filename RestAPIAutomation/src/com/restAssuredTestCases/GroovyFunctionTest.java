package com.restAssuredTestCases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.restAssuredData.PropertyOperations;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.Matchers.*;


public class GroovyFunctionTest {
	
	public static RequestSpecification httpRequest;
	public static Response response;
	
	@BeforeClass
	public void setUp() {
		RestAssured.baseURI = PropertyOperations.getEnvironmentData("Groovy_Url");
		httpRequest = RestAssured.given();
	}
	
	@Test
	public void testXpathUsingAbsolute() {
		httpRequest.get("/10").then().body(hasXPath("/CUSTOMER/CITY[contains(text(),'Dallas')]"));
	}
	
	@Test
	public void testXpathUsingRelative() {
		httpRequest.get("/10").then().body(hasXPath("//*[contains(text(),'Dallas')]"));
	}
	
	@Test
	public void testXpath() {
		httpRequest.get("/10").then().body("CUSTOMER.ID", containsString("10"), "CUSTOMER.FIRSTNAME", equalTo("Sue")).log().all();
	}
	
	@Test
	public void testXpathUsingText() {
		httpRequest.get("/10").then().body("CUSTOMER.text()", equalTo("10SueFuller135 Upland Pl.Dallas"));
	}
}
