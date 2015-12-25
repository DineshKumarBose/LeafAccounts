/**
 * 
 */
package com.leafsoft.urlutilitis.action;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpUtils;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.leafsoft.http.HttpConstants;
import com.leafsoft.model.LeafUser;
import com.leafsoft.org.OrgUtil;
import com.leafsoft.urlutilitis.util.UrlUtilititisUtil;
import com.leafsoft.urlutilitis.util.urlutilitisConstants;
import com.leafsoft.util.Constants;
import com.leafsoft.util.UserUtil;

/**
 * @author dinesh-2301
 *
 */
public class UrlUtiltiesAction extends DispatchAction{
	
	private Logger LOGGER = Logger.getLogger(UrlUtiltiesAction.class.getName());
	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LOGGER.log(Level.INFO,"Incoming Request::::"+new Date());
		String url = request.getParameter("url").trim();
		response.setContentType(HttpConstants.JSON_CONTENT_TYPE);//No I18N
		JSONObject encodeJson = new JSONObject();
		String encodedUrl = UrlUtilititisUtil.enCodeUrl(url);
		encodeJson.put(Constants.RESULT, Constants.SUCCESS);
		encodeJson.put(urlutilitisConstants.ENCODED_URL, encodedUrl);
		response.getWriter().write(encodeJson.toString());
		return null;
		
	}
	
	public ActionForward encodeUrl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try{
		LOGGER.log(Level.INFO,"LID::::::"+UserUtil.getCurrentUser().getLid());
		LOGGER.log(Level.INFO,"getRemoteuseripaddress::::"+OrgUtil.getRemoteuseripaddress());
		LOGGER.log(Level.INFO,"userid::::"+OrgUtil.getUserlid());
		String url = request.getParameter("url").trim();
		response.setContentType(HttpConstants.JSON_CONTENT_TYPE);//No I18N
		JSONObject encodeJson = new JSONObject();
		String encodedUrl = UrlUtilititisUtil.enCodeUrl(url);
		encodeJson.put(Constants.RESULT, Constants.SUCCESS);
		encodeJson.put(urlutilitisConstants.ENCODED_URL, encodedUrl);
		response.getWriter().write(encodeJson.toString());
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE,"encodeUrl():::Exception:::{0}"+e.getMessage(),e);
		}
		return null;
	}
	
	public ActionForward decodeUrl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LOGGER.log(Level.INFO,"LID::::::"+UserUtil.getCurrentUser().getLid());
		try {
		String url = request.getParameter("url").trim();
		response.setContentType(HttpConstants.JSON_CONTENT_TYPE);//No I18N
		JSONObject decodeJson = new JSONObject();
		String decodedUrl = UrlUtilititisUtil.deCodeUrl(url);
		decodeJson.put(Constants.RESULT, Constants.SUCCESS);
		decodeJson.put(urlutilitisConstants.DECODED_URL, decodedUrl);
		response.getWriter().write(decodeJson.toString());
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE,"decodeUrl():::Exception{0}"+e.getMessage(),e);
		}
		return null;
	}

}
