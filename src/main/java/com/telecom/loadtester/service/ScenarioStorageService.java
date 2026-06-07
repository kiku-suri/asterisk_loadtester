package com.telecom.loadtester.service;

import com.telecom.loadtester.config.StorageProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.*;

@Service
public class ScenarioStorageService {

    private final StorageProperties properties;

    public ScenarioStorageService(
            StorageProperties properties) {

        this.properties = properties;
    }

    public String saveFile(
            MultipartFile file) throws Exception {

        Path target =
                Paths.get(
                        properties.getScenarioStorage(),
                        file.getOriginalFilename());

        Files.copy(
                file.getInputStream(),
                target,
                StandardCopyOption.REPLACE_EXISTING);

        return target.toString();
    }
}
