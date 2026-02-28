package com.api.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.request.model.SearchPayload;
import com.api.services.JobService;
import com.api.utils.SpecUtils;

public class SearchAPITest {
	
	private JobService jobService;
	private static final String JOB_NUMBER ="JOB_199964";
	private SearchPayload searchPayLoad;

	@BeforeMethod(description= "Intiating jobservice and search payload")
	public void setup() {
		jobService = new JobService();
		searchPayLoad = new SearchPayload(JOB_NUMBER);
	}
	
	@Test(description = "Verify Search API properly")
	public void searchAPITest() {
		
		jobService.search(Role.FD, searchPayLoad)
		.then()
		.spec(SpecUtils.responseSpec_OK());
		
	}
}
