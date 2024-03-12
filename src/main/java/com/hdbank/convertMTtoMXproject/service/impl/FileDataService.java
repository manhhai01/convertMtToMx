package com.hdbank.convertMTtoMXproject.service.impl;

import com.hdbank.convertMTtoMXproject.entity.FileData;
import com.hdbank.convertMTtoMXproject.repository.FileDataRepository;
import com.hdbank.convertMTtoMXproject.service.IFileDataService;
import com.hdbank.convertMTtoMXproject.utils.TranslateMtToMx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileDataService implements IFileDataService {
    @Autowired
    private FileDataRepository fileDataRepository;

    @Value("${folder.path.input}")
    private String folderInputPath;

    @Value("${folder.path.output}")
    private String folderOutputPath;

    @Override
    public String uploadFile(MultipartFile file) {
        try {
            Path pathInputRoot = Paths.get(folderInputPath);
            Path pathOutputRoot = Paths.get(folderOutputPath);
            if (!Files.exists(pathInputRoot)) {
                Files.createDirectory(pathInputRoot);
            }
            if (!Files.exists(pathOutputRoot)) {
                Files.createDirectory(pathOutputRoot);
            }
            String fileName = UUID.randomUUID().toString();
            Files.copy(file.getInputStream(), pathInputRoot.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            FileData fileData = fileDataRepository.save(FileData.builder()
                    .name(file.getOriginalFilename())
                    .type(file.getContentType())
                    .filePath(fileName).build()
            );

            String dataMT = readFile(fileData.getFilePath());
            String dataMX = TranslateMtToMx.translateMt202ToPacs009_Auto(dataMT);
            writeFile(fileData.getFilePath(), dataMX);

            return fileData.getId().toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Resource downloadFile(Long id) throws IOException {
        Optional<FileData> fileData = fileDataRepository.findById(id);
        Path path = Paths.get(folderOutputPath);
        Path pathFile = path.resolve(fileData.get().getFilePath());
        Resource resource = new UrlResource(pathFile.toUri());
        if (resource.exists() || resource.isReadable()) {
            return resource;
        }
        return null;
    }

    private void writeFile(String fileName, String data) {
        try {
            Path pathRoot = Paths.get(folderOutputPath);
            BufferedWriter bw = new BufferedWriter(new FileWriter(pathRoot.resolve(fileName).toFile()));
            bw.write(data);
            bw.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String readFile(String fileName) {
        StringBuilder rsb = new StringBuilder();
        try {
            Path pathRoot = Paths.get(folderInputPath);
            BufferedReader br = new BufferedReader(new FileReader(pathRoot.resolve(fileName).toFile()));
            String line;
            while ((line = br.readLine()) != null) {
                rsb.append(line).append("\n");
            }
            br.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return rsb.toString();
    }
}
