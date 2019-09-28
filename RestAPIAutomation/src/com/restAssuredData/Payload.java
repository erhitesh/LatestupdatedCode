package com.restAssuredData;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.simple.JSONObject;

public class Payload {

	public static String getPostData() {
		String str = "{" + "\"city\":\"Hyderabad\"" + "}";

		return str;
	}

	public static int generateRandomData(int maxNumber) {
		int min = 1, max = maxNumber;
		return new Random().nextInt((max - min) + 1) + min;
	}

	public static String getRegistrationData() {
		String str = "{" + "\"FirstName\":\"Giserv\"," + "\"LastName\":\"Bhardwaj\","
				+ "\"UserName\":\"Er\"+RandomStringUtils.randomAlphanumeric(5)," + "\"Password\":\"fiserv@123\","
				+ "\"Email\":\"QATester\"+generateRandomData(10000)+\"@gmail.com\"" + "}";

		return str;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject registrationStaticData() {
		JSONObject object = new JSONObject();
		object.put("FirstName", "QA");
		object.put("LastName", "Tester");
		object.put("UserName", "QAAPI");
		object.put("Password", "qatester@123");
		object.put("Email", "QATester@gmail.com");

		return object;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject registrationDynamicData() {
		JSONObject object = new JSONObject();
		object.put("FirstName", RandomStringUtils.randomAlphabetic(5));
		object.put("LastName", RandomStringUtils.randomAlphabetic(5));
		object.put("UserName", "QA" + RandomStringUtils.randomAlphabetic(5));
		object.put("Password", "fiserv@123");
		object.put("Email",
				RandomStringUtils.randomAlphabetic(5) + RandomStringUtils.randomAlphabetic(5) + "@gmail.com");

		return object;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject registrationInvalidData() {
		JSONObject object = new JSONObject();
		object.put("FirstName", RandomStringUtils.randomAlphabetic(5));
		object.put("LastName", RandomStringUtils.randomAlphabetic(5));
		object.put("UserName", "QA" + RandomStringUtils.randomAlphabetic(5));
		object.put("Email", "QATester" + RandomStringUtils.randomAlphabetic(5));

		return object;
	}
}