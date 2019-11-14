package com.studentRestfulApi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.studentRestfulApi.model.Address;
import com.studentRestfulApi.model.Student;
import com.studentRestfulApi.service.ServiceLayer;

@RestController
@RequestMapping("/api")
public class MainController {
	
	private final ServiceLayer serviceLayer;
	
	@Autowired
	public MainController(ServiceLayer serviceLayer) {
		this.serviceLayer = serviceLayer;
	}

	@GetMapping(value="/getAllStudents", produces="application/json")
	@ResponseBody
	public ResponseEntity<?> getAllStudents(){
		return serviceLayer.getStudents();
	}
	
	@GetMapping(value="/getStudent/{id}", produces="application/json")
	@ResponseBody
	public ResponseEntity<?> getStudent(@PathVariable int id) {
			return serviceLayer.getStudentById(id);

	}
	
	@PutMapping(value = "/updateStudent", consumes = "application/json", produces="application/json")
	@ResponseBody
	public ResponseEntity<?> updateStudent(@Valid @RequestBody Student student, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors())
			return new ResponseEntity<>("Some error message",HttpStatus.BAD_REQUEST);
		
		return serviceLayer.updateStudent(student);
	}
	
	@PostMapping(value = "/createStudent", consumes = "application/json")
	@ResponseBody
	public ResponseEntity<?> saveStudent(@Valid @RequestBody Student student, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors())
			return new ResponseEntity<>("Please check all student information is correct.",HttpStatus.BAD_REQUEST);
		
		return serviceLayer.saveStudent(student);
	}
	
	
	@DeleteMapping("/deleteStudent/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable int id) {
		return serviceLayer.deleteStudentById(id);
	}
	
	@PutMapping(value = "/updateAddress", consumes = "application/json", produces="application/json")
	@ResponseBody
	public ResponseEntity<?> updateAddress(@RequestParam Integer studentId, @RequestBody Address address) {
		return serviceLayer.updateAddress(studentId, address);
	}
	
}
