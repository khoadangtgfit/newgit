package com.example.demo32.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String firtName;

  private String lastName;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "employee_role", joinColumns = {@JoinColumn(name = "employee_id") }, inverseJoinColumns = {
      @JoinColumn(name = "role_id") })
  @JsonBackReference
  private Set<Role> roles = new HashSet<Role>();

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "department_id")
  @JsonBackReference
  private Department department;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirtName() {
    return firtName;
  }

  public void setFirtName(String firtName) {
    this.firtName = firtName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

}
