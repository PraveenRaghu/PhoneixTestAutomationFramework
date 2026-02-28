package com.api.tests;

import static com.api.utils.SpecUtils.responseSpec_OK;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.services.JobService;
import com.api.utils.FakerDataGenerator;
import com.database.dao.CustomerAddressDao;
import com.database.dao.CustomerDao;
import com.database.dao.JobHeadDao;
import com.database.model.CustomerAddressDBModel;
import com.database.model.CustomerDBModel;
import com.database.model.JobHeadModel;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITestWithFakerData {
	private CreateJobPayload createJobPayload;
	private JobService jobService ;
	
	@BeforeMethod(description="Creating createapi requestpayload and intansiating JobService")
	public void setup() {
		
		jobService	= new JobService();
		 createJobPayload = FakerDataGenerator.generateFakeCreateJobData();
	}
	
	
	@Test(description="Verifing create api for inwarranty flow", groups= {"api","regression", "smoke"})
	public void createJobAPITest() {
		
		int customerID=jobService.createJOB(Role.FD, createJobPayload)
		.then()
		.spec(responseSpec_OK())
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CreateAPIresponseSchema.json"))
		.body("message", Matchers.equalTo("Job created successfully. "))
		.body("data.mst_service_location_id", Matchers.equalTo(1))
		.body("data.job_number", Matchers.startsWith("JOB_"))
		.extract().body().jsonPath().getInt("data.tr_customer_id");
		Customer expectedCustomerData=createJobPayload.customer();
		CustomerDBModel actualCustomerData= CustomerDao.getCustomerInfo(customerID);
		
		Assert.assertEquals(actualCustomerData.getFirst_name(), expectedCustomerData.first_name());
	    Assert.assertEquals(actualCustomerData.getLast_name(), expectedCustomerData.last_name());
	    Assert.assertEquals(actualCustomerData.getEmail_id(), expectedCustomerData.email_id());
	    Assert.assertEquals(actualCustomerData.getEmail_id_alt(), expectedCustomerData.email_id_alt());
	    Assert.assertEquals(actualCustomerData.getMobile_number(), expectedCustomerData.mobile_number());
	    Assert.assertEquals(actualCustomerData.getMobile_number_alt(), expectedCustomerData.mobile_number_alt());
	    
	    
	    CustomerAddressDBModel customerAddressFromDB = CustomerAddressDao.getCustomerAddressData(actualCustomerData.getTr_customer_address_id());
	    Assert.assertEquals(customerAddressFromDB.getFlat_number(), createJobPayload.customer_address().flat_number());
	    Assert.assertEquals(customerAddressFromDB.getApartment_name(), createJobPayload.customer_address().apartment_name());
	    Assert.assertEquals(customerAddressFromDB.getStreet_name(), createJobPayload.customer_address().street_name());
	    Assert.assertEquals(customerAddressFromDB.getLandmark(), createJobPayload.customer_address().landmark());
	    Assert.assertEquals(customerAddressFromDB.getArea(), createJobPayload.customer_address().area());
	    Assert.assertEquals(customerAddressFromDB.getPincode(), createJobPayload.customer_address().pincode());
	    Assert.assertEquals(customerAddressFromDB.getCountry(), createJobPayload.customer_address().country());
	    Assert.assertEquals(customerAddressFromDB.getState(), createJobPayload.customer_address().state());
	    
	    JobHeadModel jobheadModelFromDB = JobHeadDao.getJobHeadData(customerID); 
	    Assert.assertEquals(jobheadModelFromDB .getMst_oem_id(), createJobPayload.mst_oem_id());
	    Assert.assertEquals(jobheadModelFromDB .getMst_service_location_id(), createJobPayload.mst_service_location_id());
	    Assert.assertEquals(jobheadModelFromDB .getMst_platform_id(), createJobPayload.mst_platform_id());
	    
		
		
		
	}

}
