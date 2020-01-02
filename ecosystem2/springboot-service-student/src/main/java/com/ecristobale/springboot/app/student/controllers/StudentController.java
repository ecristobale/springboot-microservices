package com.ecristobale.springboot.app.student.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ecristobale.springboot.app.student.models.Student;
import com.ecristobale.springboot.app.student.models.service.IStudentService;

@RestController
public class StudentController {

	@Autowired
	IStudentService studentService;
	
	 @GetMapping(value = "/students")
	 public List<Student> getAllStudents() {
		return studentService.findAll();
	 }
	
	 @GetMapping(value = "/students/{schoolname}")
	 public List<Student> getStudents(@PathVariable("schoolname") String schoolName) {
		return studentService.findBySchoolName(schoolName);
	 }
}
