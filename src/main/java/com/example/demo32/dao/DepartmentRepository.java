package com.example.demo32.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo32.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
  
}
