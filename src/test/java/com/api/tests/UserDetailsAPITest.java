package com.api.tests;

import static com.api.constant.Role.FD;
import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.services.UserService;

import static com.api.utils.SpecUtils.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;
@Listeners(com.listeners.APITestListener.class)
public class UserDetailsAPITest {
	private UserService userService;
	
	@BeforeMethod(description= " Intializing User service")
	public void setup() {
		 userService = new UserService();
	}
	
	@Test(description="Verifing api response for user details endpoint", groups= {"api","regression", "smoke"})
	public void userDetailsAPITest() {
		
		
		
		userService.userDetails(FD)
		.then()
			.spec(responseSpec_OK())
		.and()
			.body(matchesJsonSchemaInClasspath("response-schema/userDetailsAPIResponseSchema.json"));	}

}
