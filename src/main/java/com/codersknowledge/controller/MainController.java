package com.codersknowledge.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.codersknowledge.entity.Employee;
import com.codersknowledge.model.DatatableRequestHelper;
import com.codersknowledge.model.DatatableResponseHelper;
import com.codersknowledge.service.EmployeeService;

/**
 * @author Zubayer Ahamed
 * 
 */
@Controller
@RequestMapping("/")
public class MainController {

	@Autowired private EmployeeService employeeService;

	@GetMapping
	public String loadHomePage() {
		return "index";
	}

	@GetMapping("/students")
	public @ResponseBody DatatableResponseHelper<Employee> loadTableData() {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		DatatableRequestHelper helper = new DatatableRequestHelper(request);

		List<Employee> students = employeeService.getAllEmployee(helper.getLength(), helper.getStart(), helper.getColumns().get(helper.getOrderColumnNo()).getName(), helper.getOrderType(), helper.getSearchValue());
		int totalRows = employeeService.countAllEmployee(helper.getColumns().get(helper.getOrderColumnNo()).getName(), helper.getOrderType(), helper.getSearchValue());

		DatatableResponseHelper<Employee> response = new DatatableResponseHelper<Employee>();
		response.setDraw(helper.getDraw());
		response.setRecordsTotal(totalRows);
		response.setRecordsFiltered(totalRows);
		response.setData(students);
		return response;
	}
}
