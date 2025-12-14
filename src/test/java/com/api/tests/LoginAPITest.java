package com.api.tests;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;
import static com.api.utils.ConfigManager.*;

import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {
	
	@Test
	public void loginAPITest() throws IOException {
		
		
		UserCredentials userCredentials = new UserCredentials("iamfd", "password");
		
		given()
			.baseUri(getProperty("BASE_URI"))
		.and()
			.contentType(JSON)
			.accept(JSON)
		.and()
			.body(userCredentials)
		.and()
			.log().uri()
			.log().body()
			.log().headers()
			.log().method()
		.when()
			.post("login")
		.then()
			.log().all()
			.statusCode(200)
			.time(lessThan(1500L))
		.and()
			.body("message", equalTo("Success"))
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/loginAPIResponseSchema.json"));
	}

}
