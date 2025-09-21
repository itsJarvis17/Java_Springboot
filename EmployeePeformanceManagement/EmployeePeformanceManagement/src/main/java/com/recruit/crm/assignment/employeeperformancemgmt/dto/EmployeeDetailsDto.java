package com.recruit.crm.assignment.employeeperformancemgmt.dto;

import java.util.List;

import com.recruit.crm.assignment.employeeperformancemgmt.model.Department;
import com.recruit.crm.assignment.employeeperformancemgmt.model.EmployeeProject;
import com.recruit.crm.assignment.employeeperformancemgmt.model.PerformanceReview;
import com.recruit.crm.assignment.employeeperformancemgmt.model.Project;

public class EmployeeDetailsDto {

	private String email;
	private Department department;
	private List<EmployeeProject> projects;
	private List<PerformanceReview> performanceReview;

	public EmployeeDetailsDto(String email, Department department, List<EmployeeProject> list,
			List<PerformanceReview> performanceReviews) {
		super();
		this.email = email;
		this.department = department;
		this.projects = list;
		this.performanceReview = performanceReviews;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<EmployeeProject> getProjects() {
		return projects;
	}

	public void setProjects(List<EmployeeProject> projects) {
		this.projects = projects;
	}

	public List<PerformanceReview> getPerformanceReview() {
		return performanceReview;
	}

	public void setPerformanceReview(List<PerformanceReview> performanceReview) {
		this.performanceReview = performanceReview;
	}

}
