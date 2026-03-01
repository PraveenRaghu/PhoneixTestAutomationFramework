package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.database.DataBaseManager;
import com.database.model.CustomerProductDBModel;

public class CustomerProductDao {
	private static final Logger LOGGER = LogManager.getLogger(CustomerProductDao.class);
	private static final String  PRODUCT_QUERY= """
			
			select * from tr_customer_product where id = ?
			""";
	
	public static CustomerProductDBModel getProductInfoFromDB(int tr_customer_product_id) {
		CustomerProductDBModel customerProductModel = null;
		try {
			LOGGER.info("Getting the connection from the DataBase Manager");
		Connection connection = DataBaseManager.getConnection();
		PreparedStatement ps= connection.prepareStatement(PRODUCT_QUERY);
		ps.setInt(1, tr_customer_product_id);
		LOGGER.info("Execution the Query {}",PRODUCT_QUERY);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			customerProductModel = new CustomerProductDBModel(rs.getInt("id"), 
					rs.getInt("tr_customer_id"), rs.getInt("mst_model_id"), 
					rs.getString("dop"), rs.getString("popurl"), rs.getString("imei2"), 
					rs.getString("imei1"), rs.getString("serial_number"));
		}
		
		}
		catch(SQLException e) {
			LOGGER.error("Not able to convert result set to bean",e);
			System.err.println(e.getMessage());
		}
		
		return customerProductModel;
		
	}

}
