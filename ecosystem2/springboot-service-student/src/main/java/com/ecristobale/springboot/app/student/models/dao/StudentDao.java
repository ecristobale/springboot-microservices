package com.ecristobale.springboot.app.student.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ecristobale.springboot.app.student.models.Student;

public interface StudentDao extends CrudRepository<Student, Long> {

	List<Student> findBySchoolName(String schoolName);

}
