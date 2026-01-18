package com.api.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import static com.api.utils.SpecUtils.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class LoginAPITest {
	private UserCredentials userCredentials;
	
	@BeforeMethod(description ="Create the payload for login api")
	public void setup() {
	 userCredentials = new UserCredentials("iamfd", "password");
	}
	
	@Test(description="Verifying if login api is working for FD user ", groups = {"api","regression","smoke"})
	public void loginAPITest() throws IOException {
		
		
		
		
		given()
			.spec(requestSpec(userCredentials))
			//.body(userCredentials)
		.when()
			.post("login")
		.then()
			.spec(responseSpec_OK())
		.and()
			.body("message", equalTo("Success"))
			.body(matchesJsonSchemaInClasspath("response-schema/loginAPIResponseSchema.json"));
	}

}
