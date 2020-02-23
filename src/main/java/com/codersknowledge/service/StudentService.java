package com.codersknowledge.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.codersknowledge.entity.Student;
import com.codersknowledge.enums.SortOrderType;

@Component
public interface StudentService {
	
	public int saveBulStudent(List<Student> students);
	public List<Student> getAllStudents(int limit, int offset, String orderBy, SortOrderType orderType, String searchText);

}
