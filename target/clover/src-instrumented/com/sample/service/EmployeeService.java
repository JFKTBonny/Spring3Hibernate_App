/* $$ This file has been instrumented by Clover 4.5.2#20240131180750 $$ */package com.sample.service;

import java.util.List;

import com.sample.model.Employee;


public interface EmployeeService {
	
	public void addEmployee(Employee employee);

	public List<Employee> listEmployeess();
	
	public Employee getEmployee(int empid);
	
	public void deleteEmployee(Employee employee);
}
