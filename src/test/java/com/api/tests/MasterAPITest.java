package com.api.tests;

import static com.api.utils.SpecUtils.requestSpec;
import static com.api.utils.SpecUtils.responseSpec_OK;
import static com.api.utils.SpecUtils.responseSpec_TEXT;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.constant.Role.*;
import com.api.services.MasterService;
@Listeners(com.listeners.APITestListener.class)
public class MasterAPITest {
	
private MasterService masterService;
	
	@BeforeMethod(description= " Intializing Master service")
	public void setup() {
		 masterService = new MasterService();
	}
	
	@Test(description="Verifing master api giving correct response", groups= {"api","regression", "smoke"})
	public void masterAPITest() {
		
		// default Content-Type=application/x-www-form-urlencoded
		masterService.master(Role.FD)
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
		.spec(responseSpec_TEXT(401));
	}

}
