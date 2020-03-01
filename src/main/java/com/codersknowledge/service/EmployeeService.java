package com.codersknowledge.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.codersknowledge.entity.Employee;
import com.codersknowledge.enums.DatatableSortOrderType;

/**
 * 
 * @author Zubayer Ahamed
 *
 */
@Component
public interface EmployeeService {

	public int saveEmployees(List<Employee> employees);

	public List<Employee> getAllEmployee(int limit, int offset, String orderBy, DatatableSortOrderType orderType, String searchText);

	public int countAllEmployee(String orderBy, DatatableSortOrderType orderType, String searchText);
}
