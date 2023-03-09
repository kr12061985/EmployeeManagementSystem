package com.employee.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.entity.Employee;
import com.employee.exception.ResourceNotFoundException;
import com.employee.repository.EmployeeRepository;
import com.employee.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	
	 @Autowired
	   	    private EmployeeService employeeService;

	    @GetMapping("/employees")
	    public List < Employee > getAllEmployees() {
	        return employeeService.getAllEmployees();
	    }

	    @GetMapping("/employees/{id}")
	    public ResponseEntity < Employee > getEmployeeById(@PathVariable(value = "id") int employeeId)
	    throws ResourceNotFoundException {
	        Employee employee = employeeService.getEmployee(employeeId)
	            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
	        return ResponseEntity.ok().body(employee);
	    }

	    @PostMapping("/employees")
	    public Employee createEmployee(@Valid @RequestBody Employee employee) {
	        return employeeService.saveEmployee(employee);
	    }

	    @PutMapping("/employees/{id}")
	    public ResponseEntity < Employee > updateEmployee(@PathVariable(value = "id") int employeeId,
	        @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
	        Employee employee = employeeService.getEmployee(employeeId)
	            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

	        employee.setEmailId(employeeDetails.getEmailId());
	        employee.setLastName(employeeDetails.getLastName());
	        employee.setFirstName(employeeDetails.getFirstName());
	        final Employee updatedEmployee = employeeService.saveEmployee(employee);
	        return ResponseEntity.ok(updatedEmployee);
	    }

	    @DeleteMapping("/employees/{id}")
	    public Map < String, Boolean > deleteEmployee(@PathVariable(value = "id") int employeeId)
	    throws ResourceNotFoundException {
	        Employee employee = employeeService.getEmployee(employeeId)
	            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

	        employeeService.deleteEmployee(employee);
	        Map < String, Boolean > response = new HashMap < > ();
	        response.put("deleted", Boolean.TRUE);
	        return response;
	    }
}
