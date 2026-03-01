package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.utils.ConfigManager;
import com.api.utils.JSONReaderUtil;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataBaseManager {
	private static final Logger LOGGER = LogManager.getLogger(DataBaseManager.class);
	private static final String DB_URL = ConfigManager.getProperty("DB_URL");
	private static final String DB_USER_NAME = ConfigManager.getProperty("DB_USER_NAME");
	private static final String DB_PASSWORD = ConfigManager.getProperty("DB_PASSWORD");
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
			
			LOGGER.warn("Dataconnection is not available creating HikariDataSource");
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
					
					LOGGER.info("Hikari Data source is created");
				}

			}

		}
	}
	
	public static Connection getConnection() throws SQLException {
		Connection connection = null;
		
		if(hikariDataSource == null) {
			LOGGER.info("Intializing the Database connection using HikariCP ");
			intialzePool();
		}
		else if(hikariDataSource.isClosed()) {
			LOGGER.error("Hikari Data Source is Cloced");
			throw new SQLException("Hikari Data Source is Cloced");
		}
		
			connection = hikariDataSource.getConnection();
		
		
		return connection;
	}
}
