package com.chasetheblack.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.chasetheblack.pma.dao.EmployeeRepository;
import com.chasetheblack.pma.dao.ProjectRepository;
import com.chasetheblack.pma.dto.TimeChartData;
import com.chasetheblack.pma.entities.Employee;
import com.chasetheblack.pma.entities.Project;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/projects")

public class ProjectController {
	@Autowired
	ProjectRepository proRepo;
	
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping
	public String displayProjects(Model model) {
		List<Project> projects = proRepo.findAll();
		model.addAttribute("projects", projects);
		return"projects/list-projects";
	}


	@GetMapping("/new")
	public String displayProjectForm(Model model) {

		Project aProject = new Project();
		Iterable<Employee> employees = empRepo.findAll();
		model.addAttribute("project", aProject);
		model.addAttribute("allEmployees", employees);

		return "projects/new-project";
	}

	@PostMapping("/save")
	public String createProject(Project project, Model model) {
		proRepo.save(project);
//use a redirect to prevent duplicate submissions


		return "redirect:/projects/new";
	}
	@GetMapping("/timelines")
	public String displayProjectTimelines(Model model) throws JsonProcessingException{
		List<TimeChartData> timelineData = proRepo.getTimeData();		
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonTimelineString = objectMapper.writeValueAsString(timelineData);
		
		System.out.println("----------------------project timelines-------------------------");
		System.out.println(jsonTimelineString);
		
		model.addAttribute("projectTimeList", jsonTimelineString);
		
		return"projects/project-timelines";
	}
}
