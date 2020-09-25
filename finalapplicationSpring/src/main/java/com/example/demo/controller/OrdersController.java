package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;


import com.example.demo.entity.Clients;
import com.example.demo.entity.DeliveryMen;
import com.example.demo.entity.Managers;
import com.example.demo.entity.Orders;
import com.example.demo.entity.Organizations;
import com.example.demo.entity.Products;
import com.example.demo.repository.ClientsRepository;
import com.example.demo.repository.DeliveryManRepository;
import com.example.demo.repository.ManagersRepository;
import com.example.demo.repository.OrdersRepository;
import com.example.demo.repository.OrganizationsRepository;
import com.example.demo.repository.ProductsRepository;

@RestController
@CrossOrigin
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
	private ProductsRepository productsRepos;
	
	
	@Autowired 
	private OrganizationsRepository orgRrepo;
	
	
	
	

	@PostMapping(path="orders/client/create/{idClient}")
	public ResponseEntity<?> createOrderClient(@Valid @RequestBody Orders order, @PathVariable Long idClient){
		
		Set<Products> products=new HashSet<>();
		
		double price =0; 
		
		
		Clients client=clientsRepository.findByPhone(idClient);
		order.setIdClient(client.getPhone());
		
		for (Products p: order.getProducts_orders()) {
			
			Products product = productsRepos.findByIdProduct(p.getIdProduct());
			price = price + product.getPriceProduct();			
			products.add(product);			
			System.out.println(product.getLabel() + product.getPriceProduct()+ product.getIdProduct());
			
		
		}
		
		order.setTotalPrice(price);
		order.setOrderStatus("Pending");
		order.setDateOrder(new Date());		
		order.setProducts_orders(products);
		Orders newOrder = ordersRepository.save(order);
		
		return new ResponseEntity<Orders>(newOrder,HttpStatus.CREATED);
	}
	
	
	
	@PutMapping(path="orders/manager/create/{idOrder}/{idManager}")
	public ResponseEntity<?> createOrderManager(@Valid @RequestBody Orders order,@PathVariable Long idOrder,@PathVariable String idManager){
		
		Orders foundOrder= ordersRepository.findByIdOrder(idOrder);
		
		order.setIdClient(foundOrder.getIdClient());
		order.setOrderStatus("Accepted by a Manager");
		order.setTotalPrice(foundOrder.getTotalPrice());
		order.setIdOrder(idOrder);		
		order.setIdManager(idManager);
		order.setProducts_orders(foundOrder.getProducts_orders());
      
		final Orders updatedOrder = ordersRepository.save(order);
		
		return new ResponseEntity<Orders>(updatedOrder,HttpStatus.OK);
		
		
	}
	
	@PutMapping(path="orders/manager/request/{idOrder}/{idManager}/{idDeliveryMan}")
	public ResponseEntity<?> requestDeliveryMan(@Valid @RequestBody Orders order,@PathVariable Long idOrder,@PathVariable String idManager,
			@PathVariable String idDeliveryMan){
		
		Orders foundOrder= ordersRepository.findByIdOrder(idOrder);
		DeliveryMen dman = deliverymanRepo.findByEmail(idDeliveryMan);
			
		
		order.setIdClient(foundOrder.getIdClient());
		order.setOrderStatus("Requested a Delivery Man");
		dman.setOrderStatus("Requested for a Delivery");
		order.setTotalPrice(foundOrder.getTotalPrice());
		order.setIdOrder(idOrder);		
		order.setIdManager(idManager);
		order.setProducts_orders(foundOrder.getProducts_orders());
      
		final Orders updatedOrder = ordersRepository.save(order);
		
		return new ResponseEntity<Orders>(updatedOrder,HttpStatus.OK);
		
		
	}
	
	@PutMapping(path="orders/dman/create/{idOrder}/{idDeliveryMan}")
	public ResponseEntity<?> createOrderDeliveryMan(@Valid @RequestBody Orders order,@PathVariable Long idOrder,@PathVariable String idDeliveryMan){
		
		DeliveryMen dman = deliverymanRepo.findByEmail(idDeliveryMan);
		Orders foundOrder= ordersRepository.findByIdOrder(idOrder);
		foundOrder.setIdClient(foundOrder.getIdClient());
		foundOrder.setIdManager(foundOrder.getIdManager());
		foundOrder.setOrderStatus("Accepted by a Delivery Man");
		foundOrder.setTotalPrice(foundOrder.getTotalPrice());
		foundOrder.setIdOrder(foundOrder.getIdOrder());		
		foundOrder.setIdDeliveryMan(idDeliveryMan);
		dman.setOrderStatus("On their way to recover a delivery");
		 
		final Orders updatedOrder = ordersRepository.save(foundOrder);
		
		return new ResponseEntity<Orders>(updatedOrder,HttpStatus.OK);
		
		
	}
	
	@PutMapping(path="orders/dman/endofdelivery/{idOrder}/{idDeliveryMan}")
	public ResponseEntity<?> triggerEndOfDelivery(@Valid @RequestBody Orders order,@PathVariable Long idOrder,@PathVariable String idDeliveryMan){
		
		DeliveryMen dman = deliverymanRepo.findByEmail(idDeliveryMan);
		
		Orders foundOrder= ordersRepository.findByIdOrder(idOrder);
		foundOrder.setIdClient(foundOrder.getIdClient());
		foundOrder.setIdManager(foundOrder.getIdManager());
		foundOrder.setOrderStatus("Delivered");
		foundOrder.setTotalPrice(foundOrder.getTotalPrice());
		foundOrder.setIdOrder(foundOrder.getIdOrder());		
		foundOrder.setIdDeliveryMan(idDeliveryMan);
		dman.setOrderStatus("Available for Work");
		 
		final Orders updatedOrder = ordersRepository.save(foundOrder);
		
		return new ResponseEntity<Orders>(updatedOrder,HttpStatus.OK);
		
		
	}
	
	@PutMapping(path="orders/dman/refuse/{idOrder}/{idDeliveryMan}")
	public ResponseEntity<?> refuseAnOrder(@Valid @RequestBody Orders order,@PathVariable Long idOrder,@PathVariable String idDeliveryMan){
		
		Orders foundOrder= ordersRepository.findByIdOrder(idOrder);
		foundOrder.setIdClient(foundOrder.getIdClient());
		foundOrder.setIdManager(foundOrder.getIdManager());
		foundOrder.setOrderStatus("Refused by a Delivery Man");
		foundOrder.setTotalPrice(foundOrder.getTotalPrice());
		foundOrder.setIdOrder(foundOrder.getIdOrder());		
		foundOrder.setIdDeliveryMan(idDeliveryMan);
		 
		final Orders updatedOrder = ordersRepository.save(foundOrder);		
		return new ResponseEntity<Orders>(updatedOrder,HttpStatus.OK);
		
		
	}
	
	
	@PutMapping(path="orders/manager/refuse/{idOrder}/{idManager}")
	public ResponseEntity<?> refuseOrderManager(@Valid @RequestBody Orders order,@PathVariable Long idOrder,@PathVariable String idManager){
		
		Orders foundOrder= ordersRepository.findByIdOrder(idOrder);
		foundOrder.setIdClient(foundOrder.getIdClient());
		foundOrder.setOrderStatus("Refused by a Manager");
		foundOrder.setTotalPrice(foundOrder.getTotalPrice());
		foundOrder.setIdOrder(idOrder);		
		foundOrder.setIdManager(idManager);
		 
		final Orders updatedOrder = ordersRepository.save(foundOrder);
		
		return new ResponseEntity<Orders>(updatedOrder,HttpStatus.OK);
		
		
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
	
	@GetMapping("orders/orderslist/org/{idOrganization}")
	public Managers getManagerFitsOrder(@PathVariable Long idOrganization) {
		
		Organizations org= orgRrepo.findByIdOrganization(idOrganization);
		String emailManager = org.getIdManager();
		
		Managers manager= managersRepository.findByEmail(emailManager);
		
		return manager;
	}
	
	
	
	@GetMapping("orders/orderslist/deliveryman/{idDeliveryMan}")
	public List<Orders> getOrdersBySpecificDeliveryMan(@PathVariable String idDeliveryMan) {
		
		return ordersRepository.findByIdDeliveryMan(idDeliveryMan);
	}
	
	
		
	@GetMapping("orders/orderslist/pending")
	public List<Orders> getAllOrders(){
		
	final String status = "Pending";
	
    List<Orders> ordersList = new ArrayList<Orders>();
		
    ordersRepository.findAllByOrderStatus(status).forEach(ordersList :: add);
		
	return ordersList;
	}
		
		
	@GetMapping("orders/orderslist/requested")
	public List<Orders> getAllOrdersRequestedADeliveryMan(){
		
	final String status = "Requested a Delivery Man";
	
    List<Orders> ordersList = new ArrayList<Orders>();
		
    ordersRepository.findAllByOrderStatus(status).forEach(ordersList :: add);
		
	return ordersList;
	
	}
	
	@GetMapping("orders/orderslist/inqueue")
	public List<Orders> getAllOrdersInQueue(){
		
	final String status = "Accepted by a Delivery Man";
	
    List<Orders> ordersList = new ArrayList<Orders>();
		
    ordersRepository.findAllByOrderStatus(status).forEach(ordersList :: add);
		
	return ordersList;
	
	}
		
	
		
		

}
