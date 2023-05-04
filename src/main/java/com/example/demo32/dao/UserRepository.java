package com.example.demo32.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.demo32.entity.User1;

@Repository
public interface UserRepository extends JpaRepository<User1, Integer> {

}