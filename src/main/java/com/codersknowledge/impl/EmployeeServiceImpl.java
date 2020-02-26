package com.codersknowledge.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.codersknowledge.entity.Employee;
import com.codersknowledge.enums.SortOrderType;
import com.codersknowledge.repository.EmployeeRepository;
import com.codersknowledge.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@PersistenceContext private EntityManager em;
	@Autowired private EmployeeRepository studentRepository;
	@Autowired private JdbcTemplate jdbcTemplate;

	@Transactional
	@Override
	public int saveEmployees(List<Employee> students) {
		for(Employee st : students) {
			studentRepository.save(st);
		}
		return 0;
	}

	@Override
	public List<Employee> getAllStudents(int limit, int offset, String orderBy, SortOrderType orderType, String searchText) {
		String sql = "SELECT * FROM student ORDER BY "+ orderBy + " " + orderType +" LIMIT "+ limit +" OFFSET " + offset;
		
		List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
		List<Employee> list = new ArrayList<>();
		result.stream().forEach(row -> list.add(constractStudentObjectFromMap(row)));
		return list;
	}
	
	private Employee constractStudentObjectFromMap(Map<String, Object> row) {
		Employee st = new Employee();
		st.setEmployeeId((Long) row.get("studentId"));
		st.setFirstName((String) row.get("name"));
		return st;
	}

	@Override
	public Long getAllStudentsCount(String searchText) {
		return studentRepository.count();
	}

	
}
