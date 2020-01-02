package com.ecristobale.springboot.app.student.models.service;

import java.util.List;

import com.ecristobale.springboot.app.student.models.Student;

public interface IStudentService {

	public List<Student> findAll();
	public Student findById(Long id);
	public List<Student> findBySchoolName(String schoolName);
}
