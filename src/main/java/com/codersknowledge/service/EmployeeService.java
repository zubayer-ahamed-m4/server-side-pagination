package com.codersknowledge.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.codersknowledge.entity.Employee;
import com.codersknowledge.enums.SortOrderType;

@Component
public interface EmployeeService {

	public int saveEmployees(List<Employee> students);

	public List<Employee> getAllStudents(int limit, int offset, String orderBy, SortOrderType orderType, String searchText);

	public Long getAllStudentsCount(String searchText);
}
