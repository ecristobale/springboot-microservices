package com.ecristobale.springboot.app.school.models.service.delegate;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StudentServiceDelegate implements IStudentServiceDelegate {

	@Autowired
	RestTemplate clientRestTemplate;
	
	@Override
	public String getStudentsBySchoolName(String schoolName) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("schoolname", schoolName);
		ResponseEntity<String> response = clientRestTemplate.exchange("http://student-service/students/{schoolname}", HttpMethod.GET, null, String.class, pathVariables);
		return response.getBody();
	}

}
