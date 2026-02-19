package com.database.dao;

import java.sql.SQLException;

import com.database.model.CustomerAddressDBModel;
import com.database.model.CustomerDBModel;
import com.database.model.CustomerProductDBModel;

public class CustomerDaoRunner {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		CustomerProductDBModel productModel= CustomerProductDao.getProductInfoFromDB(191966);
		
		System.out.println(productModel);

	}

}
