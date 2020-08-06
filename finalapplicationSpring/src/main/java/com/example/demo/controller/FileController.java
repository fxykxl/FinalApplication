package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.entity.Files;
import com.example.demo.repository.FilesRepository;
import com.example.demo.services.FilesService;
import com.example.demo.services.UploadFileResponse;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class FileController {
	
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	
	@Autowired
    private FilesService dbFileStorageService;
	
	@Autowired
	private FilesRepository filesRepository;
	
	@PostMapping("admin/uploadFile")
    public BodyBuilder uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
		
		Files files = new Files(file.getOriginalFilename(), file.getContentType(),			
				               file.getBytes());
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getContentType());
		
	
		filesRepository.save(files);
	
	    return ResponseEntity.status(HttpStatus.OK);
		
      
    }
	
	
	
	@GetMapping("admin/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
        // Load file from database
        Files dbFile = dbFileStorageService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }

}
