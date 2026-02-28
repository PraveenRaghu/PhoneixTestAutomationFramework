package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.request.model.Details;
import com.api.services.DashBoardService;
import com.api.utils.SpecUtils;

public class DetailsAPITest {
	
	private DashBoardService dashboardService;
	private Details detailsPayload;
	@BeforeMethod(description =" Intiating dashboard and details payload")
	public void setup() {
		dashboardService = new DashBoardService();
		detailsPayload = new Details("created_today");
	}
	
	@Test(description ="Verify Details Api working properly")
	public void detailAPITest() {
		dashboardService.details(Role.FD, detailsPayload)
		.then()
		.spec(SpecUtils.responseSpec_OK())
		.body("message",Matchers.equalTo("Success"));
	}

}
