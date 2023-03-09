package com.employee.service;

import java.util.List;
import java.util.Optional;

import com.employee.entity.Employee;

public interface EmployeeService {

	public Employee saveEmployee(Employee employee);
	
	public List < Employee > getAllEmployees();
	
	public Optional<Employee>  getEmployee(int empId);
	
	public void  deleteEmployee(Employee employee);
	
	
}
