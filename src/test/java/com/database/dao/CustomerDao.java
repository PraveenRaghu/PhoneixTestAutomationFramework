package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.database.DataBaseManager;
import com.database.model.CustomerDBModel;

public class CustomerDao {
	private static final Logger LOGGER = LogManager.getLogger(CustomerDao.class);
	
	public static final String CUSTOMER_DETAIL_QUERY ="""
			select * from tr_customer tc where id= ?
			""";
	private CustomerDao() {
		
	}
	public static CustomerDBModel getCustomerInfo(int customerID)  {
		CustomerDBModel customerDBModel = null;
		try {
			LOGGER.info("Getting the connection from the DataBase Manager");
	Connection connection=	DataBaseManager.getConnection();
	PreparedStatement preparedStatment=connection.prepareStatement(CUSTOMER_DETAIL_QUERY);
	preparedStatment.setInt(1, customerID);
	LOGGER.info("Execution the Query {}",CUSTOMER_DETAIL_QUERY);
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
			LOGGER.error("Not able to convert result set to bean",e);
			System.err.println(e.getMessage());
		}
	
	return customerDBModel;
		
		
	}

}
