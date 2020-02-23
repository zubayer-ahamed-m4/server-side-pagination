package com.codersknowledge.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.codersknowledge.entity.Student;
import com.codersknowledge.enums.SortOrderType;
import com.codersknowledge.repository.StudentRepository;
import com.codersknowledge.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@PersistenceContext private EntityManager em;
	@Autowired private StudentRepository studentRepository;
	@Autowired private JdbcTemplate jdbcTemplate;

	@Transactional
	@Override
	public int saveBulStudent(List<Student> students) {
		for(Student st : students) {
			studentRepository.save(st);
		}
		return 0;
	}

	@Override
	public List<Student> getAllStudents(int limit, int offset, String orderBy, SortOrderType orderType, String searchText) {
		String sql = "SELECT * FROM student ORDER BY "+ orderBy + " " + orderType +" LIMIT "+ limit +" OFFSET " + offset;
		
		List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
		List<Student> list = new ArrayList<>();
		result.stream().forEach(row -> list.add(constractStudentObjectFromMap(row)));
		return list;
	}
	
	private Student constractStudentObjectFromMap(Map<String, Object> row) {
		Student st = new Student();
		st.setStudentId((Long) row.get("studentId"));
		st.setName((String) row.get("name"));
		return st;
	}

}
