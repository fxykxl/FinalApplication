package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Admins;
import com.example.demo.entity.Clients;
import com.example.demo.entity.DeliveryMen;
import com.example.demo.entity.Managers;
import com.example.demo.entity.Orders;
import com.example.demo.repository.ClientsRepository;
import com.example.demo.repository.DeliveryManRepository;
import com.example.demo.repository.ManagersRepository;
import com.example.demo.repository.OrdersRepository;

@RestController
public class OrdersController {
	
	@Autowired
	private OrdersRepository ordersRepository;
	
	@Autowired
	private ManagersRepository managersRepository;
	
	@Autowired
	private DeliveryManRepository deliverymanRepo;
	
	@Autowired
	private ClientsRepository clientsRepository;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@PostMapping("orders/create/{idManager}/{idDeliveryMan}/{idClient}")
	public ResponseEntity<?> createOrder(@Valid @RequestBody Orders order ,@PathVariable String idManager 
			,@PathVariable Long idClient, @PathVariable String idDeliveryMan ){
		
		Managers manager= managersRepository.findByEmail(idManager);
		Clients client=clientsRepository.findByPhone(idClient);
		DeliveryMen deliveryMan = deliverymanRepo.findByEmail(idDeliveryMan);
		
		
		order.setIdClient(client.getPhone());
		order.setIdDeliveryMan(deliveryMan.getEmail());
		order.setIdManager(manager.getEmail());
		
		Orders newOrder = ordersRepository.save(order);
		
		return new ResponseEntity<Orders>(newOrder,HttpStatus.CREATED);
	}
	
	
	
	@GetMapping("orders/orderslist/{idOrder}")
	public Orders getSpecificOrder(@PathVariable Long idOrder) {
		return ordersRepository.findByIdOrder(idOrder);
	}
	
	
	
	
	@GetMapping("orders/orderslist/client/{idClient}")
	public List<Orders> getOrdersBySpecificClient(@PathVariable Long idClient) {
				
		return ordersRepository.findByIdClient(idClient);
	}
	
	
	@GetMapping("orders/orderslist/manager/{idManager}")
	public List<Orders> getOrdersBySpecificManager(@PathVariable String idManager) {
		
		return ordersRepository.findByIdManager(idManager);
	}
	
	
	@GetMapping("orders/orderslist/deliveryman/{idDeliveryMan}")
	public List<Orders> getOrdersBySpecificDeliveryMan(@PathVariable String idDeliveryMan) {
		
		return ordersRepository.findByIdDeliveryMan(idDeliveryMan);
	}
	
	
		
	@GetMapping("orders/orderslist")
	public List<Orders> getAllOrders(){
    List<Orders> ordersList = new ArrayList<Orders>();
		
    ordersRepository.findAll().forEach(ordersList :: add);
		
		return ordersList;
	}
		
		
	
		
		

}
