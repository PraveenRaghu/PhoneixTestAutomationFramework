package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.database.DataBaseManager;
import com.database.model.JobHeadModel;

public class JobHeadDao {

	private static final String JOB_HEAD_QUERY = """

			Select * from tr_job_head where tr_customer_id = ?
			""";

	private JobHeadDao() {

	}

	public static JobHeadModel getJobHeadData(int tr_customer_id) {
		JobHeadModel jobHeadModel = null;
		try {
			Connection conn = DataBaseManager.getConnection();
			PreparedStatement ps = conn.prepareStatement(JOB_HEAD_QUERY);
			ps.setInt(1, tr_customer_id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				jobHeadModel = new JobHeadModel(rs.getInt("id"), rs.getString("job_number"), 
						rs.getString("tr_customer_id"), rs.getString("tr_customer_product_id"), 
						rs.getString("job_number"), rs.getString("job_number"), 
						rs.getString("job_number"),rs.getString("job_number"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jobHeadModel;
	}

}
