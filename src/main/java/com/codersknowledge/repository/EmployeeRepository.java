package com.codersknowledge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codersknowledge.entity.Employee;

/**
 * 
 * @author Zubayer Ahamed
 *
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
