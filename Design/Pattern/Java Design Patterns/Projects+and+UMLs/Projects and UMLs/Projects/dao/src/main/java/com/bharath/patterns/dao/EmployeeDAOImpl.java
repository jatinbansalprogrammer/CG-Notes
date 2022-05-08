package com.bharath.patterns.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void create(Employee employee) {
		String sql = "insert into employee values(?,?)";
		jdbcTemplate.update(sql, employee.getId(), employee.getName());
	}

}
