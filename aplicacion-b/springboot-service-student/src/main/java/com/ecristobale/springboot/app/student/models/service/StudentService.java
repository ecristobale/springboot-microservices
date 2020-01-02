package com.ecristobale.springboot.app.student.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecristobale.springboot.app.student.models.Student;
import com.ecristobale.springboot.app.student.models.dao.StudentDao;

@Service
public class StudentService implements IStudentService {

	@Autowired
	private StudentDao studentDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Student> findAll() {
		return (List<Student>) studentDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Student findById(Long id) {
		return studentDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Student> findBySchoolName(String schoolName) {
		return studentDao.findBySchoolName(schoolName);
	}

}
