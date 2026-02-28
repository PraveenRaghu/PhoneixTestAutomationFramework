package com.api.tests;

import static com.api.utils.SpecUtils.responseSpec_OK;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import com.api.services.AuthServices;

public class LoginAPITest {
	private UserCredentials userCredentials;
	 private AuthServices authserivce;
	
	@BeforeMethod(description ="Create the payload for login api")
	public void setup() {
	 userCredentials = new UserCredentials("iamfd", "password");
	 
	 authserivce = new AuthServices();
	 
	
	}
	
	@Test(description="Verifying if login api is working for FD user ", groups = {"api","regression","smoke"})
	public void loginAPITest() throws IOException {
		authserivce.login(userCredentials)
	   
		.then()
			.spec(responseSpec_OK())
		.and()
			.body("message", equalTo("Success"))
			.body(matchesJsonSchemaInClasspath("response-schema/loginAPIResponseSchema.json"));
	}

}
