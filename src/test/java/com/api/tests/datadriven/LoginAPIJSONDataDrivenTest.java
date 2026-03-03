package com.api.tests.datadriven;

import static com.api.utils.SpecUtils.responseSpec_OK;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import com.api.services.AuthServices;
@Listeners(com.listeners.APITestListener.class)
public class LoginAPIJSONDataDrivenTest {
private AuthServices authServices;
	
	@BeforeMethod(description="Intializing Auth services")
	public void setup() {
		authServices = new AuthServices();
	}
	@Test(description = "Verifying if login api is working for FD user ", groups = { "api", "regression",
			"datadriven" }, dataProviderClass = com.dataproviders.DataProviderUtils.class, dataProvider = "LoginAPIJSONDataprovider")
	public void loginAPITest(UserCredentials userCredentials) {

		authServices.login(userCredentials). then().spec(responseSpec_OK()).and().body("message", equalTo("Success"))
				.body(matchesJsonSchemaInClasspath("response-schema/loginAPIResponseSchema.json"));
	}

}
