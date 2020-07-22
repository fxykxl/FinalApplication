package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Admins;
import com.example.demo.entity.Clients;
import com.example.demo.entity.DeliveryMen;
import com.example.demo.entity.Managers;
import com.example.demo.entity.Organizations;
import com.example.demo.functions.AdminsFunctionsImpl;
import com.example.demo.functions.ClientsFunctionsImpl;
import com.example.demo.functions.DeliveryManFunctionsImpl;
import com.example.demo.functions.ManagersFunctionsImpl;
import com.example.demo.payload.LoginRequest;
import com.example.demo.repository.AdminsRepository;
import com.example.demo.repository.ClientsRepository;
import com.example.demo.repository.DeliveryManRepository;
import com.example.demo.repository.ManagersRepository;
import com.example.demo.repository.OrganizationsRepository;



@CrossOrigin
@RestController
public class AdminsController {
	
	@Autowired
	private ClientsFunctionsImpl clientsFunctionsImpl;
	
	@Autowired
	private ClientsRepository clientsRepository;
	
	@Autowired
	private AdminsFunctionsImpl adminsfunctionsimpl;
	
	@Autowired
	private AdminsRepository adminsRepository;
	
	@Autowired
	private DeliveryManRepository deliverymenRepository;
	
	@Autowired
	private DeliveryManFunctionsImpl deliveryMenFuncImpl;
	
	@Autowired 
	private ManagersRepository managersRepository;
	
	@Autowired
	private OrganizationsRepository organizationsRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	

	@PostMapping(path="admin/clientslist/create")
    public ResponseEntity<?> createClient(@Valid @RequestBody Clients client , BindingResult result) {
		
		if(result.hasErrors()) {
			
			Map<String , String> errorMap = new HashMap<>();
			for(FieldError error : result.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<Map<String , String>>(errorMap ,HttpStatus.BAD_REQUEST );
		}
		
		if(clientsRepository.existsByPhone(client.getPhone())) {
			return new ResponseEntity<String>("Phone Already Exists",HttpStatus.BAD_REQUEST);
		}
		
		if(clientsRepository.existsByEmail(client.getEmail())) {
			return new ResponseEntity<>("Email Already Exists",HttpStatus.BAD_REQUEST);
		}
				
		
			client.setPasswordClient(bCryptPasswordEncoder.encode(client.getPasswordClient()));	
			
			client.setPhone(client.getPhone());
			
			Clients client1 = clientsRepository.save(client);					
			
			return new ResponseEntity<Clients>(client1,HttpStatus.CREATED);
			

		
    }
	
	
	
	@GetMapping(path="admin/clientslist")
	public List<Clients> getAllClients(){
		
		List<Clients> clientslist = new ArrayList<Clients>();
		
		clientsRepository.findAll().forEach(clientslist :: add);
		
		return clientslist;
		
	}
	
	
	@PutMapping(path="admin/clientslist/update/{phone}")
	public ResponseEntity<?> updateClient(@PathVariable Long phone,@Valid @RequestBody Clients client, BindingResult result){
		
       if(result.hasErrors()) {
			
			Map<String , String> errorMap = new HashMap<>();
			for(FieldError error : result.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			
			return new ResponseEntity<Map<String , String>>(errorMap ,HttpStatus.BAD_REQUEST );
            
    }

		
		Clients newClient= clientsRepository.findByPhone(phone);
		
    	newClient.setFirstName(client.getFirstName());
    	newClient.setLastName(client.getLastName());
    	newClient.setAddressClient(client.getAddressClient());
    	newClient.setBirthDate(client.getBirthDate());
	    newClient.setEmail(client.getEmail());
    	newClient.setInscriptionDate(client.getInscriptionDate());
    	newClient.setPasswordClient(client.getPasswordClient());
	
		 
		final Clients updatedClient= clientsRepository.save(newClient);
		return new ResponseEntity<Clients>(updatedClient,HttpStatus.OK); 
		
		
	}
	
	
	
	@DeleteMapping(path="admin/clientslist/delete/{phone}")
	public void deleteClient(@PathVariable Long phone) {
		
		Clients clientToBeDeleted = clientsFunctionsImpl.AfficherClient(phone).get(0);
		adminsfunctionsimpl.DeleteClient(clientToBeDeleted);
		
	}
	
	
	@GetMapping("admin/clientslist/{phone}")
	public Clients getSpecificClient(@PathVariable Long phone) {
		return clientsFunctionsImpl.AfficherClient(phone).get(0);
	}
	
	
	//Admins Functions
	
	
	@PostMapping(path="admin/create")
    public ResponseEntity<?> createAdmin(@Valid @RequestBody Admins admin, BindingResult result) {
	    if(result.hasErrors()) {
			
				Map<String , String> errorMap = new HashMap<>();
				for(FieldError error : result.getFieldErrors()) {
					errorMap.put(error.getField(), error.getDefaultMessage());
				}
				
				return new ResponseEntity<Map<String , String>>(errorMap ,HttpStatus.BAD_REQUEST );
	            
	    }
	    
	    if(adminsRepository.existsByEmail(admin.getEmail())) {
	    	return new ResponseEntity<>("Email Already Exists" ,HttpStatus.BAD_REQUEST );
	    }
        
	    admin.setPasswordAdmin(bCryptPasswordEncoder.encode(admin.getPasswordAdmin()));
	    
	    admin.setEmail(admin.getEmail());
	    
	    Admins adminNew= adminsRepository.save(admin);	    
	    return new ResponseEntity<Admins>(adminNew,HttpStatus.CREATED);
	    
	    
	
				
    }
	
	
	
	
	@PostMapping("/admin/signin")
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){
    if(result.hasErrors()) {
			
			Map<String , String> errorMap = new HashMap<>();
			for(FieldError error : result.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			
			return new ResponseEntity<Map<String , String>>(errorMap ,HttpStatus.BAD_REQUEST );
            
    }
			
			Admins admin= adminsRepository.findByEmail(loginRequest.getEmail());			
			if(bCryptPasswordEncoder.matches(loginRequest.getPassword() ,admin.getPasswordAdmin())) {
				
				return ResponseEntity.ok("Logged in Successfully");
			
			}
						
			return new ResponseEntity<>("There have been Errors" ,HttpStatus.BAD_REQUEST );
			
			
			
    }
	
	
	
	@PutMapping(path="admin/update/{email}")
	public ResponseEntity<?> updateAdmin(@PathVariable String email,@Valid @RequestBody Admins admin, BindingResult result){
		
       if(result.hasErrors()) {
			
			Map<String , String> errorMap = new HashMap<>();
			for(FieldError error : result.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			
			return new ResponseEntity<Map<String , String>>(errorMap ,HttpStatus.BAD_REQUEST );
            
    }
		
		Admins newAdmin= adminsRepository.findByEmail(email);		
		newAdmin.setFirstName(admin.getFirstName());
		newAdmin.setLastName(admin.getLastName());
		newAdmin.setAddressAdmin(admin.getAddressAdmin());
		newAdmin.setBirthDate(admin.getBirthDate());
		newAdmin.setPhone(admin.getPhone());
		newAdmin.setInscriptionDate(admin.getInscriptionDate());
		newAdmin.setPasswordAdmin(admin.getPasswordAdmin());			
		 
		final Admins updatedAdmin= adminsRepository.save(newAdmin);
		return new ResponseEntity<Admins>(updatedAdmin,HttpStatus.OK); 
		
		
	}
	

	
	
	//.....................................DeliveryMen Functions.............................................	
	
	@PostMapping(path="admin/deliverymenlist/create")
    public ResponseEntity<?> createDeliveryMan(@Valid @RequestBody DeliveryMen deliveryMan,BindingResult result) {				
        
	     if(result.hasErrors()) {
				
				Map<String , String> errorMap = new HashMap<>();
				for(FieldError error : result.getFieldErrors()) {
					errorMap.put(error.getField(), error.getDefaultMessage());
				}
				return new ResponseEntity<Map<String , String>>(errorMap ,HttpStatus.BAD_REQUEST );
			}
				
			
			if(deliverymenRepository.existsByEmail(deliveryMan.getEmail())) {
				return new ResponseEntity<>("Email Already Exists",HttpStatus.BAD_REQUEST);
			}
					
			
			deliveryMan.setPasswordDeliverMan(bCryptPasswordEncoder.encode(deliveryMan.getPasswordDeliverMan()));	
				
			deliveryMan.setPhone(deliveryMan.getPhone());
				
			DeliveryMen deliveryMan2 = deliverymenRepository.save(deliveryMan);					
				
			return new ResponseEntity<DeliveryMen>(deliveryMan2,HttpStatus.CREATED);
				

			
	    }
	
	
	@GetMapping(path="admin/deliverymenlist")
	public List<DeliveryMen> getAllDeliveryMen(){
		
		List<DeliveryMen> deliveryMenList = new ArrayList<DeliveryMen>();
		
		deliverymenRepository.findAll().forEach(deliveryMenList :: add);
		
		return deliveryMenList;
		
	}
	
	
	@PutMapping(path="admin/deliverymenlist/update/{email}")
	public ResponseEntity<?> updateDeliveryMan(@PathVariable String email,@Valid @RequestBody DeliveryMen deliveryMan, BindingResult result){
		
	       if(result.hasErrors()) {
				
				Map<String , String> errorMap = new HashMap<>();
				for(FieldError error : result.getFieldErrors()) {
					errorMap.put(error.getField(), error.getDefaultMessage());
				}
				
				return new ResponseEntity<Map<String , String>>(errorMap ,HttpStatus.BAD_REQUEST );
	            
	    }

			
	       DeliveryMen newDeliveryMan= deliverymenRepository.findByEmail(deliveryMan.getEmail());
			
	       newDeliveryMan.setAddressDeliveryMan(deliveryMan.getAddressDeliveryMan());
	       newDeliveryMan.setBirthDate(deliveryMan.getBirthDate());
	       newDeliveryMan.setEmail(email);
	       newDeliveryMan.setFirstName(deliveryMan.getFirstName());
	       newDeliveryMan.setLastName(deliveryMan.getLastName());
	       newDeliveryMan.setPasswordDeliverMan(deliveryMan.getPasswordDeliverMan());
	       newDeliveryMan.setPhone(deliveryMan.getPhone());
	
			 
			final DeliveryMen updateDeliveryMan= deliverymenRepository.save(newDeliveryMan);
			
		
			return new ResponseEntity<DeliveryMen>(updateDeliveryMan,HttpStatus.OK); 
			
			
		}
	
	
	@DeleteMapping(path="admin/deliverymenlist/delete/{email}")
	public void deleteDeleveryMan(@PathVariable String email) {
		
		DeliveryMen deliveryManToBeDeleted = deliveryMenFuncImpl.AfficherDeliveryMan(email).get(0);
		
		adminsfunctionsimpl.DeleteDeliveryMan(deliveryManToBeDeleted);
		
	}
	
	
	@GetMapping("admin/deliverymenlist/{email}")
	public DeliveryMen getSpecificDeliveryMan(@PathVariable String email) {
		
		return deliveryMenFuncImpl.AfficherDeliveryMan(email).get(0);
		
	}
	
	
	//.......................................Managers Functions.......................................
	
	
	@PostMapping(path="admin/managerslist/create")
	public ResponseEntity<?> createManagers(@Valid @RequestBody Managers manager, BindingResult result) {
		 if(result.hasErrors()) {
				
				Map<String , String> errorMap = new HashMap<>();
				for(FieldError error : result.getFieldErrors()) {
					errorMap.put(error.getField(), error.getDefaultMessage());
				}
				
				return new ResponseEntity<Map<String , String>>(errorMap ,HttpStatus.BAD_REQUEST );
	            
	    }
		 
		if(managersRepository.existsByEmail(manager.getEmail())) {
				return new ResponseEntity<>("Email Already Exists",HttpStatus.BAD_REQUEST);
		}
							
		manager.setPasswordManager(bCryptPasswordEncoder.encode(manager.getPasswordManager()));			
		managersRepository.save(manager);
		
		return new ResponseEntity<Managers>(manager,HttpStatus.CREATED);
		
       }

	
	@GetMapping(path="admin/managerslist")
	public List<Managers> getAllManagers(){
		
		List<Managers> managersList = new ArrayList<Managers>();
		
		managersRepository.findAll().forEach(managersList :: add);
		
		return managersList;
		
	}
	
	
	@PutMapping(path="admin/managerslist/update/{email}")
	public ResponseEntity<?> updateManager(@PathVariable String email,@Valid @RequestBody Managers manager, BindingResult result){
		
	       if(result.hasErrors()) {
				
				Map<String , String> errorMap = new HashMap<>();
				for(FieldError error : result.getFieldErrors()) {
					errorMap.put(error.getField(), error.getDefaultMessage());
				}
				
				return new ResponseEntity<Map<String , String>>(errorMap ,HttpStatus.BAD_REQUEST );
	            
	    }

			
			Managers newManager= managersRepository.findByEmail(manager.getEmail());
			
			newManager.setAddressManager(manager.getAddressManager());
			newManager.setBirthDate(manager.getBirthDate());
			newManager.setEmail(email);
			newManager.setFirstName(manager.getFirstName());
			newManager.setLastName(manager.getLastName());
			newManager.setPasswordManager(manager.getPasswordManager());
			newManager.setPhone(manager.getPhone());
			newManager.setProname(manager.getProname());
			 
			final Managers updateManager= managersRepository.save(newManager);
			
		
			return new ResponseEntity<Managers>(updateManager,HttpStatus.OK); 
			
			
		}
	
	
	@DeleteMapping(path="admin/managerslist/delete/{email}")
	public void deleteManager(@PathVariable String email) {
		
		Managers managerToBeDeleted = managersRepository.findByEmail(email);
		
		adminsfunctionsimpl.DeleteManager(managerToBeDeleted);
		
	}
	
	
	@GetMapping("admin/managerslist/{email}")
	public Managers getSpecificManager(@PathVariable String email) {
		
		return managersRepository.findByEmail(email);
		
	}
	
	
	//.....................................Organizations functions ...................................
	
	@PostMapping("admin/organization/organizationslist/create/{idManager}")
	public ResponseEntity<?> createOrganization(@Valid @RequestBody Organizations organization, @PathVariable String idManager ,BindingResult result) {
		 if(result.hasErrors()) {
				
				Map<String , String> errorMap = new HashMap<>();
				for(FieldError error : result.getFieldErrors()) {
					errorMap.put(error.getField(), error.getDefaultMessage());
				}
				
				return new ResponseEntity<Map<String , String>>(errorMap ,HttpStatus.BAD_REQUEST );
	            
	    }	
		 
		 Managers manager= managersRepository.findByEmail(idManager);
		 if(manager==null) {
			 return new ResponseEntity<String>("You don't have the privilege",HttpStatus.BAD_REQUEST);
		 }
		 
		organization.setIdManager(manager.getEmail());		
		organizationsRepository.save(organization);
		
		return new ResponseEntity<Organizations>(organization,HttpStatus.CREATED);
   }
	
	@PutMapping("admin/organization/organizationslist/update/{idManager}/{idOrganization}")
	public ResponseEntity<?> updateOrganization(@Valid @RequestBody Organizations organization, @PathVariable Long idOrganization ,@PathVariable String idManager ,BindingResult result) {
		 if(result.hasErrors()) {
				
				Map<String , String> errorMap = new HashMap<>();
				for(FieldError error : result.getFieldErrors()) {
					errorMap.put(error.getField(), error.getDefaultMessage());
				}
				
				return new ResponseEntity<Map<String , String>>(errorMap ,HttpStatus.BAD_REQUEST );
	            
	    }
		 
		 Managers manager= managersRepository.findByEmail(idManager);
		 
		 if(manager==null) {
			 return new ResponseEntity<String>("You don't have the privilege",HttpStatus.BAD_REQUEST);
		 }
		 
		 Organizations organization2 = organizationsRepository.findByIdOrganization(idOrganization);
		 organization2.setIdManager(idManager);
		 organization2.setDescription(organization.getDescription());
		 organization2.setLabel(organization.getLabel());
		  
		 final Organizations updatedOrganization =  organizationsRepository.save(organization2);

			
		return new ResponseEntity<Organizations>(updatedOrganization,HttpStatus.OK); 

  }
	
	@DeleteMapping(path="admin/organization/organizationslist/delete/{idOrganization}")
	public void deleteOrganization(@PathVariable Long idOrganization) {
		
		Organizations organizationToBeDeleted = organizationsRepository.findByIdOrganization(idOrganization);
		
		organizationsRepository.delete(organizationToBeDeleted);
		
		
	}

	@GetMapping(path="admin/organization/organizationslist")
	public List<Organizations> getAllOrganizations(){
		
		List<Organizations> organizationsList = new ArrayList<Organizations>();
		
		organizationsRepository.findAll().forEach(organizationsList :: add);
		
		return organizationsList;
		
	}

}
