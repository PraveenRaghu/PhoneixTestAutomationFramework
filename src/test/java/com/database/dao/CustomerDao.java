package com.database.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.database.DataBaseManager;
import com.database.model.CustomerDBModel;

public class CustomerDao {
	
	
	public static final String CUSTOMER_DETAIL_QUERY ="""
			select * from tr_customer tc where id= 188668
			""";
	
	public static CustomerDBModel getCustomerInfo() throws SQLException {
		
	Connection connection=	DataBaseManager.getConnection();
	Statement statment=connection.createStatement();
	ResultSet resultSet=statment.executeQuery(CUSTOMER_DETAIL_QUERY);
	CustomerDBModel customerDBModel = null;
	
	while(resultSet.next()) {
		System.out.println(resultSet.getString("first_name")); 
		System.out.println(resultSet.getString("email_id")); 
		
		 customerDBModel = new CustomerDBModel(resultSet.getString("first_name"),
				resultSet.getString("last_name"), resultSet.getString("mobile_number"), 
				resultSet.getString("mobile_number_alt"), resultSet.getString("email_id"), 
				resultSet.getString("email_id_alt"));
	}
	
	return customerDBModel;
		
		
	}

}
