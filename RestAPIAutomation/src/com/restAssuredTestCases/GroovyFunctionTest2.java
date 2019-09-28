package com.restAssuredTestCases;

import java.util.List;

import org.testng.annotations.Test;

import com.restAssuredAPI.Photo;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class GroovyFunctionTest2 {

	public static RequestSpecification httpRequest;
	public static Response response;

//	@BeforeClass
//	public void setUp() {
//		RestAssured.baseURI = PropertyOperations.getEnvironmentData("Group_Kt_Url");
//		httpRequest = RestAssured.given();
//	}
//
//	@Test
//	public void testWithoutRoot() {
//		httpRequest.get().then().body("RestResponse.result.name", is("Italy"))
//				.body("RestResponse.result.alpha2_code", is("IT")).body("RestResponse.result.alpha3_code", is("ITA"));
//	}
//
//	@Test
//	public void testWithRoot() {
//		httpRequest.get().then().root("RestResponse.result").body("name", is("Italy")).body("alpha2_code", is("IT"))
//				.body("alpha3_code", is("ITA"));
//	}
//	
//	@Test
//	public void testDetachRoot() {
//		httpRequest.get().then().root("RestResponse.result").body("name", is("Italy")).body("alpha2_code", is("IT"))
//				.detachRoot("result").body("result.alpha3_code", is("ITA"));
//	}
//	
//	@Test
//	public void testGetResponseAsString() {
//		String responseAsString = httpRequest.get().asString();
//		System.out.println("Response As String:\n\n" + responseAsString);
//	}
//	
//	@Test
//	public void testGetResponseAsInputStream() throws IOException {
//		InputStream responseAsInputStream = httpRequest.get().asInputStream();
//		System.out.println("Stream Length " + responseAsInputStream.toString().length());
//		responseAsInputStream.close();
//	}
//	
//	@Test
//	public void testGetResponseAsByteArray() {
//		byte [] byteArr = httpRequest.get().asByteArray();
//		System.out.println("Byte Array Length " + byteArr.length);
//	}
//	
//	@Test
//	public void testExtractDetailUsinXpath() {
//		RestAssured.baseURI = "http://jsonplaceholder.typicode.com/photos";
//		httpRequest = RestAssured.given();
//		
//		String href = httpRequest.get("/1").then().contentType(ContentType.JSON).body("albumId", equalTo(1)).extract().path("url");
//		System.out.println("Href....." + href);
//	}
//	
//	@Test
//	public void testExtractDetailInOneLine() {
//		RestAssured.baseURI = "http://jsonplaceholder.typicode.com/photos";
//		httpRequest = RestAssured.given();
//		
//		String thumbnailUrl = httpRequest.get("/1").path("thumbnailUrl");
//		System.out.println("Fetched thumbnailUrl...." + thumbnailUrl);
//		
//		//type 2
//		String thumbnailUrl2 = httpRequest.get("/1").andReturn().jsonPath().getString("thumbnailUrl");
//		System.out.println("Fetched thumbnailUrl...." + thumbnailUrl2);
//	}

	@Test
	public void testFetchValueAsList() {
		RestAssured.baseURI = "http://jsonplaceholder.typicode.com/photos";
		httpRequest = RestAssured.given();

		List<String> responseList = httpRequest.get().then().extract().body().jsonPath().get("title");

		for (String string : responseList) {
			System.out.println("! URL " + string);
		}
	}

	@Test
	public void testFetchValueAsClass() {
		RestAssured.baseURI = "http://jsonplaceholder.typicode.com/photos";
		httpRequest = RestAssured.given();

		List<Photo> responseList = httpRequest.get().then().extract().body().jsonPath().getList(".", Photo.class);
		System.out.println("Url size ... " + responseList.size());
		for (Photo photo : responseList) {
			System.out.println("AlbumbId " + photo.albumId + " ! Id " + photo.id + " ! Title " + photo.title + " ! Url "
					+ photo.url + " ! AlbumbId " + photo.albumId);
		}
	}

}
