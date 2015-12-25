package com.leafsoft.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class JdbcUtil {
	private static Logger LOGGER = Logger.getLogger(JdbcUtil.class.getName());
	public DriverManagerDataSource getAccountsDataSource() {
		AppResources jdbc = new AppResources();
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(jdbc.getMysqlDriverClassName());
        dataSource.setUrl(jdbc.getAccountsConnectionUrl()+jdbc.getAccountsDatabase());
        dataSource.setUsername(jdbc.getAccountsUserName());
        dataSource.setPassword(jdbc.getAccountsPassword());
        return dataSource;
	}
	
	public static void cleanUp(DriverManagerDataSource datasource) {
        try {
            if (!datasource.getConnection().isClosed()) {
            	datasource.getConnection().close();
            }
        } catch (Exception e) {
        	LOGGER.log(Level.SEVERE,"Exception While cleanup:::"+e);
        }
    }
	
	public static void createDatabase(String dbname) {
		try {
			AppResources jdbc = new AppResources();
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        dataSource.setDriverClassName(jdbc.getMysqlDriverClassName());
	        dataSource.setUrl(jdbc.getAccountsConnectionUrl());
	        dataSource.setUsername(jdbc.getAccountsUserName());
	        dataSource.setPassword(jdbc.getAccountsPassword());
	        String sql = "CREATE DATABASE IF NOT EXISTS "+dbname;
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			jdbcTemplate.update(sql, new Object[] {});
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE,"Exception while create database"+e.getMessage(),e);
		}
	}
}
