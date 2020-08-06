package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Clients;
import com.example.demo.entity.Orders;


@Repository
public interface OrdersRepository extends JpaRepository<Orders , Long>{
	
	Orders findByIdOrder(Long idOrder);
	
	List<Orders> findByIdClient(Long idClient);
	List<Orders> findByIdManager(String idManager);
	
	List<Orders> findByIdDeliveryMan(String idDeliveryMan);
	
	
	
	

}
