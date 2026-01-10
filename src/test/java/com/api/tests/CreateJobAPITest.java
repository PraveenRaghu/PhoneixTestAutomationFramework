package com.api.tests;

import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.pojo.CreateJobPayload;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtils;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class CreateJobAPITest {
	
	
	
	
	@Test
	public void createJobAPITest() {
		Customer customer = new Customer("Praven", "R","8085536329", "", "praveen.raghu.raghu@gmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("980", "Peace", "Street1","Ganesh Mandir", "Limbodi","452020", "India", "M.P");
		CustomerProduct customerProduct = new CustomerProduct("2025-04-06T18:30:00.000Z","27002151778092", "27002151778092", "27002151778092", "2025-04-06T18:30:00.000Z", 1, 1);
		Problems problems = new Problems(1, "Battery Issue");
		Problems[] problemArray = new Problems[1];
		problemArray[0]= problems;
		
		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 2, 1, customer, customerAddress, customerProduct, problemArray);
		given()
		.spec(SpecUtils.requestSpecWithAuth(Role.FD, createJobPayload))
		.when()
		.post("/job/create")
		.then()
		.spec(SpecUtils.responseSpec_OK());
		
	}

}
