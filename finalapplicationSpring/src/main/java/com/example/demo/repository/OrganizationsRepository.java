package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Managers;
import com.example.demo.entity.Organizations;

public interface OrganizationsRepository extends JpaRepository<Organizations , String>{
	
	
	

}
