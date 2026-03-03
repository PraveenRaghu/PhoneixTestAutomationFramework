package com.api.tests.datadriven;

import static com.api.utils.SpecUtils.responseSpec_OK;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.services.AuthServices;
import com.dataproviders.api.bean.UserBean;
@Listeners(com.listeners.APITestListener.class)
public class LoginAPIDataDrivenTest {
	private AuthServices authSerivce ;
	@BeforeMethod(description= "Intializing auth service")
	
	public void setup() {
		 authSerivce = new AuthServices();
	}
	
	@Test(description="Verifying if login api is working for FD user ", 
			groups = {"api","regression","datadriven"},
			dataProviderClass=com.dataproviders.DataProviderUtils.class,
			dataProvider="LoginAPIDataprovider"
					)
	public void loginAPITest(UserBean userbean)  {
		
		
		
		
		authSerivce.login(userbean)
		.then()
			.spec(responseSpec_OK())
		.and()
			.body("message", equalTo("Success"))
			.body(matchesJsonSchemaInClasspath("response-schema/loginAPIResponseSchema.json"));
	}

}
