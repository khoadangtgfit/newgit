package com.example.demo32.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo32.dao.EmployeeRepository;
import com.example.demo32.dao.RoleRepository;
import com.example.demo32.entity.Employee;
import com.example.demo32.entity.Role;
import com.example.demo32.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api")
public class RoleController {

  @Autowired
  RoleRepository roleRepository;
  
  @Autowired
  EmployeeRepository employeeRepository;
  
  @PostMapping("/roles/addEmp")
  public ResponseEntity<Employee> addEmployeeToRole(@RequestParam("role_id") Long role_id, @RequestParam("employee_id") Long employee_id){
    Employee employee=employeeRepository.findById(employee_id).orElseThrow(()->new ResourceNotFoundException("not found employee by id = "+employee_id));
    Role role=roleRepository.findById(role_id).orElseThrow(()->new ResourceNotFoundException("not found role by id = "+role_id));
  
    return new ResponseEntity<Employee>(employee,HttpStatus.OK);
  }
}
