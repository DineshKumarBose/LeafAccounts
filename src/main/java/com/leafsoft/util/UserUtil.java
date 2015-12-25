package com.leafsoft.util;


import javax.xml.bind.DatatypeConverter;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.leafsoft.dao.impl.JdbcUserDAO;
import com.leafsoft.model.LeafUser;

import io.jsonwebtoken.Claims;

public class UserUtil {
	
	public static LeafUser getCurrentUser() {
		LeafUser leafuser = null;
		User User = null;
		Authentication a = SecurityContextHolder.getContext().getAuthentication();
		try{
			if(a.getPrincipal() != null) {
				User = (User) a.getPrincipal();
			} else {
				return null;
			}
		}catch(Exception e) {
			return null;
		}
		if(User!=null) {
			DriverManagerDataSource datasource = new JdbcUtil().getAccountsDataSource(); 
			JdbcUserDAO userDAO = new JdbcUserDAO();
			userDAO.setDataSource(datasource);
			leafuser = userDAO.loadUserByUsername(User.getUsername());
		}
		return leafuser;
	}
	
	public static String getUserToken(LeafUser user) {
		long ttlMillis = -1;
		return new TokenHandler(DatatypeConverter.parseBase64Binary(AppResources.getInstance().getTokeSecret())).createJWT(user.getLid()+"", user.getUsername(), user.getEmail(), ttlMillis);
		//return new TokenHandler(DatatypeConverter.parseBase64Binary(AppResources.getInstance().getTokeSecret())).createTokenForUser(user);
	}
	
	public static LeafUser getUserFromToken(String token) {
		//return new TokenHandler(DatatypeConverter.parseBase64Binary(AppResources.getInstance().getTokeSecret())).parseUserFromToken(token);
		Claims claims =  new TokenHandler(DatatypeConverter.parseBase64Binary(AppResources.getInstance().getTokeSecret())).parseJWT(token);
		String claimsId = claims.getId();
		DriverManagerDataSource datasource = new JdbcUtil().getAccountsDataSource();
        // Inject the datasource into the dao
    	JdbcUserDAO userDAO = new JdbcUserDAO();
    	userDAO.setDataSource(datasource);
		LeafUser user = userDAO.findByCustomerId(Integer.parseInt(claimsId));
		if(user.getUsername().equals(claims.getIssuer()) && user.getEmail().equals(claims.getSubject())) {
			return user;
		}
		else {
			return null;
		}
	}
	
}
