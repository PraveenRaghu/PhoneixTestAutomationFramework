package com.api.services;

import static com.api.utils.SpecUtils.requestSpecWithAuth;
import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constant.Role;
import com.api.request.model.CreateJobPayload;
import com.api.utils.SpecUtils;

import io.restassured.response.Response;

public class JobService {

	private static final String CREATE_JOB_ENDPOINT ="/job/create";
	private static final String SEARCH_ENDPOINT = "/job/search";
	private static final Logger LOGGER = LogManager.getLogger(JobService.class);
	
	public Response createJOB(Role role, CreateJobPayload createJobPayload) {
		LOGGER.info("Making reuest to the {} for the role {}",CREATE_JOB_ENDPOINT,role);
	return	given()
		.spec(requestSpecWithAuth(role, createJobPayload))
		.when()
		.post(CREATE_JOB_ENDPOINT);
		
		
	}
	
	public Response search(Role role, Object payload) {
		LOGGER.info("Making request to the {} for the role {}",SEARCH_ENDPOINT,role);
		return given().spec(SpecUtils.requestSpecWithAuth(role, payload)).post(SEARCH_ENDPOINT);
		
		
	}
}
