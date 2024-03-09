package com.hdbank.convertMTtoMXproject.controller;

import com.hdbank.convertMTtoMXproject.service.IFileDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileDataController {
    @Autowired
    private IFileDataService iFileDataService;

    @PostMapping
    public ResponseEntity<?> uploadFile(@RequestParam MultipartFile file) throws IOException {
        String uploadFile = iFileDataService.uploadFile(file);
        return new ResponseEntity<>(uploadFile, HttpStatus.OK);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadFile(@PathVariable String fileName) throws IOException {
        Resource resource = iFileDataService.downloadFile(fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }
}
