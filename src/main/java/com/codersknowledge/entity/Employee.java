package com.codersknowledge.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

/**
 * 
 * @author Zubayer Ahamed
 *
 */
@Data
@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "employeeId", unique = true, nullable = false)
	private Long employeeId;

	@Column(name = "firstName", nullable = false, length = 50)
	private String firstName;

	@Column(name = "lastName", nullable = false, length = 50)
	private String lastName;
	
	@Column(name = "position", nullable = false, length = 50)
	private String position;

	@Column(name = "office", nullable = false, length = 50)
	private String office;

	@Temporal(TemporalType.DATE)
	@Column(name = "startDate", nullable = false)
	private Date startDate;

	@Column(name = "salary", nullable = false)
	private Double salary;
}
