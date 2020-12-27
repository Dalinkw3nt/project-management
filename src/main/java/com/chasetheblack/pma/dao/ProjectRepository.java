package com.chasetheblack.pma.dao;

import java.util.List;

//import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.chasetheblack.pma.dto.ChartData;
import com.chasetheblack.pma.dto.TimeChartData;
import com.chasetheblack.pma.entities.Project;

public interface ProjectRepository  extends PagingAndSortingRepository<Project, Long>{
@Override
//@Bean
	public List<Project> findAll();
	
@Query(nativeQuery=true, value="SELECT stage as label, COUNT(*) as value\r\n"
		+ "FROM project\r\n"
		+ "GROUP BY stage")
public List<ChartData> getProjectStatus();

@Query(nativeQuery=true, value="SELECT name as projectName, start_date as startDate, end_date as endDate FROM project WHERE start_date is not null")
public List<TimeChartData> getTimeData();


}
