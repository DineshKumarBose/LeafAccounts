package com.leafsoft.web.controller;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.leafsoft.dao.impl.JdbcUserDAO;
import com.leafsoft.mail.SendMail;
import com.leafsoft.model.LeafUser;
import com.leafsoft.util.JdbcUtil;
import com.leafsoft.util.UserUtil;
 
@Controller
public class RegisterController {
	private static final Logger LOGGER = Logger.getLogger(RegisterController.class.getName());
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String viewRegistration(Map<String, Object> model) {
        LeafUser userForm = new LeafUser();    
        model.put("userForm", userForm);
        return "Registration";
    }
     
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView processRegistration(@ModelAttribute("userForm") LeafUser user,
            Map<String, Object> model,HttpServletRequest request) {
         
        // for testing purpose:
    	LOGGER.log(Level.INFO,"username: " + user.getUsername());
    	LOGGER.log(Level.INFO,"email: " + user.getEmail());
    	LOGGER.log(Level.INFO,"birth date: " + user.getDob());
    	ModelAndView modelview = new ModelAndView();
    	DriverManagerDataSource datasource = new JdbcUtil().getAccountsDataSource();
    	JdbcUserDAO userDAO = new JdbcUserDAO();
    	userDAO.setDataSource(datasource);
    	if(!userDAO.hasUser(user.getUsername())) { 
    	user.setEnabled(0);
    	user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        
    	
    	// Inject the datasource into the dao
        int lid = userDAO.insert(user);
        LOGGER.log(Level.INFO,"Registered lid::::::"+lid);
        user.setLid(lid);
        modelview.setViewName("login");
        modelview.addObject("msg", "Your account has been created! Please confirm your email address before login...");
        String token = UserUtil.getUserToken(user);
        String serverName = request.getServerName();
        if(serverName.contains("localhost")) {
        	serverName = serverName+":"+request.getServerPort()+(request.getServletContext().getContextPath());
        }
        String verificationUrl = request.getScheme()+"://"+serverName+"/emailVerification?token="+token;
        String msg = "<img src='images/leafsoft.png' alt='LeafSoft'><br><h3>Hi "+user.getUsername()+"</h3>,<br>"+"<h4>You just signed up for LeafSoft. Please follow this link to confirm that this is your e-mail address.<br>";
        msg = msg + "<a href="+verificationUrl+">VerificationLink</a>";
        msg = msg + "<br><br>Thanks<br>The LeafSoft Team";
        SendMail.send(user.getEmail(), "Please confirm your email address", msg);
    	}
    	else {
    		modelview.setViewName("Registration");
            modelview.addObject("error", "UserName already exist..");
    	}
        SecurityContextHolder.clearContext();
        JdbcUtil.cleanUp(datasource);
        return modelview;
    }
    
    @RequestMapping(value = "/emailVerification", method = RequestMethod.GET)
    public ModelAndView emailVerificationProcess(HttpServletRequest request,
            Map<String, Object> model) {
    	 ModelAndView modelview = new ModelAndView();
    	 String token = request.getParameter("token");
    	 LeafUser user = UserUtil.getUserFromToken(token);
    	 if(user != null) {
    		 DriverManagerDataSource datasource = new JdbcUtil().getAccountsDataSource();
    	        // Inject the datasource into the dao
    	    	JdbcUserDAO userDAO = new JdbcUserDAO();
    	    	userDAO.setDataSource(datasource);
    	    	userDAO.updateUserStatus(user.getLid(), "1");
    	    	JdbcUtil.cleanUp(datasource);
    	    	String msg = "<img src='images/leafsoft.png' alt='LeafSoft'><br><h3>Hi "+user.getUsername()+"</h3>,<br>"+"<h4>Welcome to LeafSoft!<br>Now that you've successfully created an LeafSoft Online account.";
    	        msg = msg + "<br><br>Thanks<br>The LeafSoft Team";
    	        SendMail.send(user.getEmail(), "Welcome to LeafSoft", msg);
    	    	modelview.addObject("msg","email verification done!!. Please Login");
    	 } else {
    		 	modelview.addObject("msg","can't verify your account. Please tryagain..");
    	 }
         modelview.setViewName("login");
    	return modelview;
    }
    
    @RequestMapping(value="/forgetPassword", method= RequestMethod.GET)
    public String forgetPassword(Map<String, Object> model) {
    	return "forgetPassword";
    }
    
    @RequestMapping(value="/forgetPassword", method= RequestMethod.POST)
    public ModelAndView forgetPassword(@RequestParam("username") String username,
            Map<String, Object> model,HttpServletRequest request) {
    	ModelAndView modelview = new ModelAndView();
    	LOGGER.log(Level.INFO,"Email::::"+username);
    	DriverManagerDataSource datasource = new JdbcUtil().getAccountsDataSource();
        // Inject the datasource into the dao
    	JdbcUserDAO userDAO = new JdbcUserDAO();
    	userDAO.setDataSource(datasource);
    	LeafUser user = userDAO.loadUserByUsername(username);
    	String token = UserUtil.getUserToken(user);
        String serverName = request.getServerName();
        if(serverName.contains("localhost")) {
        	serverName = serverName+":"+request.getServerPort()+(request.getServletContext().getContextPath());
        }
        String verificationUrl = request.getScheme()+"://"+serverName+"/resetPassword?token="+token;
        LOGGER.log(Level.INFO,"Generated token"+token);
        String msg = "<img src='images/leafsoft.png' alt='LeafSoft'><br><h3>Hi "+user.getUsername()+"</h3>,<br>"+"<h4>Here is the link to reset your LeafSoft Account Password:";
        msg = verificationUrl;
        msg = msg + "<br><br>Thanks<br>The LeafSoft Team";
        SendMail.send(user.getEmail(), "Reset your LeafSoft account password", msg);
        SecurityContextHolder.clearContext();
    	modelview.setViewName("login");
    	modelview.addObject("msg", "Reset Password link has been send to your registered mail!");
    	return modelview;
    }
    
    @RequestMapping(value="/resetPassword", method= RequestMethod.GET)
    public ModelAndView resetPassword(@RequestParam("token") String token,
            Map<String, Object> model,HttpServletRequest request) {
    	 ModelAndView modelview = new ModelAndView();
    	 LeafUser user = UserUtil.getUserFromToken(token);
    	 LOGGER.log(Level.INFO,":::token::::"+token);
    	 if(user != null) {
    		 	modelview.addObject("token",token);
    		 	modelview.addObject("username",user.getUsername());
    	    	modelview.addObject("msg","Enter your new password...");
    	    	modelview.setViewName("resetPassword");
    	 } else {
    		 	modelview.addObject("error","can't verify your account. Please try again..");
    		 	modelview.setViewName("login");
    	 }
         
    	return modelview;
    }
    @RequestMapping(value="/resetPassword", method= RequestMethod.POST)
    public ModelAndView resetPassword(@RequestParam("token") String token,
            Map<String, Object> model,@RequestParam("password") String password,HttpServletRequest request) {
    	 ModelAndView modelview = new ModelAndView();
    	 LeafUser user = UserUtil.getUserFromToken(token);
    	 String newpassword = new BCryptPasswordEncoder().encode(password);
    	 if(user != null) {
    		 	DriverManagerDataSource datasource = new JdbcUtil().getAccountsDataSource();
    	        // Inject the datasource into the dao
    	    	JdbcUserDAO userDAO = new JdbcUserDAO();
    	    	userDAO.setDataSource(datasource);
    	    	userDAO.updateUserPassword(user.getLid(), newpassword);
    	    	modelview.addObject("msg","ResetPassword successfully");
    	    	String msg = "<img src='images/leafsoft.png' alt='LeafSoft'><br><h3>Hi "+user.getUsername()+"</h3>,<br>"+"<h4>The password for your LeafSoft Account was recently changed.<br><br>";
    	        msg = msg + "<br><br>Thanks<br>The LeafSoft Team";
    	        SendMail.send(user.getEmail(), "Your password changed", msg);
    	    	JdbcUtil.cleanUp(datasource);
    	 } else {
    		 	modelview.addObject("error","can't reset your account. Please try again..");
    	 }
    	 modelview.setViewName("login");
         
    	return modelview;
    }
    
    @RequestMapping(value="/changePassword", method= RequestMethod.GET)
    public String changePassword(Map<String, Object> model) {
    	return "changePassword";
    }
    
    @RequestMapping(value="/changePassword", method= RequestMethod.POST)
    public ModelAndView changePassword(@RequestParam("oldpassword") String oldpassword,
            Map<String, Object> model,@RequestParam("password") String password,HttpServletRequest request) {
    	 ModelAndView modelview = new ModelAndView();
    	 LeafUser user = UserUtil.getCurrentUser();
    	 BCryptPasswordEncoder Bcrypt = new BCryptPasswordEncoder();
    	 String encodedPassword = Bcrypt.encode(password);
    	 
    	 if(user != null && Bcrypt.matches(oldpassword, user.getPassword())) {
    		 	DriverManagerDataSource datasource = new JdbcUtil().getAccountsDataSource();
    	        // Inject the datasource into the dao
    	    	JdbcUserDAO userDAO = new JdbcUserDAO();
    	    	userDAO.setDataSource(datasource);
    	    	userDAO.updateUserPassword(user.getLid(), encodedPassword);
    	    	modelview.addObject("msg","Password changed successfully");
    	    	modelview.setViewName("login");
    	    	String msg = "<img src='images/leafsoft.png' alt='LeafSoft'><br><h3>Hi "+user.getUsername()+"</h3>,<br>"+"<h4>The password for your LeafSoft Account was recently changed.<br><br>";
    	        msg = msg + "<br><br>Thanks<br>The LeafSoft Team";
    	        SendMail.send(user.getEmail(), "Your password changed", msg);
    	        SecurityContextHolder.clearContext();
    	        JdbcUtil.cleanUp(datasource);
    	 } else { 
    		 modelview.setViewName("changePassword");
    		 modelview.addObject("error","old password is wrong. Please try again..");
    	 }
         
    	return modelview;
    }
    
}