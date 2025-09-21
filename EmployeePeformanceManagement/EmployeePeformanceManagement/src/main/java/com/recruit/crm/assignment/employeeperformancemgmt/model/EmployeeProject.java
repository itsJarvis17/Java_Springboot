package com.recruit.crm.assignment.employeeperformancemgmt.model;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee_project")
public class EmployeeProject {

	@ManyToOne
	@MapsId("employee_id")
	@JoinColumn(name = "employee_id")
	private Employee employee;

	@ManyToMany
	@JoinColumn(name = "project_id")
	private Project project;

	private LocalDate assigned_date;
	private String role;
}
