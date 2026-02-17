package com.database.dao;

import java.sql.SQLException;

import com.database.model.CustomerDBModel;

public class CustomerDaoRunner {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		CustomerDBModel customerDBData= CustomerDao.getCustomerInfo();
		
		System.out.println(customerDBData);

	}

}
