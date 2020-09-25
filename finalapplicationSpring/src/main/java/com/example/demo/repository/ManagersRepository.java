package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Managers;


@Repository
public interface ManagersRepository extends JpaRepository<Managers, String> {
	
	boolean existsByEmail(String email);
	
	Managers findByEmail(String email);
	
	List<Managers> findAllByAccountStatus(String accountStatus);

}
