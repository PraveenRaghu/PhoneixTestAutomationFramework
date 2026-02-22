package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.api.utils.ConfigManager;
import com.api.utils.EnvUtil;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataBaseManager {
	private static final String DB_URL = EnvUtil.getValue("DB_URL");
	private static final String DB_USER_NAME = EnvUtil.getValue("DB_USER_NAME");
	private static final String DB_PASSWORD = EnvUtil.getValue("DB_PASSWORD");
	private static HikariConfig hikariConfig;
	private volatile static HikariDataSource hikariDataSource;
	private static final int MAXIMUM_POOL_SIZE =Integer.parseInt(ConfigManager.getProperty("MAXIMUM_POOL_SIZE"));
	private static final int MINIMUM_IDLE =Integer.parseInt(ConfigManager.getProperty("MINIMUM_IDLE"));
	private static final int CONNECTION_TIMEOUT_IN_SEC =Integer.parseInt(ConfigManager.getProperty("CONNECTION_TIMEOUT_IN_SEC"));
	private static final int IDLE_TIMEOUT_IN_SEC =Integer.parseInt(ConfigManager.getProperty("IDLE_TIMEOUT_IN_SEC"));
	private static final int MAX_LIFE_IN_MIN =Integer.parseInt(ConfigManager.getProperty("MAX_LIFE_IN_MIN"));
	private static final String HIKARI_CP_POOL_NAME =ConfigManager.getProperty("HIKARI_CP_POOL_NAME");
	


	private DataBaseManager() {

	}

	private static void intialzePool() throws SQLException {

		if (hikariDataSource == null) {
			synchronized (DataBaseManager.class) {

				if (hikariDataSource == null) {

					hikariConfig = new HikariConfig();

					hikariConfig.setJdbcUrl(DB_URL);
					hikariConfig.setUsername(DB_USER_NAME);
					hikariConfig.setPassword(DB_PASSWORD);
					hikariConfig.setMaximumPoolSize(MAXIMUM_POOL_SIZE);
					hikariConfig.setMinimumIdle(MINIMUM_IDLE);
					hikariConfig.setConnectionTimeout(CONNECTION_TIMEOUT_IN_SEC *1000);
					hikariConfig.setIdleTimeout(IDLE_TIMEOUT_IN_SEC*1000);
					hikariConfig.setMaxLifetime(MAX_LIFE_IN_MIN *60*1000);
					hikariConfig.setPoolName(HIKARI_CP_POOL_NAME);
					
					hikariDataSource = new HikariDataSource(hikariConfig);
				}

			}

		}
	}
	
	public static Connection getConnection() throws SQLException {
		Connection connection = null;
		
		if(hikariDataSource == null) {
			intialzePool();
		}
		else if(hikariDataSource.isClosed()) {
			throw new SQLException("Hikari Data Source is Cloced");
		}
		
			connection = hikariDataSource.getConnection();
		
		
		return connection;
	}
}
