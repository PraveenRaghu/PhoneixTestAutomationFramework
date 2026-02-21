package com.database.dao;

import java.sql.SQLException;

import com.database.model.CustomerAddressDBModel;
import com.database.model.CustomerDBModel;
import com.database.model.CustomerProductDBModel;
import com.database.model.MapJobProblemModel;

public class CustomerDaoRunner {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		MapJobProblemModel problemModel= MapJobProblemDao.getProblemDetails(185514);
		
		System.out.println(problemModel);

	}

}
