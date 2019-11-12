package com.studentRestfulApi.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.studentRestfulApi.model.Student;
import com.studentRestfulApi.repository.StudentRepository;

@Service
@Transactional
public class ServiceLayer {


	private final StudentRepository studentRepository;

	@Autowired
	public ServiceLayer(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	public ResponseEntity<?> getStudentById(Integer id){
		
			
		if(!studentRepository.existsById(id))
				return new ResponseEntity<>("Student with the id "+id+ " not found!", HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(studentRepository.findById(id), HttpStatus.OK);
	}
	
	public ResponseEntity<?> getStudents(){
		
		List<Student> students = studentRepository.findAll();		
		if(students.isEmpty())
				return new ResponseEntity<>("Students are not found!", HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(students,HttpStatus.OK);
	}
	
	public ResponseEntity<?> updateStudent(Student student){
			
		return new ResponseEntity<>(studentRepository.save(student), HttpStatus.OK);
	}
	
	
	public ResponseEntity<?> saveStudent(Student student){
		
		return new ResponseEntity<>(studentRepository.save(student), HttpStatus.CREATED);
	}
	
	public ResponseEntity<?> deleteStudentById(Integer id){
		
		if(!studentRepository.existsById(id))
				return new ResponseEntity<>("Student with the id "+id+ " not found!", HttpStatus.BAD_REQUEST);
		try {
			studentRepository.deleteById(id);
			return new ResponseEntity<>("Student with ID " + id + " has been deleted.", HttpStatus.OK);
		}catch(Exception e) { 
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
}
