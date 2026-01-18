package com.api.tests;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Role;
import static com.api.utils.SpecUtils.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class MasterAPITest {
	
	@Test(description="Verifing master api giving correct response", groups= {"api","regression", "smoke"})
	public void masterAPITest() {
		
		given()
		.spec(requestSpecWithAuth(Role.FD))
		.when()
		.post("master")// default Content-Type=application/x-www-form-urlencoded
		.then()
		.spec(responseSpec_OK())
		.body("message", Matchers.equalTo("Success"))
		.body("data",Matchers.notNullValue())
		.body("data", Matchers.hasKey("mst_model"))
		.body("$",Matchers.hasKey("data"))
		.body("$",Matchers.hasKey("message"))
		.body("data.mst_oem.id", Matchers.everyItem(Matchers.notNullValue()))
		.body(matchesJsonSchemaInClasspath("response-schema/MasterAPIResponseSchema-FD.json"));
		
	}
	@Test(description="Verifing master api giving correct response", groups= {"api","regression","negative", "smoke"})
	public void invalidMasterAPITest() {
		given()
		.spec(requestSpec())
		.log().all()
		.when()
		.post("master")// default Content-Type=application/x-www-form-urlencoded
		.then()
		.spec(responseSpec(401));
	}

}
