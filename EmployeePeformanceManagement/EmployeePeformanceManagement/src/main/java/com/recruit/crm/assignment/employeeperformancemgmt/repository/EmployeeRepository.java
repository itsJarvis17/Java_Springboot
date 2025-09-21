package com.recruit.crm.assignment.employeeperformancemgmt.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.recruit.crm.assignment.employeeperformancemgmt.model.Department;
import com.recruit.crm.assignment.employeeperformancemgmt.model.Employee;
import com.recruit.crm.assignment.employeeperformancemgmt.model.Project;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query("SELECT DISTINCT e FROM Employee e JOIN e.department JOIN e.projects p JOIN e.performanceReviews pr JOIN e.department d "
			+ "WHERE pr.reviewDate=:reviewDate AND pr.score=:score AND d.name IN :department AND p.name IN :projects")
	List<Employee> findEmployeesWithFilters(@Param("score") int score, @Param("reviewDate") LocalDate reviewDate,
			@Param("department") List<Department> department, @Param("projects") List<Project> projects);

	@Query("SELECT e FROM Employee e JOIN e.department JOIN e.project WHERE e.id=:id")
	Employee findEmployeeWithDetails(int id);

}
