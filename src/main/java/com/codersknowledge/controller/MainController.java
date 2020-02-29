package com.codersknowledge.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
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
import com.codersknowledge.model.DatatableColumnDefination;
import com.codersknowledge.model.DatatableRequestHelper;
import com.codersknowledge.model.DatatableResponseHelper;
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
	public @ResponseBody DatatableResponseHelper loadTableData() {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		DatatableRequestHelper helper = new DatatableRequestHelper(request);
		System.out.println(helper);


		DatatableResponseHelper response = new DatatableResponseHelper();
		response.setDraw(helper.getDraw());
		response.setRecordsTotal(studentService.getAllStudentsCount(null).intValue());
		response.setRecordsFiltered(studentService.getAllStudentsCount(null).intValue());

		List<Employee> students = studentService.getAllStudents(helper.getLength(), helper.getStart(), helper.getColumns().get(helper.getOrderColumnNo()).getName(), helper.getOrderType(), helper.getSearchValue());
		List<String[]> data = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		students.stream().forEach(s -> {
			String id = String.valueOf(s.getEmployeeId());
			String firstName = s.getFirstName();
			String lastName = s.getLastName();
			String office = s.getOffice();
			String position = s.getPosition();
			double salary = s.getSalary();
			Date date = s.getStartDate();
			data.add(new String[] {id, firstName, lastName, office, position, sdf.format(date), String.valueOf(salary)});
		});
		response.setData(data);
		return response;
	}
}
