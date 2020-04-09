package com.ram.javacoderhint.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ram.javacoderhint.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
	
}
