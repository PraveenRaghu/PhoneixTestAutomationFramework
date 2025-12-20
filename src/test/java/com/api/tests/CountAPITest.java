package com.api.tests;

import static io.restassured.RestAssured.*;

import javax.annotation.MatchesPattern;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CountAPITest {
	@Test
	public void verifyCountAPIResponse() {
		
		given()
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.and()
		.header("Authorization",AuthTokenProvider.getToken(Role.FD) )
		.when()
		.get("/dashboard/count")
		.then()
		.log().all()
		.statusCode(200)
		.body("message",Matchers.equalTo("Success"))
		.time(Matchers.lessThan(1000L))
		.body("data", Matchers.notNullValue())
		.body("data.size()", Matchers.equalTo(3))
		.body("data.count", Matchers.everyItem(Matchers.greaterThanOrEqualTo(0)))
		.body("data.label", Matchers.everyItem(Matchers.not(Matchers.blankOrNullString())))
		.body("data.key",Matchers.containsInAnyOrder("pending_fst_assignment","pending_for_delivery","created_today"))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CountAPIReposeSchema-FD.json"));
	}
	@Test
	public void verifyCountAPIMissingToken() {
		given()
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.and()
		.log().uri()
		.log().method()
		.log().headers()
		.when()
		.get("/dashboard/count")
		.then()
		.log().all()
		.statusCode(401);
	}

}
