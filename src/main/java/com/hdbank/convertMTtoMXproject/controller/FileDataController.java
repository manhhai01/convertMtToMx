package com.hdbank.convertMTtoMXproject.controller;

import com.hdbank.convertMTtoMXproject.payload.response.BaseResponse;
import com.hdbank.convertMTtoMXproject.service.IFileDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileDataController {
    @Autowired
    private IFileDataService iFileDataService;

    @PostMapping
    public ResponseEntity<?> uploadFile(@RequestParam MultipartFile file) throws IOException {
        String uploadFile = iFileDataService.uploadFile(file);
        String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/file/")
                .path(uploadFile)
                .toUriString();
        return new ResponseEntity<>(BaseResponse.builder()
                .status(200)
                .message("Convert MT to MX successfully.")
                .data(downloadURL)
                .build(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> downloadFile(@PathVariable Long id) throws IOException {
        Resource resource = iFileDataService.downloadFile(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}

