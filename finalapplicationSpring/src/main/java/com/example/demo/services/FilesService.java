package com.example.demo.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Files;
import com.example.demo.repository.FilesRepository;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FilesService {



    @Autowired
    private FilesRepository dbFileRepository;

    public Files storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            
			try {
				Files dbFile = new Files(fileName, file.getContentType(), file.getBytes());
				
				 return dbFileRepository.save(dbFile);
				
			} catch (IOException e) {
				System.out.println("Error characters");
				
			}
			
			return null;

           
    }
    

   public Files getFile(String idFile) {
	   return dbFileRepository.findByIdFile(idFile);
   }
   
}