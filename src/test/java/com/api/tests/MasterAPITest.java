package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;

import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

public class MasterAPITest {
	
	@Test
	public void masterAPITest() {
		
		given()
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.and()
		.header("Authorization",AuthTokenProvider.getToken(Role.FD))
		.contentType("")// Hence we need to pass content type explicitly for Post
		.log().all()
		.when()
		.post("master")// default Content-Type=application/x-www-form-urlencoded
		.then()
		.log().all()
		.statusCode(200)
		.time(Matchers.lessThan(1000l))
		.body("message", Matchers.equalTo("Success"))
		.body("data",Matchers.notNullValue())
		.body("data", Matchers.hasKey("mst_model"))
		.body("$",Matchers.hasKey("data"))
		.body("$",Matchers.hasKey("message"))
		.body("data.mst_oem.id", Matchers.everyItem(Matchers.notNullValue()))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/MasterAPIResponseSchema-FD.json"));
		
	}

}
