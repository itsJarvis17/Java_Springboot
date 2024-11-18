package com.bhargavn.StudentSpringBootProject;

import com.bhargavn.StudentSpringBootProject.model.Student;
import com.bhargavn.StudentSpringBootProject.service.StudentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StudentSpringBootProjectApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(StudentSpringBootProjectApplication.class, args);
		Student s = applicationContext.getBean(Student.class);
		s.setId(101);
		s.setName("Bhargav");
		s.setDept("EXTC");
		StudentService ss = applicationContext.getBean(StudentService.class);
		ss.addStudent(s);

		System.out.println(ss.getAllStudents());
	}

}
