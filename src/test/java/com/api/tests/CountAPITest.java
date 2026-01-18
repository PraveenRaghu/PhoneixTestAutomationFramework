package com.api.tests;

import static com.api.constant.Role.FD;
import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static com.api.utils.SpecUtils.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class CountAPITest {
	@Test(description="Verifing Count api giving correct response", groups= {"api","regression", "smoke"})
	public void verifyCountAPIResponse() {
		
		given()
		.spec(requestSpecWithAuth(FD))
		.when()
		.get("/dashboard/count")
		.then()
		.spec(responseSpec_OK())
		.body("message",Matchers.equalTo("Success"))
		.body("data", Matchers.notNullValue())
		.body("data.size()", Matchers.equalTo(3))
		.body("data.count", Matchers.everyItem(Matchers.greaterThanOrEqualTo(0)))
		.body("data.label", Matchers.everyItem(Matchers.not(Matchers.blankOrNullString())))
		.body("data.key",Matchers.containsInAnyOrder("pending_fst_assignment","pending_for_delivery","created_today"))
		.body(matchesJsonSchemaInClasspath("response-schema/CountAPIReposeSchema-FD.json"));
	}
	@Test(description="Verifing count api giving correct response when token is missing", groups= {"api","regression", "smoke"})
	public void verifyCountAPIMissingToken() {
		given()
		.spec(requestSpec())
		.when()
		.get("/dashboard/count")
		.then()
		.spec(responseSpec_TEXT(401));
		
	}

}
