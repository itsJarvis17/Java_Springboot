package com.recruit.crm.assignment.employeeperformancemgmt.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceReview {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public LocalDate getReview_date() {
		return review_date;
	}
	public void setReview_date(LocalDate review_date) {
		this.review_date = review_date;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public List<String> getReview_comments() {
		return review_comments;
	}
	public void setReview_comments(List<String> review_comments) {
		this.review_comments = review_comments;
	}
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;

	private LocalDate review_date;
	private int score;
	private List<String> review_comments;
}
