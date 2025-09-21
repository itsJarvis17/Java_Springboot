package com.recruit.crm.assignment.employeeperformancemgmt.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.recruit.crm.assignment.employeeperformancemgmt.dto.EmployeeDetailsDto;
import com.recruit.crm.assignment.employeeperformancemgmt.model.Department;
import com.recruit.crm.assignment.employeeperformancemgmt.model.Employee;
import com.recruit.crm.assignment.employeeperformancemgmt.model.PerformanceReview;
import com.recruit.crm.assignment.employeeperformancemgmt.model.Project;
import com.recruit.crm.assignment.employeeperformancemgmt.repository.EmployeeRepository;
import com.recruit.crm.assignment.employeeperformancemgmt.repository.PerformanceReviewRepository;

class EmployeePerformanceReviewServiceTest {

	@Mock
	private EmployeeRepository empRepo;
	
	 @Mock
	    private PerformanceReviewRepository prRepo;

	@InjectMocks
	private EmployeePerformanceReviewService employeeService; // Service under test

	private Employee emp1;
	private Employee emp2;
	private Department dept;
	private Project proj;
    private PerformanceReview review1, review2;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		dept = new Department();
		dept.setId(1);
		dept.setName("IT");

		proj = new Project();
		proj.setId(100);
		proj.setName("ProjectX");

		emp1 = new Employee();
		emp1.setId(1);
		emp1.setDepartment(dept);

		emp2 = new Employee();
		emp2.setId(2);
		emp2.setDepartment(dept);
		
		review1 = new PerformanceReview();
        review1.setId(1001);
        review1.setScore(5);
        review1.setReview_date(LocalDate.of(2025, 1, 1));

        review2 = new PerformanceReview();
        review2.setId(1002);
        review2.setScore(4);
        review2.setReview_date(LocalDate.of(2025, 2, 1));
	}

	@Test
	void testGetEmployeesWithFilters_ReturnsEmployees() {
		// given
		LocalDate reviewDate = LocalDate.of(2025, 1, 1);
		List<Department> departments = List.of(dept);
		List<Project> projects = List.of(proj);

		when(empRepo.findEmployeesWithFilters(5, reviewDate, departments, projects)).thenReturn(List.of(emp1, emp2));

		// when
		List<Employee> result = employeeService.getEmployeesWithFilters(5, reviewDate, departments, projects);

		// then
		assertThat(result).hasSize(2).containsExactly(emp1, emp2);
		verify(empRepo, times(1)).findEmployeesWithFilters(5, reviewDate, departments, projects);
	}

	@Test
	void testGetEmployeesWithFilters_NoResults() {
		// given
		when(empRepo.findEmployeesWithFilters(anyInt(), any(), anyList(), anyList())).thenReturn(List.of());

		// when
		List<Employee> result = employeeService.getEmployeesWithFilters(3, LocalDate.now(), List.of(), List.of());

		// then
		assertThat(result).isEmpty();
		verify(empRepo, times(1)).findEmployeesWithFilters(anyInt(), any(), anyList(), anyList());
	}
	
	@Test
    void testGetEmployeeDetails_ReturnsDto() {
        // given
        int empId = 1;
        int limit = 3;

        when(empRepo.findEmployeeWithDetails(empId)).thenReturn(emp1);
        when(prRepo.findLast3ReviewsByEmployeeId(empId, limit)).thenReturn(List.of(review1, review2));

        // when
        EmployeeDetailsDto result = employeeService.getEmployeeDetails(empId, limit);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo("alice@example.com");
        assertThat(result.getDepartment().getName()).isEqualTo("IT");
        verify(empRepo, times(1)).findEmployeeWithDetails(empId);
        verify(prRepo, times(1)).findLast3ReviewsByEmployeeId(empId, limit);
    }

    @Test
    void testGetEmployeeDetails_NoReviews() {
        // given
        int empId = 1;
        int limit = 3;

        when(empRepo.findEmployeeWithDetails(empId)).thenReturn(emp2);
        when(prRepo.findLast3ReviewsByEmployeeId(empId, limit)).thenReturn(List.of());

        // when
        EmployeeDetailsDto result = employeeService.getEmployeeDetails(empId, limit);

        // then
        assertThat(result).isNotNull();

        verify(empRepo, times(1)).findEmployeeWithDetails(empId);
        verify(prRepo, times(1)).findLast3ReviewsByEmployeeId(empId, limit);
    }
}
