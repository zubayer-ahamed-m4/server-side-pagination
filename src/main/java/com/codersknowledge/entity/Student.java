package com.codersknowledge.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "studentId", unique = true, nullable = false)
	private Long studentId;
	@Column(name = "name", nullable = false, length = 50)
	private String name;
}
