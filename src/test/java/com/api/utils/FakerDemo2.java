package com.api.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.github.javafaker.Faker;

public class FakerDemo2 {
	 final static String COUNTRY = "India";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Faker faker = new Faker(new Locale("en-IND"));
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		String mobileNumber= faker.numerify("80########");
		String altMobileNumber= faker.numerify("80########");
		String customerEmailAddres = faker.internet().emailAddress();
		String altcustomerEmailAddres = faker.internet().emailAddress();
		
		Customer customer = new Customer(firstName, lastName, mobileNumber, altMobileNumber, customerEmailAddres, altcustomerEmailAddres);
		System.out.println(customer);
		
		String flatNumber= faker.numerify("###");
		String apartmentName = faker.address().streetName();
		String streetName = faker.address().streetName();
		String landmark = faker.address().streetName();
		String area = faker.address().streetName();
		String pincode= faker.numerify("4#####");
		String country = faker.address().country();
		String state = faker.address().state();
		
		
		CustomerAddress customerAddress = new CustomerAddress(flatNumber, apartmentName, streetName, landmark, area, pincode, COUNTRY, state);
		System.out.println(customerAddress);
		
		// creating Customer Product faker object
		String dop= DateTimeUtil.getTimeWithDaysAgo(10);
		String imeiSerialNumber =faker.numerify("###############");
		String popurl = faker.internet().url();
		
		CustomerProduct customerProduct = new CustomerProduct(dop, imeiSerialNumber, imeiSerialNumber, imeiSerialNumber, popurl, 1, 1);
		System.out.println(customerProduct);
		
		String fakeRemark = faker.lorem().sentence(5);
		Random random = new Random();
		int problemID = random.nextInt(26)+1;
		
		Problems problems = new Problems(problemID, fakeRemark);
		
		List<Problems> problemList = new ArrayList<Problems>();
		problemList.add(problems);
		
		CreateJobPayload createJob = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemList);
		System.out.println(createJob);
	}

}
