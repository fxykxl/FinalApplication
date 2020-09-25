package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Clients;
import com.example.demo.entity.DeliveryMen;
import com.example.demo.entity.Managers;
import com.example.demo.entity.Menu;
import com.example.demo.entity.Orders;
import com.example.demo.entity.Organizations;
import com.example.demo.repository.MenusRepository;
import com.example.demo.repository.OrganizationsRepository;

@RestController
@CrossOrigin
public class MenusController {
	
	@Autowired
	private MenusRepository menusRepository;
	
	@Autowired
	private OrganizationsRepository organizationRepo;
	
	@PostMapping("menus/menuslist/create/{idOrganization}")
	public ResponseEntity<?> createMenu(@Valid @RequestBody Menu menu , @PathVariable Long idOrganization ){
		
		Organizations org= organizationRepo.findByIdOrganization(idOrganization);
		if (org == null) {
			return new ResponseEntity<>("Organization Doesn't Exist",HttpStatus.BAD_REQUEST);
		}

		menu.setIdOrganization(idOrganization);
		menusRepository.save(menu);
		
		
		return new ResponseEntity<Menu>(menu,HttpStatus.CREATED);
	}
	
	 @GetMapping("menus/menuslist/{idOrganization}")
     public List<Menu> getAllMenusOrganization(@PathVariable Long idOrganization){
		 
	   Organizations organization = organizationRepo.findByIdOrganization(idOrganization);
		 
		List<Menu> menuslist = new ArrayList<Menu>();
		menuslist = organization.getMenus();		
				
		return menuslist;
	 }
	 
	 @GetMapping("menus/menuslist/id/{idMenu}")
     public Menu getAllMenusByID(@PathVariable Long idMenu){
		 
	   Menu menu=menusRepository.findByIdMenu(idMenu);
		 
			
				
		return menu;
	 }
	
	  @GetMapping(path="menus/menuslist")
	  public List<Menu> getAllMenus(){
			
			List<Menu> clientslist = new ArrayList<Menu>();
			
			menusRepository.findAll().forEach(clientslist :: add);
			
			return clientslist;
			
		}
	  
	  @DeleteMapping(path="menus/menuslist/delete/{idMenu}")
		public ResponseEntity<?> updateOrganization(@PathVariable Long idMenu) {

			Menu menu= menusRepository.findByIdMenu(idMenu);
			
			menusRepository.delete(menu);
			 
			 return new ResponseEntity<Menu>(HttpStatus.OK);
			 
		}

}
