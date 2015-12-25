package com.leafsoft.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.leafsoft.util.DateAndTime;

public class LeafUserRowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			LeafUser user = new LeafUser();
			user.setLid(rs.getInt("lid"));
			user.setUsername(rs.getString("username"));
			user.setEmail(rs.getString("email"));
			user.setDob(DateAndTime.getDateFromString(rs.getString("dob"),DateAndTime.ORIGDATEFORMAT));
			return user;
		}
}
