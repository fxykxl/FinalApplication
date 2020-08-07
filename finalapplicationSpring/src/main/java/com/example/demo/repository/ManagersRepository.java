package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Managers;



public interface ManagersRepository extends JpaRepository<Managers, String> {
	
	boolean existsByEmail(String email);
	
	Managers findByEmail(String email);
	
	List<Managers> findAllByAccountStatus(String accountStatus);

}
