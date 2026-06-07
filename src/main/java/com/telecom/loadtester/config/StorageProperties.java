package com.telecom.loadtester.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StorageProperties {

    @Value("${loadtester.scenario.storage}")
    private String scenarioStorage;

    public String getScenarioStorage() {
        return scenarioStorage;
    }
}
