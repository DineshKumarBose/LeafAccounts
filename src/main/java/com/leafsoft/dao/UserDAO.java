package com.leafsoft.dao;

import com.leafsoft.model.LeafUser;

public interface UserDAO {
	public int insert(LeafUser user);
	public LeafUser findByCustomerId(int userId);
	public LeafUser loadUserByUsername(String userName);
	public boolean updateUserStatus(long luid,String status);
	public boolean updateUserPassword(long lid, String password);
	public boolean hasUser(String userName);
}
