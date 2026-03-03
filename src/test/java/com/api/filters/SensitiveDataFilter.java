package com.api.filters;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class SensitiveDataFilter implements Filter {
	private static final Logger LOGGER = LogManager.getLogger(SensitiveDataFilter.class);

	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {
        LOGGER.info("***********REQUEST DETAILS***********");
        LOGGER.info("Base URI: {}",requestSpec.getURI());
        LOGGER.info("HTTP Method: {}",requestSpec.getMethod());
  
        redactHeader(requestSpec);
        
		redactPayload(requestSpec);
		Response response = ctx.next(requestSpec, responseSpec);
		LOGGER.info("***********RESPONSE DETAILS***********");
        LOGGER.info("STATUS: {}",response.getStatusLine());
        LOGGER.info("RESPONSE Time: {}ms",response.timeIn(TimeUnit.MILLISECONDS));
        LOGGER.info("RESPONSE HEADER: \n {}",response.getHeaders());
		redactResponseBody(response);

		return response;
	}

	private void redactHeader(FilterableRequestSpecification requestSpec) {
	List<Header> headerList=	requestSpec.getHeaders().asList();
	 for(Header h: headerList) {
		 if(h.getName().equalsIgnoreCase("Authorization")) {
			 LOGGER.info("Header {} : {}",h.getName(),"\"[REDEACTED]\"");
		 }
		 else {
			 LOGGER.info("Header {} : {}",h.getName(),h.getValue());
		 }
	 }
		
	}

	private void redactResponseBody(Response response) {
		String responseBody = response.asPrettyString();
		responseBody = responseBody.replaceAll("\"token\"\s*:\s*\"[^\"]+\"", "\"token\":\"[REDACTED]\"");
		LOGGER.info("Response Body:\n {}", responseBody);
	}

	public void redactPayload(FilterableRequestSpecification requestSpec) {
		if(requestSpec.getBody()!=null) {
		String requestpayload = requestSpec.getBody().toString();
		requestpayload = requestpayload.replaceAll("\"password\"\s*:\s*\"[^\"]+\"", "\"password\": \"[REDACTED]\"");
		LOGGER.info("Request Payload:\n{}", requestpayload);
		}
	}

}
