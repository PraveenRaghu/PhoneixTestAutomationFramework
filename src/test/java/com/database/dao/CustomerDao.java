package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.database.DataBaseManager;
import com.database.model.CustomerDBModel;

public class CustomerDao {
	
	
	public static final String CUSTOMER_DETAIL_QUERY ="""
			select * from tr_customer tc where id= ?
			""";
	private CustomerDao() {
		
	}
	public static CustomerDBModel getCustomerInfo(int customerID)  {
		CustomerDBModel customerDBModel = null;
		try {
	Connection connection=	DataBaseManager.getConnection();
	PreparedStatement preparedStatment=connection.prepareStatement(CUSTOMER_DETAIL_QUERY);
	preparedStatment.setInt(1, customerID);
	ResultSet resultSet=preparedStatment.executeQuery();
	
	
	while(resultSet.next()) {
		System.out.println(resultSet.getString("first_name")); 
		System.out.println(resultSet.getString("email_id")); 
		
		 customerDBModel = new CustomerDBModel(
				 resultSet.getInt("id"),
				 resultSet.getString("first_name"),
				resultSet.getString("last_name"), resultSet.getString("mobile_number"), 
				resultSet.getString("mobile_number_alt"), resultSet.getString("email_id"), 
				resultSet.getString("email_id_alt"),
				 resultSet.getInt("tr_customer_address_id"));
	}
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
		}
	
	return customerDBModel;
		
		
	}

}
