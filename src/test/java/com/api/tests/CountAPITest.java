package com.api.tests;

import static com.api.constant.Role.FD;
import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.services.DashBoardService;
import com.api.services.UserService;

import static com.api.utils.SpecUtils.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class CountAPITest {
	
	
private DashBoardService dashBoardService;
	
	@BeforeMethod(description= " Intializing Dasboard service")
	public void setup() {
		dashBoardService = new DashBoardService();
	}
	@Test(description="Verifing Count api giving correct response", groups= {"api","regression", "smoke"})
	public void verifyCountAPIResponse() {
		
		dashBoardService.count(FD)
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
		dashBoardService.countWithNoAuth()
		.then()
		.spec(responseSpec_TEXT(401));
		
	}

}
