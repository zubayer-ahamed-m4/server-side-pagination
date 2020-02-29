package com.codersknowledge.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.codersknowledge.entity.Employee;
import com.codersknowledge.enums.DatatableSortOrderType;

@Component
public interface EmployeeService {

	public int saveEmployees(List<Employee> students);

	public List<Employee> getAllStudents(int limit, int offset, String orderBy, DatatableSortOrderType orderType, String searchText);

	public Long getAllStudentsCount(String searchText);
}
