package com.ecristobale.springboot.app.school.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ecristobale.springboot.app.school.models.service.delegate.IStudentServiceDelegate;

@RestController
public class SchoolController {

	@Autowired
	IStudentServiceDelegate studentServiceDelegate;
	
	@GetMapping("/schoolDetails/{schoolname}")
	public String getStudents(@PathVariable("schoolname") String schoolName) {
		return studentServiceDelegate.getStudentsBySchoolName(schoolName);
	}
}
