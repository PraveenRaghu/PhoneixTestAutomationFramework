package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.database.DataBaseManager;
import com.database.model.JobHeadModel;

public class JobHeadDao {
	private static final Logger LOGGER = LogManager.getLogger(JobHeadDao.class);
	private static final String JOB_HEAD_QUERY = """

			Select * from tr_job_head where tr_customer_id = ?
			""";

	private JobHeadDao() {

	}

	public static JobHeadModel getJobHeadData(int tr_customer_id) {
		JobHeadModel jobHeadModel = null;
		try {
			LOGGER.info("Getting the connection from the DataBase Manager");
			Connection conn = DataBaseManager.getConnection();
			PreparedStatement ps = conn.prepareStatement(JOB_HEAD_QUERY);
			ps.setInt(1, tr_customer_id);
			LOGGER.info("Execution the Query {}",JOB_HEAD_QUERY);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				jobHeadModel = new JobHeadModel(rs.getInt("id"), rs.getString("job_number"), 
						rs.getString("tr_customer_id"), rs.getString("tr_customer_product_id"), 
						rs.getString("job_number"), rs.getString("job_number"), 
						rs.getString("job_number"),rs.getString("job_number"));
			}

		} catch (SQLException e) {
			LOGGER.error("Not able to convert result set to bean",e);
			e.printStackTrace();
		}
		return jobHeadModel;
	}

}
