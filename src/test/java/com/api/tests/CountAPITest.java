package com.api.tests;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static com.api.constant.Role .*;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtils;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CountAPITest {
	@Test
	public void verifyCountAPIResponse() {
		
		given()
		.spec(SpecUtils.requestSpecWithAuth(FD))
		.when()
		.get("/dashboard/count")
		.then()
		.spec(SpecUtils.responseSpec_OK())
		.body("message",Matchers.equalTo("Success"))
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
		.spec(SpecUtils.requestSpec())
		.when()
		.get("/dashboard/count")
		.then()
		.spec(SpecUtils.responseSpec(401));
		
	}

}
