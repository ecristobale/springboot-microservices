package com.ecristobale.springboot.app.student.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.ecristobale.springboot.app.student.models.Student;
import com.ecristobale.springboot.app.student.models.service.IStudentService;

@RestController
public class StudentController {

	@Autowired
	IStudentService studentService;
	
	@GetMapping(value = "/students")
	public List<Student> getAllStudents() {
		List<Student> students = studentService.findAll();
			 
		for (final Student student : students) {
		       Link selfLink = linkTo(methodOn(StudentController.class)
		         .getStudentsById(student.getStudentId())).withSelfRel();
		       student.add(selfLink);
		}
		 
		return students;
	}

	@GetMapping(value = "/students/school/{schoolname}")
	public List<Student> getStudents(@PathVariable("schoolname") String schoolName) {
		List<Student> students = studentService.findBySchoolName(schoolName);
		
		for (final Student student : students) {
		       Link selfLink = linkTo(methodOn(StudentController.class)
		         .getStudentsById(student.getStudentId())).withSelfRel();
		       student.add(selfLink);
		}
		
		return students;
	}
	
	@GetMapping(value = "/students/{id}")
	public Student getStudentsById(@PathVariable("id") Long id) {
		Student student = studentService.findById(id);
		Link link = linkTo(StudentController.class).withSelfRel();
		student.add(link);
		return student;
	}
}
