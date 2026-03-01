package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.database.DataBaseManager;
import com.database.model.CustomerAddressDBModel;

public class CustomerAddressDao {
	private static final Logger LOGGER = LogManager.getLogger(CustomerAddressDao.class);
	private static final String CUSTOMER_ADDRESS_QUERY = """
						select id,
							flat_number,
							apartment_name,
							street_name,
							landmark,
							area,
							pincode,
							country,
							state

						from tr_customer_address where id =?
						""";
	
	private CustomerAddressDao() {
		
	}
	public static CustomerAddressDBModel getCustomerAddressData(int customerAddressId ) {
		CustomerAddressDBModel customerAddressDBModel = null;
		try {
			LOGGER.info("Getting the connection from the DataBase Manager");
			Connection connection=DataBaseManager.getConnection();
			PreparedStatement ps=connection.prepareStatement(CUSTOMER_ADDRESS_QUERY);
			ps.setInt(1,customerAddressId );
			ResultSet rs=ps.executeQuery();
			LOGGER.info("Execution the Query {}",CUSTOMER_ADDRESS_QUERY);
			while(rs.next()) {
				customerAddressDBModel = new CustomerAddressDBModel(rs.getInt("id"), rs.getString("flat_number"), rs.getString("apartment_name"), 
						rs.getString("street_name"), rs.getString("landmark"),rs.getString("area"), rs.getString("pincode"), rs.getString("country"), rs.getString("state"));
				        
			}
		} catch (SQLException e) {
			LOGGER.error("Not able to convert result set to bean",e);
			e.printStackTrace();
		}
		
		return customerAddressDBModel;
		
	}
}
