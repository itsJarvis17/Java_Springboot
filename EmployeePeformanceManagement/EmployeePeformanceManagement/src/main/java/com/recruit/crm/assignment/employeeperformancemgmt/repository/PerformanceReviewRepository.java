package com.recruit.crm.assignment.employeeperformancemgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.recruit.crm.assignment.employeeperformancemgmt.model.PerformanceReview;

@Repository
public interface PerformanceReviewRepository extends JpaRepository<PerformanceReview, Integer> {

	@Query("SELECT pr FROM PerformanceReview pr WHRE pr.employee_id=:id ORDER BY pr.reviewDate DESC LIMIT :limit")
	List<PerformanceReview> findLast3ReviewsByEmployeeId(@Param("id") int id, int limit);

	
}
