package com.example.demo32.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;

import com.example.demo32.dao.DepartmentRepository;
import com.example.demo32.dao.EmployeeRepository;
import com.example.demo32.entity.Department;
import com.example.demo32.entity.Employee;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

  @Autowired
  private DepartmentRepository departmentRepository;
  
  @Autowired
  private EmployeeRepository employeeRepository;

  @GetMapping("/{id}")
  public ResponseEntity<Department> getDepartmentById(@PathVariable("id") Long id) {
    Optional<Department> deOptional = departmentRepository.findById(id);
    if (deOptional.isPresent()) {
      return ResponseEntity.ok(deOptional.get());
    }
    return ResponseEntity.notFound().build();
  }

  @GetMapping("/{id}/employees")
  public List<Employee> getAllEmployeesByDepartmentId(@PathVariable Long id) {
    Department department = departmentRepository.findById(id).orElseThrow();
    return department.getEmployees();
  }

  @PostMapping
  public Department createEmployee(@RequestBody Department department) {
    return departmentRepository.save(department);
  }

  @PostMapping("/{departmentId}/employees")
  public Department addEmployeeToDepartment(@PathVariable Long departmentId, @RequestBody Employee employee) {
    Department department = departmentRepository.findById(departmentId).orElseThrow();
    if (department == null) {
      throw new NotFoundException("Department not found");
    }
    employee.setDepartment(department);
    employeeRepository.save(employee);
    department.getEmployees().add(employee);

    return departmentRepository.save(department);
  }

}
