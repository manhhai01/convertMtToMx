package com.hdbank.convertMTtoMXproject.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IFileDataService {
    String uploadFile(MultipartFile file) throws IOException;
    Resource downloadFile(Long id) throws IOException;
}
