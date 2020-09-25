package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Clients;
import com.example.demo.entity.Managers;
import com.example.demo.entity.Menu;
import com.example.demo.entity.Organizations;
import com.example.demo.entity.Products;
import com.example.demo.repository.MenusRepository;
import com.example.demo.repository.ProductsRepository;

@RestController
@CrossOrigin
public class ProductsController {
	
	@Autowired
	private ProductsRepository productsRepo;
	
	@Autowired
	private MenusRepository menusRepos;
	
	@PostMapping(path="/products/create/{idMenu}")
	public ResponseEntity<?> createProduct(@Valid @RequestBody Products products , @PathVariable Long idMenu){
		
			
		Menu menu =menusRepos.findByIdMenu(idMenu);
		
		if(menu != null ) {
			
			ArrayList<Products> productslist = new ArrayList<>();				
			productslist.addAll(menu.getProducts());					
			productslist.add(products);
			System.out.println(products.getLabel());
			System.out.println(productslist.size());
			menu.setProducts(productslist);
			Products newProduct = productsRepo.save(products);
				
			/*ArrayList<Menu> menuList = new ArrayList<Menu>();
			menuList.add(menu);
			products.setMenus(menuList);
			Products newProduct = productsRepo.save(products);*/
			

			return new ResponseEntity<Menu>(menu,HttpStatus.CREATED);
		}
		
		return new ResponseEntity<>("Errors creating a product",HttpStatus.BAD_REQUEST);
		
	} 
	
	
	@GetMapping(path="/products/productslist/{idMenu}")
	public List<Products> getAllProductsForSpecificMenu(@PathVariable Long idMenu){
		
		Menu menu= menusRepos.findByIdMenu(idMenu);
		
        List<Products> productsList = menu.getProducts();
        
	
		
		return productsList;
	}
	
	@GetMapping(path="/products/productslist")
	public List<Products> getAllProducts(){
		
		
        List<Products> productsList = new ArrayList<>();
        
		
        productsRepo.findAll().forEach(productsList :: add);
		
		return productsList;
        
	}
	
	@GetMapping(path="/products/productslist/get/{idProduct}")
	public Products getSpecificProduct (@PathVariable Long idProduct) {
		return productsRepo.findByIdProduct(idProduct);
	}
	
	
	@PutMapping(path="/products/productslist/update/{idProduct}")
	public ResponseEntity<?> updateOrganization(@Valid @RequestBody Products product, @PathVariable Long idProduct) {
	
		

			 
			 product.setDescriptionProduct(product.getDescriptionProduct());
			 product.setLabel(product.getLabel());
			 product.setPriceProduct(product.getPriceProduct());
			 product.setIdProduct(idProduct);

		
			 final Products updatedProduct =  productsRepo.save(product);

				
			return new ResponseEntity<Products>(updatedProduct,HttpStatus.OK); 


 }
	
	

}
