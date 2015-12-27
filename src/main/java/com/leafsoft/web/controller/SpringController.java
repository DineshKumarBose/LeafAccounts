package com.leafsoft.web.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.leafsoft.dao.impl.JdbcUserDAO;
import com.leafsoft.model.LeafUser;
import com.leafsoft.util.JdbcUtil;
import com.leafsoft.util.UserUtil;

@Controller
public class SpringController {
	@Resource(name="sessionRegistry")
	 private SessionRegistryImpl sessionRegistry;

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public ModelAndView welcomePage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "LeafSoft");
		model.addObject("message", "This is welcome page!");
		model.setViewName("hello");
		return model;

	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView indexPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "LeafSoft");
		model.addObject("message", "This is welcome page!");
		model.setViewName("hello");
		return model;

	}


	@RequestMapping(value = "/urlutility**", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "LeafSoft");
		model.addObject("message", "This is protected page!");
		model.setViewName("urlutility");

		return model;

	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(
		@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout) {
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		LeafUser user = UserUtil.getCurrentUser();
		if(user==null) {
			model.setViewName("login");
		} else {
			model.setViewName("hello");
		}

		return model;

	}
	
	// for 403 access denied page
		@RequestMapping(value = "/403", method = RequestMethod.GET)
		public ModelAndView accesssDenied(Principal user) {

			ModelAndView model = new ModelAndView();

			if (user != null) {
				model.addObject("msg", "Hi " + user.getName() 
				+ ", you do not have permission to access this page!");
			} else {
				model.addObject("msg", 
				"You do not have permission to access this page!");
			}

			model.setViewName("403");
			return model;

		}
		
		@RequestMapping(value = "/loginUsers", method = RequestMethod.GET)
		@ResponseBody
		public String loginUsers(HttpServletRequest req,HttpServletResponse res) {
			String sessionId = req.getRequestedSessionId();
			System.out.print("sessionId;:"+sessionId);
			JSONObject resJson = new JSONObject();
			if(sessionId!=null) {
			//List<Object> principals = sessionRegistry.getAllPrincipals();
			SessionInformation sessioninfo = sessionRegistry.getSessionInformation(sessionId);
				if(sessioninfo!= null) {
					String userName =((User) sessioninfo.getPrincipal()).getUsername();
					DriverManagerDataSource datasource = new JdbcUtil().getAccountsDataSource();
			        // Inject the datasource into the dao
			    	JdbcUserDAO userDAO = new JdbcUserDAO();
			    	userDAO.setDataSource(datasource);
			    	LeafUser user = userDAO.loadUserByUsername(userName);
			    	resJson = new JSONObject();
			    	resJson.put("lid", user.getLid());
			    	resJson.put("username", user.getUsername());
			    	resJson.put("enabled", user.getEnabled());
			    	resJson.put("email", user.getEmail());
			    	resJson.put("dob", user.getDob());
				}
			}
	    	return resJson.toString();
	    	

		}

}
