package com.codersknowledge.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codersknowledge.entity.Employee;
import com.codersknowledge.enums.SortOrderType;
import com.codersknowledge.model.RequestHelper;
import com.codersknowledge.model.ResponseHelper;
import com.codersknowledge.service.EmployeeService;

@Controller
@RequestMapping("/")
public class MainController {
	
	@Autowired private EmployeeService studentService;

	@GetMapping
	public String loadHomePage() {
		return "index";
	}
	
	@GetMapping("/students")
	public @ResponseBody ResponseHelper loadTableData(RequestHelper params) {
		System.out.println(params);
		ResponseHelper response = new ResponseHelper();
		response.setDraw(params.getDraw());
		response.setRecordsFiltered(studentService.getAllStudentsCount(null).intValue());
		response.setRecordsTotal(studentService.getAllStudentsCount(null).intValue());
		
		List<Employee> students = studentService.getAllStudents(params.getLength(), params.getStart(), "studentId", SortOrderType.ASC, null);
		List<String[]> data = new ArrayList<>();
		students.stream().forEach(s -> {
			String id = String.valueOf(s.getEmployeeId());
			String name = s.getFirstName();
			data.add(new String[] {id, name});
		});
		response.setData(data);
		return response;
	}
}
