package com.recruit.crm.assignment.employeeperformancemgmt.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.recruit.crm.assignment.employeeperformancemgmt.dto.EmployeeDetailsDto;
import com.recruit.crm.assignment.employeeperformancemgmt.model.Department;
import com.recruit.crm.assignment.employeeperformancemgmt.model.Employee;
import com.recruit.crm.assignment.employeeperformancemgmt.model.Project;
import com.recruit.crm.assignment.employeeperformancemgmt.services.EmployeePerformanceReviewService;

@Controller
public class EmployeePerformanceController {

	private EmployeePerformanceReviewService reviewService;

	public EmployeePerformanceController(EmployeePerformanceReviewService employeePerformanceReviewService) {
		this.reviewService = employeePerformanceReviewService;
	}

	@GetMapping(path = "/getFilteredEmployees")
	public ResponseEntity<List<Employee>> getEmployeesWithFilters(@RequestParam(name = "performance_score") int score,
			@RequestParam(name = "review_date") LocalDate reviewDate,
			@RequestParam(name = "department") List<Department> department,
			@RequestParam(name = "projects") List<Project> projects) {
		List<Employee> employees = this.reviewService.getEmployeesWithFilters(score, reviewDate, department, projects);
		if (employees != null) {
			return new ResponseEntity<>(employees, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

	@GetMapping(path = "/getEmployee")
	public ResponseEntity<EmployeeDetailsDto> getEmployeeDetails(@RequestParam(name = "id") int id,
			@RequestParam(name = "limit", defaultValue = "3") int limit) {

		EmployeeDetailsDto empDetailsDto = this.reviewService.getEmployeeDetails(id, limit);
		if (empDetailsDto != null) {
			return new ResponseEntity<>(empDetailsDto, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

}
