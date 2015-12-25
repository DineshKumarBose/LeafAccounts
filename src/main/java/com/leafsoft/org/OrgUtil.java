package com.leafsoft.org;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;

import com.leafsoft.model.LeafUser;

public class OrgUtil {
	
	private static Logger LOGGER = Logger.getLogger(OrgUtil.class.getName());
	
	private static ThreadLocal<LeafUser> OWNER = new ThreadLocal<LeafUser>();
	private static ThreadLocal<JSONArray> USER_ROLE = new ThreadLocal<JSONArray>();
	private static ThreadLocal<String> OWNERID = new ThreadLocal<String>();
	private static ThreadLocal<Integer> USERLID = new ThreadLocal<Integer>();
	private static ThreadLocal<LeafUser> USER = new ThreadLocal<LeafUser>();
	private static ThreadLocal<JSONArray> ORGADMINS = new ThreadLocal<JSONArray>();
	private static ThreadLocal<String> REMOTEUSERIPADDRESS = new ThreadLocal<String>();
	private static ThreadLocal<String> ORGDB = new ThreadLocal<String>();
	/**
	 * @return the owner
	 */
	public static LeafUser getOwner() {
		return OWNER.get();
	}
	/**
	 * @return the userRole
	 */
	public static JSONArray getUserRole() {
		return USER_ROLE.get();
	}
	/**
	 * @return the ownerid
	 */
	public static String getOwnerid() {
		return OWNERID.get();
	}
	/**
	 * @return the userlid
	 */
	public static Integer getUserlid() {
		return USERLID.get();
	}
	/**
	 * @return the user
	 */
	public static LeafUser getUser() {
		return USER.get();
	}
	/**
	 * @return the orgadmins
	 */
	public static JSONArray getOrgadmins() {
		return ORGADMINS.get();
	}
	/**
	 * @return the remoteuseripaddress
	 */
	public static String getRemoteuseripaddress() {
		return REMOTEUSERIPADDRESS.get();
	}
	/**
	 * @return the orgdb
	 */
	public static String getOrgdb() {
		return ORGDB.get();
	}
	/**
	 * @param owner the owner to set
	 */
	public static void setOwner(LeafUser owner) {
		OWNER.set(owner);
	}
	/**
	 * @param userRole the userRole to set
	 */
	public static void setUserRole(JSONArray userRole) {
		USER_ROLE.set(userRole);
	}
	/**
	 * @param ownerid the ownerid to set
	 */
	public static void setOwnerid(String ownerid) {
		OWNERID.set(ownerid);
	}
	/**
	 * @param userlid the userlid to set
	 */
	public static void setUserlid(Integer userlid) {
		USERLID.set(userlid);
	}
	/**
	 * @param user the user to set
	 */
	public static void setUser(LeafUser user) {
		USER.set(user);
	}
	/**
	 * @param orgadmins the orgadmins to set
	 */
	public static void setOrgadmins(ThreadLocal<JSONArray> orgadmins) {
		ORGADMINS = orgadmins;
	}
	/**
	 * @param remoteuseripaddress the remoteuseripaddress to set
	 */
	public static void setRemoteuseripaddress(String remoteuseripaddress) {
		REMOTEUSERIPADDRESS.set(remoteuseripaddress);
	}
	/**
	 * @param orgdb the orgdb to set
	 */
	public static void setOrgdb(ThreadLocal<String> orgdb) {
		ORGDB = orgdb;
	}
	
	public static void init(LeafUser currentUser,String orgId) {
		try {
			if (currentUser == null) {
				return;
			}
			Integer lid =currentUser.getLid();
			if(lid != null) {
				OrgUtil.setUserlid(lid);
				OrgUtil.setOwner(currentUser);
			}
			OrgUtil.setOwnerid(OrgUtil.getOwnerid() != null ? OrgUtil.getOwnerid() :null);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
	}
	
	public static void cleanup() {
		OrgUtil.setOwner(null);
		OrgUtil.setUser(null);
		OrgUtil.setUserRole(null);
		OrgUtil.setOrgdb(null);
		OrgUtil.setRemoteuseripaddress(null);
		OrgUtil.setUserlid(null);
		OrgUtil.setOrgadmins(null);
		OrgUtil.setOwnerid(null);
	}
	
}
