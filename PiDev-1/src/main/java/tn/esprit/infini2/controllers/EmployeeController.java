package tn.esprit.infini2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.infini2.entities.Employee;
import tn.esprit.infini2.services.IEmployeeService;
@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	IEmployeeService employeeService;
	
///////////////////////////////////////addMessage///////////////////////////////////////////
	
// http://localhost:8085/PIDEV/employee/add-employee
		@PostMapping("/add-employee")
		@ResponseBody
		public Employee addMessage(@RequestBody Employee e)
		{
			Employee employee = employeeService.addEmployee(e);
			return employee;
		}
}