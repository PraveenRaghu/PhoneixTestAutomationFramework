package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtils;

import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

public class MasterAPITest {
	
	@Test
	public void masterAPITest() {
		
		given()
		.spec(SpecUtils.requestSpecWithAuth(Role.FD))
		.when()
		.post("master")// default Content-Type=application/x-www-form-urlencoded
		.then()
		.spec(SpecUtils.responseSpec_OK())
		.body("message", Matchers.equalTo("Success"))
		.body("data",Matchers.notNullValue())
		.body("data", Matchers.hasKey("mst_model"))
		.body("$",Matchers.hasKey("data"))
		.body("$",Matchers.hasKey("message"))
		.body("data.mst_oem.id", Matchers.everyItem(Matchers.notNullValue()))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/MasterAPIResponseSchema-FD.json"));
		
	}

}
