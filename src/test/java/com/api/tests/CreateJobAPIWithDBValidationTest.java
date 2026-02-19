package com.api.tests;

import static com.api.utils.DateTimeUtil.getTimeWithDaysAgo;
import static com.api.utils.SpecUtils.requestSpecWithAuth;
import static com.api.utils.SpecUtils.responseSpec_OK;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
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
import com.database.dao.CustomerAddressDao;
import com.database.dao.CustomerDao;
import com.database.dao.CustomerProductDao;
import com.database.model.CustomerAddressDBModel;
import com.database.model.CustomerDBModel;
import com.database.model.CustomerProductDBModel;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class CreateJobAPIWithDBValidationTest {
	private CreateJobPayload createJobPayload;
	private Customer customer;
	private CustomerAddress customerAddress;
	private CustomerProduct customerProduct;
	
	@BeforeMethod(description="Creating createapi requestpayload")
	public void setup() {
		customer = new Customer("Praven", "R","8085536329", "", "praveen.raghu.raghu@gmail.com", "");
		customerAddress = new CustomerAddress("980", "Peace", "Street1","Ganesh Mandir", "Limbodi","452020", "India", "M.P");
		CustomerProduct customerProduct = new CustomerProduct(getTimeWithDaysAgo(10),"69992151778042", "69992151778042", "69992151778042", getTimeWithDaysAgo(10), 
				Product.NEXUS_2.getCode(), Model.NEXUS_2_BLUE.getCode());
		Problems problems = new Problems(Problem.SMARTPHONE_IS_RUNNING_SLOW.getCode(), "Battery Issue");
		List<Problems> problemList = new ArrayList<Problems>();
		problemList.add(problems);
		
		 createJobPayload = new CreateJobPayload(ServiceLocation.SERVICE_LOCATION_A.getCode(), Platform.FRONT_DESK.getCode(), Warranty_Status.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(), customer, customerAddress, customerProduct, problemList);
	}
	
	
	@Test(description="Verifing create api for inwarranty flow", groups= {"api","regression", "smoke"})
	public void createJobAPITest() {
		
Response response =	given()
		.spec(requestSpecWithAuth(Role.FD, createJobPayload))
		.when()
		.post("/job/create")
		.then()
		.spec(responseSpec_OK())
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CreateAPIresponseSchema.json"))
		.body("message", Matchers.equalTo("Job created successfully. "))
		.body("data.mst_service_location_id", Matchers.equalTo(1))
		.body("data.job_number", Matchers.startsWith("JOB_")).extract().response();
		int customerID= response.then().extract().body().jsonPath().getInt("data.tr_customer_id");
	    System.out.println(customerID);
	    CustomerDBModel customerDataFromDB=  CustomerDao.getCustomerInfo(customerID);
	    System.out.println(customerDataFromDB);
	    Assert.assertEquals(customer.first_name(), customerDataFromDB.getFirst_name());
	    Assert.assertEquals(customer.last_name(), customerDataFromDB.getLast_name());
	    Assert.assertEquals(customer.email_id(), customerDataFromDB.getEmail_id());
	    Assert.assertEquals(customer.email_id_alt(), customerDataFromDB.getEmail_id_alt());
	    Assert.assertEquals(customer.mobile_number(), customerDataFromDB.getMobile_number());
	    Assert.assertEquals(customer.mobile_number_alt(), customerDataFromDB.getMobile_number_alt());
	    
	    
	    CustomerAddressDBModel customerAddressFromDB = CustomerAddressDao.getCustomerAddressData(customerDataFromDB.getTr_customer_address_id());
	    Assert.assertEquals(customerAddressFromDB.getFlat_number(), customerAddress.flat_number());
	    Assert.assertEquals(customerAddressFromDB.getApartment_name(), customerAddress.apartment_name());
	    Assert.assertEquals(customerAddressFromDB.getStreet_name(), customerAddress.street_name());
	    Assert.assertEquals(customerAddressFromDB.getLandmark(), customerAddress.landmark());
	    Assert.assertEquals(customerAddressFromDB.getArea(), customerAddress.area());
	    Assert.assertEquals(customerAddressFromDB.getPincode(), customerAddress.pincode());
	    Assert.assertEquals(customerAddressFromDB.getCountry(), customerAddress.country());
	    Assert.assertEquals(customerAddressFromDB.getState(), customerAddress.state());
	    
	    int productID= response.then().extract().body().jsonPath().getInt("data.tr_customer_product_id");
	    CustomerProductDBModel customerProductDBData = CustomerProductDao.getProductInfoFromDB(productID);
	    Assert.assertEquals(customerProductDBData.getImei1(), customerProduct.imei1());
	    Assert.assertEquals(customerProductDBData.getImei2(), customerProduct.imei2());
	    Assert.assertEquals(customerProductDBData.getDop(), customerProduct.dop());
	    Assert.assertEquals(customerProductDBData.getPopurl(), customerProduct.popurl());
	    Assert.assertEquals(customerProductDBData.getSerial_number(), customerProduct.serial_number());
	    
		
	}

}
