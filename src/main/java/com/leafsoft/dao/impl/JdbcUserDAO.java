package com.leafsoft.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.leafsoft.dao.UserDAO;
import com.leafsoft.model.LeafUser;
import com.leafsoft.util.DateAndTime;

	public class JdbcUserDAO implements UserDAO
	{
		private static final Logger LOGGER = Logger.getLogger(JdbcUserDAO.class.getName());
		
		private DataSource dataSource;
		
		public void setDataSource(DataSource dataSource) {
			this.dataSource = dataSource;
		}
		
		public int insert(LeafUser user){
			
			final SimpleDateFormat sdf = new SimpleDateFormat(DateAndTime.ORIGDATEFORMAT);
			
			final LeafUser finaluser = user;
			
			final String sql = "INSERT INTO LeafUsers " +
					"(username, password, enabled, email, dob) VALUES (?, ?, ?, ?, ?)";
			final String sql1 = "INSERT INTO UserRoles " +
					"(lid, rolename) VALUES (?, ?)";
			KeyHolder keyHolder = new GeneratedKeyHolder();
				JdbcTemplate insert = new JdbcTemplate(dataSource);
				insert.update(
					    new PreparedStatementCreator() {
					        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					            PreparedStatement ps =
					                connection.prepareStatement(sql, new String[] {"lid"});
					            ps.setString(1, finaluser.getUsername());
					            ps.setString(2, finaluser.getPassword());
					            ps.setString(3, "0");
					            ps.setString(4, finaluser.getEmail());
					            ps.setString(5, sdf.format(finaluser.getDob()));
					            return ps;
					        }
					    },
					    keyHolder);
				
			    insert.update(sql1,
				        new Object[] { keyHolder.getKey(), "ROLE_USER"});
			    return  keyHolder.getKey().intValue();
				
		}
		
		/*public User findByUserId(int custId){
			
			String sql = "SELECT * FROM CUSTOMER WHERE CUST_ID = ?";
			
			Connection conn = null;
			
			try {
				conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, custId);
				User customer = null;
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					customer = new User(
						rs.getInt("CUST_ID"),
						rs.getString("NAME"), 
						rs.getInt("Age")
					);
				}
				rs.close();
				ps.close();
				return customer;
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				if (conn != null) {
					try {
					conn.close();
					} catch (SQLException e) {}
				}
			}
			return null;
		}*/

		@Override
		public LeafUser findByCustomerId(int userId) {
			LeafUser user = null;
			try {
			String sql = "SELECT * FROM LeafUsers WHERE lid = ?";
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			user = jdbcTemplate.queryForObject(sql,new Object[]{userId},  new BeanPropertyRowMapper<LeafUser>(LeafUser.class));
			}catch(Exception e) {
				LOGGER.log(Level.INFO,"findByCustomerId():::"+userId+e.getMessage(),e);
			}
			return user;
		}

		@Override
		public LeafUser loadUserByUsername(String userName) {
			LeafUser user = null;
			try {
			String sql = "SELECT * FROM LeafUsers WHERE username = ?";
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			user = jdbcTemplate.queryForObject(sql,new Object[]{userName},  new BeanPropertyRowMapper<LeafUser>(LeafUser.class));
			}catch(Exception e) {
				LOGGER.log(Level.INFO,"loadUserByUsername():::"+userName+e.getMessage(),e);
			}
			return user;
		}

		@Override
		public boolean updateUserStatus(long lid, String status) {
			try {
			String sql = "UPDATE LeafUsers SET enabled = ? where lid = ?";
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			jdbcTemplate.update(sql, new Object[] {status, lid});
			return true;
			}
			catch(Exception e) {
				LOGGER.log(Level.INFO,"loadUserByUsername():::"+lid+e.getMessage());
			}
			return false;
		}
		
		public boolean updateUserPassword(long lid, String password) {
			try {
			String sql = "UPDATE LeafUsers SET password = ? where lid = ?";
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			jdbcTemplate.update(sql, new Object[] {password, lid});
			return true;
			}
			catch(Exception e) {
				LOGGER.log(Level.INFO,"updateUserPassword():::"+lid+e.getMessage(),e);
			}
			return false;
		}
		
		public boolean hasUser(String userName) {
			String sql = "SELECT * FROM LeafUsers WHERE username = ?";  
			try {
			Connection conn = dataSource.getConnection();
		    PreparedStatement ps = conn.prepareStatement(sql);
		    ps.setString(1,userName);
		    ResultSet rs = ps.executeQuery();
		    return rs.next();
			} catch(Exception e) {
				LOGGER.log(Level.INFO,"hasUser():::"+userName+e.getMessage(),e);
			}
			return false;
		    
		}
	}
