package com.codersknowledge.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.codersknowledge.entity.Employee;
import com.codersknowledge.enums.DatatableSortOrderType;
import com.codersknowledge.repository.EmployeeRepository;
import com.codersknowledge.service.EmployeeService;

/**
 * 
 * @author Zubayer Ahamed
 *
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@PersistenceContext private EntityManager em;
	@Autowired private EmployeeRepository employeeRepository;
	@Autowired private JdbcTemplate jdbcTemplate;

	@Transactional
	@Override
	public int saveEmployees(List<Employee> employees) {
		int count = 0;
		for(Employee em : employees) {
			employeeRepository.save(em);
			count++;
		}
		return count;
	}

	@Override
	public List<Employee> getAllEmployee(int limit, int offset, String orderBy, DatatableSortOrderType orderType, String searchText) {
		StringBuilder sql = new StringBuilder();
		sql.append(selectClause())
			.append(fromClause("employee"))
			.append(whereClause(searchText))
			.append(orderbyClause(orderBy, orderType.name()))
			.append(limitAndOffsetClause(limit, offset));

		List<Map<String, Object>> result = jdbcTemplate.queryForList(sql.toString());
		List<Employee> list = new ArrayList<>();
		result.stream().forEach(row -> list.add(constractEmployeeObjectFromMap(row)));

		return list;
	}

	@Override
	public int countAllEmployee(String orderBy, DatatableSortOrderType orderType, String searchText) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) ")
			.append(fromClause("employee"))
			.append(whereClause(searchText))
			.append(orderbyClause(orderBy, orderType.name()));
		return jdbcTemplate.queryForObject(sql.toString(), Integer.class);
	}

	private Employee constractEmployeeObjectFromMap(Map<String, Object> row) {
		Employee em = new Employee();
		em.setEmployeeId((Long) row.get("employeeId"));
		em.setFirstName((String) row.get("firstName"));
		em.setLastName((String) row.get("lastName"));
		em.setOffice((String) row.get("office"));
		em.setPosition((String) row.get("position"));
		em.setSalary((Double) row.get("salary"));
		em.setStartDate((Date) row.get("startDate"));
		return em;
	}

	private StringBuilder selectClause() {
		return new StringBuilder("SELECT * ");
	}
	private StringBuilder fromClause(String tableName) {
		return new StringBuilder(" FROM employee " + tableName + " ");
	}
	private StringBuilder whereClause(String searchText) {
		if(searchText == null || searchText.isEmpty()) return new StringBuilder();
		return new StringBuilder(" WHERE (employeeId LIKE '%"+ searchText +"%' OR firstName LIKE '%"+ searchText +"%' OR lastName LIKE '%"+ searchText +"%' OR office LIKE '%"+ searchText +"%' OR position LIKE '%"+ searchText +"%' OR salary LIKE '%"+ searchText +"%' OR startDate LIKE '%"+ searchText +"%') ");
	}
	private StringBuilder orderbyClause(String orderByField, String orderType) {
		return new StringBuilder(" ORDER BY " + orderByField + " " + orderType + " ");
	}
	private StringBuilder limitAndOffsetClause(int limit, int offset) {
		return new StringBuilder(" LIMIT " + limit + " OFFSET " + offset);
	}
}
