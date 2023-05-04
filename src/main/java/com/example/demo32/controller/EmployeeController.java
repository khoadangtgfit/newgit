package com.example.demo32.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo32.dao.EmployeeRepository;
import com.example.demo32.entity.Employee;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

  @Autowired
  private EmployeeRepository employeeRepository;
  
  @GetMapping
  public List<Employee> getAllEmployees(){
    System.out.println("gggggggg");
    return employeeRepository.findAll();
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
    Optional<Employee> employee=employeeRepository.findById(id);
    System.out.println(employee);
    if(employee.isPresent()) {
      return ResponseEntity.ok(employee.get());
    }
    return ResponseEntity.notFound().build();
  }
  
  @PostMapping
  public Employee createEmployee(@RequestBody Employee employee) {
    return employeeRepository.save(employee);

  }
  
  @PutMapping("/{id}")
  public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee){
    Optional<Employee> empOptional=employeeRepository.findById(id);
    if(empOptional.isPresent()) {
      employee.setId(id);
      employeeRepository.save(employee);
      return ResponseEntity.ok(employee);
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteEmployee(@PathVariable Long id){
    Optional<Employee> emOptional=employeeRepository.findById(id);
    if(emOptional.isPresent()) {
      employeeRepository.deleteById(id);
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.notFound().build();
  }
}
