package com.api.tests;

import org.hamcrest.Matchers;
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
import com.github.fge.jsonschema.main.JsonValidator;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class CreateJobAPITest {
	
	
	
	
	@Test
	public void createJobAPITest() {
		Customer customer = new Customer("Praven", "R","8085536329", "", "praveen.raghu.raghu@gmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("980", "Peace", "Street1","Ganesh Mandir", "Limbodi","452020", "India", "M.P");
		CustomerProduct customerProduct = new CustomerProduct("2025-04-06T18:30:00.000Z","29002151778092", "29002151778092", "29002151778092", "2025-04-06T18:30:00.000Z", 1, 1);
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
