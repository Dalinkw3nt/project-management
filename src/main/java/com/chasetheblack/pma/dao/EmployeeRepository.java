package com.chasetheblack.pma.dao;

import java.util.List;

//import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.chasetheblack.pma.dto.EmployeeProject;
import com.chasetheblack.pma.entities.Employee;

@RepositoryRestResource(collectionResourceRel="api-employees", path="api-employees")
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {



	@Query(nativeQuery = true, value = "SELECT e.first_name as firstName, e.last_name as lastName, COUNT(pe.employee_id) as projectCount\r\n"
			+ "FROM employee e left join project_employee pe ON pe.employee_id=e.employee_id\r\n"
			+ "GROUP BY e.first_name, e.last_name ORDER BY 3 DESC")
	public List<EmployeeProject> employeeProjects();

	public Employee findByEmail(String value);

	public Employee findByEmployeeId(long theId);
	


}
