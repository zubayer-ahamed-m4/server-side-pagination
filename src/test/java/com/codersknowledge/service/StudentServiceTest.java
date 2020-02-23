package com.codersknowledge.service;

import static org.mockito.Mockito.inOrder;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.codersknowledge.entity.Student;
import com.codersknowledge.enums.SortOrderType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ComponentScan(basePackages = {"com.codersknowledge.*"})
@Rollback(false)
public class StudentServiceTest {

@Autowired private StudentService studentService;
	
	@Test
	public void testInsert() {
		List<Student> students = new ArrayList<>();
		for(int i = 1; i <= 1000; i++){
			Student st = new Student();
			st.setName("name - " + i);
			students.add(st);
		}
		
		studentService.saveBulStudent(students);
	}
	
	@Test
	public void read() {
		List<Student> list = studentService.getAllStudents(10,10, "studentId", SortOrderType.ASC, null);
		list.stream().forEach(s -> System.out.println(s.toString()));
	}
}
