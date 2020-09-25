package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.DeliveryMen;



@Repository
public interface DeliveryManRepository extends JpaRepository<DeliveryMen, String>{
	
	boolean existsByEmail(String email);
	
	DeliveryMen findByEmail(String email);
	
	List<DeliveryMen> findAllByAccountStatus(String accountStatus);
	
	List<DeliveryMen> findAllByOrderStatus(String status);

}
