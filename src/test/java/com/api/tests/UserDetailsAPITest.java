package com.api.tests;

import static com.api.constant.Role.FD;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import static com.api.utils.SpecUtils.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class UserDetailsAPITest {
	
	@Test(description="Verifing api response for user details endpoint", groups= {"api","regression", "smoke"})
	public void userDetailsAPITest() {
		
		
		
		given()
			.spec(requestSpecWithAuth(FD))
			
		.when()
			.get("userdetails")
		.then()
			.spec(responseSpec_OK())
		.and()
			.body(matchesJsonSchemaInClasspath("response-schema/userDetailsAPIResponseSchema.json"));	}

}
