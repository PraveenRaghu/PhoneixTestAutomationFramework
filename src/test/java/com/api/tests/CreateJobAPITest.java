package com.api.tests;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import static com.api.utils.DateTimeUtil.*;
import com.api.utils.SpecUtils;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITest {
	
	
	
	
	@Test
	public void createJobAPITest() {
		Customer customer = new Customer("Praven", "R","8085536329", "", "praveen.raghu.raghu@gmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("980", "Peace", "Street1","Ganesh Mandir", "Limbodi","452020", "India", "M.P");
		CustomerProduct customerProduct = new CustomerProduct(getTimeWithDaysAgo(10),"69002151778092", "69002151778092", "69002151778092", getTimeWithDaysAgo(10), 1, 1);
		Problems problems = new Problems(1, "Battery Issue");
		List<Problems> problemList = new ArrayList<Problems>();
		problemList.add(problems);
		
		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 2, 1, customer, customerAddress, customerProduct, problemList);
		given()
		.spec(SpecUtils.requestSpecWithAuth(Role.FD, createJobPayload))
		.when()
		.post("/job/create")
		.then()
		.spec(SpecUtils.responseSpec_OK())
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CreateAPIresponseSchema.json"))
		.body("message", Matchers.equalTo("Job created successfully. "))
		.body("data.mst_service_location_id", Matchers.equalTo(1))
		.body("data.job_number", Matchers.startsWith("JOB_"));
		
	}

}
