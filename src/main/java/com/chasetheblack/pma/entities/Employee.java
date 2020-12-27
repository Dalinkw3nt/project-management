package com.chasetheblack.pma.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.chasetheblack.pma.validators.UniqueValue;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="employee_seq")
//	@Column(name = "employee_id")
	private long employeeId;
	
//	@Id
//    @GeneratedValue(strategy=SEQUENCE, generator="CUST_SEQ")
//    @Column(name="CUST_ID")
//    public Long getId() { return id; }
	
	
	@NotBlank(message="Must provide a first name")
	@Size(min=2, max=50)
	private String firstName;
	
	@NotBlank(message="Must provide a last name")
	@Size(min=1, max=50)
	private String lastName;
	
	@NotBlank
	@Email(message="Must provide a valid email address")
	@UniqueValue
	private String email;
	
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST}, fetch = FetchType.LAZY)
	@JoinTable(name="project_employee", joinColumns=@JoinColumn(name="employee_id"), inverseJoinColumns=@JoinColumn(name="project_id"))
	
	@JsonIgnore
	private List<Project> projects;
	
	public Employee() {
		
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> Projects) {
		this.projects = Projects;
	}


	
	public Employee(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
