package com.chasetheblack.pma.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.chasetheblack.pma.dao.EmployeeRepository;
import com.chasetheblack.pma.dao.ProjectRepository;
import com.chasetheblack.pma.dto.ChartData;
import com.chasetheblack.pma.dto.EmployeeProject;
import com.chasetheblack.pma.entities.Project;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class HomeController {
	// Field Injection
	
	@Value("${version}")
	private String ver;
	
	@Autowired
	ProjectRepository proRepo;
	@Autowired
	EmployeeRepository empRepo;

	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
		
		model.addAttribute("versionNumber", ver);
		
		Map<String, Object> map = new HashMap<>();
		
		//We are querying the database for the projects
		List<Project> projects = proRepo.findAll();
		model.addAttribute("projectsList", projects);
		
		List<ChartData> projectData = proRepo.getProjectStatus();
		
		//COnver projectData object into JSON structure for the use in JavaScript
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(projectData);
		
		model.addAttribute("projectStatusCnt", jsonString);
		
		
		//We are querying the database for employees
		List<EmployeeProject> employeesProjectCnt = empRepo.employeeProjects();
		model.addAttribute("employeesListProjectsCnt", employeesProjectCnt);
		
		
		return "main/home";
	}
}