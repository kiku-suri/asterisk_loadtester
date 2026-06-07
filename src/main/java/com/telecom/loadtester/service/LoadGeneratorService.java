package com.telecom.loadtester.service;

import com.telecom.loadtester.model.LoadGenerator;
import com.telecom.loadtester.repository.LoadGeneratorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoadGeneratorService {

    private final LoadGeneratorRepository repository;

    public LoadGeneratorService(
            LoadGeneratorRepository repository) {

        this.repository = repository;
    }

    public List<LoadGenerator> getAll() {
        return repository.findAll();
    }

    public LoadGenerator save(
            LoadGenerator generator) {

        return repository.save(generator);
    }

    public void delete(
            Long id) {

        repository.deleteById(id);
    }

    public LoadGenerator get(Long id) {
    return findById(id);
    }
    
    public LoadGenerator findById(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                            "Generator not found"));
    }
}
