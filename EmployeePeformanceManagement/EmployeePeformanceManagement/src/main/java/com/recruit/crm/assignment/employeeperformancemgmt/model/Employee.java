package com.recruit.crm.assignment.employeeperformancemgmt.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ValueGenerationType;
import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(unique = true)
	private String email;

	@OneToMany
	@JoinColumn(name = "department_id")
	private Department department;

	private LocalDate dateOfJoining;

	private double salary;

	@ManyToOne
	@JoinColumn(name = "manager_id")
	private Employee manager;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
	private List<EmployeeProject> projects = new ArrayList<>();

	public int getId() {
		return id;
	}

	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public List<EmployeeProject> getProjects() {
		return projects;
	}

	public void setProjects(List<EmployeeProject> projects) {
		this.projects = projects;
	}

	public void setId(int id) {
		this.id = id;
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

	public LocalDate getDate_of_joining() {
		return dateOfJoining;
	}

	public void setDate_of_joining(LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

}
