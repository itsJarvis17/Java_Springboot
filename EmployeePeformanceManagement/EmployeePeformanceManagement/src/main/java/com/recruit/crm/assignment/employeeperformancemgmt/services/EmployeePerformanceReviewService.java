package com.recruit.crm.assignment.employeeperformancemgmt.services;

import java.time.LocalDate;
import java.util.List;

import com.recruit.crm.assignment.employeeperformancemgmt.dto.EmployeeDetailsDto;
import com.recruit.crm.assignment.employeeperformancemgmt.model.Department;
import com.recruit.crm.assignment.employeeperformancemgmt.model.Employee;
import com.recruit.crm.assignment.employeeperformancemgmt.model.PerformanceReview;
import com.recruit.crm.assignment.employeeperformancemgmt.model.Project;
import com.recruit.crm.assignment.employeeperformancemgmt.repository.EmployeeRepository;
import com.recruit.crm.assignment.employeeperformancemgmt.repository.PerformanceReviewRepository;

public class EmployeePerformanceReviewService {

	private final EmployeeRepository empRepo;
	private final PerformanceReviewRepository prRepo;

	public EmployeePerformanceReviewService(EmployeeRepository employeePerformanceRepository,
			PerformanceReviewRepository performanceReviewRepository) {
		this.empRepo = employeePerformanceRepository;
		this.prRepo = performanceReviewRepository;
	}

	public List<Employee> getEmployeesWithFilters(int score, LocalDate reviewDate, List<Department> department,
			List<Project> projects) {
		List<Employee> employees = empRepo.findEmployeesWithFilters(score, reviewDate, department, projects);
		return employees;
	}

	public EmployeeDetailsDto getEmployeeDetails(int id, int limit) {
		Employee employee = empRepo.findEmployeeWithDetails(id);
		List<PerformanceReview> performanceReviews = prRepo.findLast3ReviewsByEmployeeId(id, limit);

		EmployeeDetailsDto empDetailsDto = new EmployeeDetailsDto(employee.getEmail(), employee.getDepartment(),
				employee.getProjects(), performanceReviews);

		return empDetailsDto;

	}

}
