package com.codersknowledge.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.codersknowledge.entity.Employee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ComponentScan(basePackages = { "com.codersknowledge.*" })
@Rollback(false)
public class EmployeeServiceTest {

	@Autowired
	private EmployeeService employeeService;

	@Test
	public void testInsert() {
		List<Employee> employees = new ArrayList<>();
		for (int i = 1; i <= 1000; i++) {
			Employee st = new Employee();
			st.setFirstName("First Name " + i);
			st.setLastName("Last Name " + i);
			st.setOffice("Office " + i);
			st.setPosition("Position " + i);
			st.setSalary(Double.valueOf(10000 + i));
			st.setStartDate(new Date());
			employees.add(st);
		}
		int count = employeeService.saveEmployees(employees);
		log.info("Totoal saved : {}", count);
		assertEquals(1000, count);
	}
}
