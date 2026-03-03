package com.api.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class SensitiveDataFilter implements Filter {
private static final Logger LOGGER = LogManager.getLogger(SensitiveDataFilter.class);
	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {


redactPayload(requestSpec);
Response response=ctx.next(requestSpec, responseSpec);
redactResponseBody(response);

		return response;
	}	
	
	public void redactResponseBody(Response response) {
		String responseBody=response.asPrettyString();
	responseBody=	responseBody.replaceAll("\"token\"\s*:\s*\"[^\"]+\"","\"token\":\"[REDACTED]\"");
		LOGGER.info("Response Body:{}",responseBody);
	}

	public void redactPayload(FilterableRequestSpecification requestSpec) {
		String requestpayload=requestSpec.getBody().toString();
	requestpayload=	requestpayload.replaceAll("\"password\"\s*:\s*\"[^\"]+\"", "\"password\": \"[REDACTED]\"");
	LOGGER.info("Request Payload:{}",requestpayload);
	}

}
