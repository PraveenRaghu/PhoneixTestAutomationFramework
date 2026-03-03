package com.api.tests.datadriven;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constant.Model;
import com.api.constant.OEM;
import com.api.constant.Platform;
import com.api.constant.Problem;
import com.api.constant.Product;
import com.api.constant.Role;
import com.api.constant.ServiceLocation;
import com.api.constant.Warranty_Status;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.services.JobService;

import static com.api.utils.DateTimeUtil.*;
import static com.api.utils.SpecUtils.*;

import io.restassured.module.jsv.JsonSchemaValidator;
@Listeners(com.listeners.APITestListener.class)
public class CreateJobAPIFakeDataDrivenTest {
	
private JobService jobService ;
	
	@BeforeMethod(description= " Intiating the Jobservice")
	public void setup() {
		jobService = new JobService();
	}

	@Test(description="Verifing create api for inwarranty flow", groups= {"api","regression", "datadriven","faker"},
	      dataProviderClass =com.dataproviders.DataProviderUtils.class,
	      dataProvider= "CreateJobAPIFakerAPIDataProvider"
			)
	public void createJobAPITest(CreateJobPayload createJobPayload) {
		
		jobService.createJOB(Role.FD, createJobPayload)
		.then()
		.spec(responseSpec_OK())
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CreateAPIresponseSchema.json"))
		.body("message", Matchers.equalTo("Job created successfully. "))
		.body("data.mst_service_location_id", Matchers.equalTo(1))
		.body("data.job_number", Matchers.startsWith("JOB_"));
		
	}

}
