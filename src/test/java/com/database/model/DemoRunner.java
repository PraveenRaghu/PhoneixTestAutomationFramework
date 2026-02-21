package com.database.model;

import com.database.dao.JobHeadDao;

public class DemoRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//CustomerDBModel customer = new CustomerDBModel("Praveen", "R","8085536329","","test@test.com", "");
		
		//System.out.println(customer);
		
		JobHeadModel headModel = JobHeadDao.getJobHeadData(193013); 
		System.out.println( headModel);
		
		

	}

}
