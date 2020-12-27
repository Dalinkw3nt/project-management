package com.chasetheblack.pma.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chasetheblack.pma.dao.EmployeeRepository;
import com.chasetheblack.pma.entities.Employee;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping
	public String displayEmployees(Model model) {
		Iterable<Employee> employees = empRepo.findAll();
		model.addAttribute("employees", employees);
		return"employees/list-employees";
	}

	@GetMapping("/new")
	public String displayEmployeeForm(Model model) {

		Employee anEmployee = new Employee();

		model.addAttribute("employee", anEmployee);

		return "employees/new-employee";
	}

	@PostMapping("/save")
	public String createEmployee(@Valid Employee employee, Model model, Errors errors) {
		
		if(errors.hasErrors())
			return "employees/new-employee";
		
		// save to the database using an employee CRUD repository
		empRepo.save(employee);

		return "redirect:/employees";
	}

	@GetMapping("/update")

	public String displayEmployeeUpdateForm(@RequestParam("id") Long theId, Model model) {
		Employee theEmp = empRepo.findByEmployeeId(theId);
		
		model.addAttribute("employee", theEmp);
			
		return "employees/new-employee";
	}
	
	@GetMapping("/delete")

	public String deleteEmployee(@RequestParam("id") Long theId, Model model) {
		Employee theEmp = empRepo.findByEmployeeId(theId);
		empRepo.delete(theEmp);
		return "redirect:/employees";
	}
}
