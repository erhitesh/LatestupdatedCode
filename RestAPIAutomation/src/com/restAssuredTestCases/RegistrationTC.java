package com.restAssuredTestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.restAssuredAPI.LoginAuthorization;
import com.restAssuredAPI.RegistrationDuplicateResponse;
import com.restAssuredAPI.RegistrationFailureResponse;
import com.restAssuredAPI.RegistrationSuccessResponse;
import com.restAssuredAPI.TestBase;
import com.restAssuredData.Payload;
import com.restAssuredData.ResourcesTypes;
import com.restAssuredData.ResponseCode;
import com.restUtils.ExtentManager;

import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;


public class RegistrationTC extends TestBase {

	RegistrationSuccessResponse registrationSuccess;
	RegistrationDuplicateResponse duplicateRegistration;
	RegistrationFailureResponse failureRegistration;
	LoginAuthorization authorization;

	@Test
	public void testRegistration() {
		response = httpRequest.body(Payload.registrationDynamicData()).when().post(ResourcesTypes.registrationResources)
				.then().extract().response();

		ResponseBody<?> body = response.getBody();

		if (response.statusCode() == Integer.parseInt(ResponseCode.requestCreated)) {
			ExtentManager.logs(Status.INFO, "OPERATION_SUCCESS");
			registrationSuccess = body.as(RegistrationSuccessResponse.class);
			Assert.assertEquals("OPERATION_SUCCESS", registrationSuccess.SuccessCode);
			Assert.assertEquals("Operation completed successfully", registrationSuccess.Message);

		} else if (response.statusCode() == Integer.parseInt(ResponseCode.requestSuccessfull)) {
			ExtentManager.logs(Status.INFO, "User already exists");
			duplicateRegistration = body.as(RegistrationDuplicateResponse.class);
			Assert.assertEquals("User already exists", duplicateRegistration.FaultId);
			Assert.assertEquals("FAULT_USER_ALREADY_EXISTS", duplicateRegistration.fault);

		} else if (response.statusCode() == Integer.parseInt(ResponseCode.badRequest)) {
			ExtentManager.logs(Status.INFO, "Invalid post data, please correct the request");
			failureRegistration = body.as(RegistrationFailureResponse.class);
			Assert.assertEquals("Invalid post data, please correct the request", failureRegistration.FaultId);
			Assert.assertEquals("FAULT_INVALID_POST_REQUEST", failureRegistration.fault);

		} else if (response.statusCode() == Integer.parseInt(ResponseCode.methodNotAllowed)) {
			failureRegistration = body.as(RegistrationFailureResponse.class);
			Assert.assertEquals("Invalid post data, please correct the request", failureRegistration.FaultId);
			Assert.assertEquals("FAULT_INVALID_POST_REQUEST", failureRegistration.fault);
		}
	}

	@Test
	public void testAuthentication() {
		response = httpRequest.headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).auth()
				.basic("ToolsQA", "TestPassword").when().post().then().extract().response();

		authorization = response.getBody().as(LoginAuthorization.class);

		if (response.statusCode() == Integer.parseInt(ResponseCode.requestSuccessfull)) {
			ExtentManager.logs(Status.INFO, "Operation completed successfully");
			Assert.assertEquals("OPERATION_SUCCESS", authorization.FaultId);
			Assert.assertEquals("Operation completed successfully", authorization.Fault);
			Assert.assertEquals("Basic", authorization.AuthenticationType);

		} else if (response.statusCode() == Integer.parseInt(ResponseCode.unAuthorizedRequest)) {
			ExtentManager.logs(Status.INFO, "FAULT_USER_INVALID_USER_PASSWORD");
			Assert.assertEquals("FAULT_USER_INVALID_USER_PASSWORD", authorization.StatusID);
			Assert.assertEquals("Invalid or expired Authentication key provided", authorization.Status);
		}
	}
}
