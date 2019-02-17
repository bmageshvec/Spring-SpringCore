package com.pluralsight.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.pluralsight.model.Ride;
import com.pluralsight.util.RidetrackerRowMapper;

@Repository("rideRepository")
public class RideRepositoryImpl implements RideRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<Ride> getRides() {
		List<Ride> rides = jdbcTemplate.query("select  *  from ride", new RidetrackerRowMapper());
		return rides;
	}

	@Override
	public Ride createRide(Ride ride) {
		System.out.println(" Inside Repository CreateRide");
		KeyHolder keyholder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement("insert into ride (name,duration) values (?,?)",
						new String[] { "id" });
				ps.setString(1, ride.getName());
				ps.setInt(2, ride.getDuration());
				return ps;
			}
		}, keyholder);
		Number objId = keyholder.getKey();
		System.out.println("" + objId.intValue());
		return getRide(objId.intValue());

	}

	@Override
	public Ride getRide(Integer id) {
		Ride ride = jdbcTemplate.queryForObject("select  *  from ride where id=?", new RidetrackerRowMapper(), id);
		return ride;
	}

	@Override
	public Ride updateRide(Ride ride) {
		 jdbcTemplate.update("update ride set duration=? , name=? where id=?", ride.getDuration(), ride.getName(),ride.getId());
		 return ride;
	}

	@Override
	public void deleteRide(Integer id) {
		jdbcTemplate.update("delete  from ride  where id=?", id);
	}

	
	

}
