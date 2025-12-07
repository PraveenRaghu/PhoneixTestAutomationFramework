package com.api.tests;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;

import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.http.ContentType.*;

public class LoginAPITest {
	
	@Test
	public void loginAPITest() {
		
		UserCredentials userCredentials = new UserCredentials("iamfd", "password");
		
		given()
			.baseUri("http://64.227.160.186:9000/v1")
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
