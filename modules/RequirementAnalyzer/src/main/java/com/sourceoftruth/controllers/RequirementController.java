package com.sourceoftruth.controllers;

import com.sourceoftruth.model.Testcase;
import com.sourceoftruth.service.TestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/requirements")
public class RequirementController {

    private final TestResultService testResultService;

    @Autowired
    RequirementController(TestResultService testResultService){

        this.testResultService = testResultService;
    }

    @GetMapping
    public List<Requirement> get() throws IOException {
        Map<String, Testcase> requirements = new HashMap<>();

        testResultService.getTestCases()
                .forEach(tc -> requirements.put(tc.name(), tc));

        return requirements.entrySet().stream()
                        .map(entry -> new Requirement(entry.getKey(), entry.getValue()))
                        .toList();

    }

    record Sot(List<Requirement> requirements){}
    record Requirement(String title, Testcase testcase){}
}
