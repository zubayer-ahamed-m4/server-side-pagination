package com.codersknowledge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codersknowledge.entity.Student;
import com.codersknowledge.enums.SortOrderType;
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
	public @ResponseBody ServersidePaginationResponse loadTableData() {
		ServersidePaginationResponse response = new ServersidePaginationResponse();
		response.setDraw(0);
		response.setRecordsFiltered(10);
		response.setRecordsTotal(10);
		response.setData(studentService.getAllStudents(10, 10, "studentId", SortOrderType.DESC, null));
		return response;
	}
}
