package com.codersknowledge.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codersknowledge.entity.Student;
import com.codersknowledge.enums.SortOrderType;
import com.codersknowledge.model.PaginationParams;
import com.codersknowledge.model.ServersidePaginationResponse;
import com.codersknowledge.service.StudentService;

@Controller
@RequestMapping("/")
public class MainController {
	
	@Autowired private StudentService studentService;

	@GetMapping
	public String loadHomePage() {
		return "index";
	}
	
	@GetMapping("/students")
	public @ResponseBody ServersidePaginationResponse loadTableData(PaginationParams params) {
		System.out.println(params);
		ServersidePaginationResponse response = new ServersidePaginationResponse();
		response.setDraw(params.getDraw());
		response.setRecordsFiltered(params.getLength());
		response.setRecordsTotal(params.getLength());
		
		List<Student> students = studentService.getAllStudents(params.getLength(), params.getStart(), "studentId", SortOrderType.ASC, null);
		List<String[]> data = new ArrayList<>();
		students.stream().forEach(s -> {
			String id = String.valueOf(s.getStudentId());
			String name = s.getName();
			data.add(new String[] {id, name});
		});
		response.setData(data);
		return response;
	}
}
