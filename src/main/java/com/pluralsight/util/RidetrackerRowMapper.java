package com.pluralsight.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.pluralsight.model.Ride;

public class RidetrackerRowMapper implements RowMapper<Ride> {

	@Override
	public Ride mapRow(ResultSet rs, int rowNum) throws SQLException {
		Ride rideObj = new Ride(rs.getInt("id"), rs.getString("name"), rs.getInt("duration"));

		return rideObj;
	}

}
