package com.hdbank.convertMTtoMXproject.service.impl;

import com.hdbank.convertMTtoMXproject.entity.FileData;
import com.hdbank.convertMTtoMXproject.repository.FileDataRepository;
import com.hdbank.convertMTtoMXproject.service.IFileDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.Optional;

@Service
public class FileDataService implements IFileDataService {
    @Autowired
    private FileDataRepository fileDataRepository;

    @Value("${folder.path}")
    private String folderPath;

    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        String fileName = null;
        try {
            Path pathRoot = Paths.get(folderPath);
            if (!Files.exists(pathRoot)) {
                Files.createDirectory(pathRoot);
            }
            Files.copy(file.getInputStream(), pathRoot.resolve(Objects.requireNonNull(file.getOriginalFilename())), StandardCopyOption.REPLACE_EXISTING);
            fileName = String.valueOf(pathRoot.resolve(Objects.requireNonNull(file.getOriginalFilename())));
        } catch (Exception e) {
            e.printStackTrace();
        }
//        FileData fileData = fileDataRepository.save(FileData.builder()
//                .name(file.getOriginalFilename())
//                .type(file.getContentType())
//                .filePath(filePath).build()
//        );

        return fileName;
    }

    @Override
    public Resource downloadFile(String fileName) throws IOException {
        Path path = Paths.get(folderPath);
        Path pathFile = path.resolve(fileName);
        Resource resource = new UrlResource(pathFile.toUri());
        if (resource.exists() || resource.isReadable()) {
            return resource;
        }
        return null;
    }
}
