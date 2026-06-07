package com.telecom.loadtester.service;

import com.telecom.loadtester.model.LoadGenerator;
import org.springframework.stereotype.Service;

@Service
public class GeneratorValidationService {

    private final SshExecutionService sshService;

    public GeneratorValidationService(
            SshExecutionService sshService) {

        this.sshService = sshService;
    }

    public boolean validateGenerator(
            LoadGenerator generator) {

        try {

            String result =
                    sshService.executeCommand(
                            generator.getIpAddress(),
                            generator.getSshPort(),
                            generator.getSshUsername(),
                            generator.getSshPassword(),
                            "hostname");

            return !result.isBlank();

        } catch (Exception ex) {

            return false;
        }
    }

    public boolean verifySippInstalled(
            LoadGenerator generator) {

        try {

            String result =
                    sshService.executeCommand(
                            generator.getIpAddress(),
                            generator.getSshPort(),
                            generator.getSshUsername(),
                            generator.getSshPassword(),
                            "which sipp");

            return result.contains("sipp");

        } catch (Exception ex) {

            return false;
        }
    }
}
