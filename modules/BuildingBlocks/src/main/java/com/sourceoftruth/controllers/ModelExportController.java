package com.sourceoftruth.controllers;

import com.structurizr.Workspace;
import com.structurizr.model.Component;
import com.structurizr.model.Container;
import com.structurizr.model.Model;
import com.structurizr.model.Person;
import com.structurizr.model.SoftwareSystem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/workspace")
public class ModelExportController {

    @GetMapping
    public Workspace get() {
        Workspace workspace = new Workspace("1 Source Of Truth", "A application where we create a complete Documentation model for an entire company");
        Model model = workspace.getModel();

        Person developer = model.addPerson("Developer", "documents their codebase using");
        SoftwareSystem softwareSystem = model.addSoftwareSystem("Source Of Truth", "Analyze a codebase and customized views");
        Container frontend = softwareSystem.addContainer("Frontend", "Frontend that creates a customized view per user", "React frontend");
        Container backend = softwareSystem.addContainer("Backend", "Backend that analyzes a codebase and can report on the results", "Multi Maven module project");
        Component requirementAnalyzer = backend.addComponent("RequirementAnalyzer", "Analyzes the codebase and reads test-reports and reports on the results", "Java/Spring");
        Component buildingBlocks = backend.addComponent("BuildingBlocks", "Analyzes the codebase and generates a Structurizr model", "Maven module Java/Spring");
        Component launcher = backend.addComponent("Launcher", "Launches the application and is used for common property definitions", "Maven module Java/Spring");

        developer.uses(softwareSystem, "Uses", "Rest");
        frontend.uses(backend, "Get information from", "HTTP/Rest");
        requirementAnalyzer.uses(launcher, "Is added to the runtime classpath using", "Spring", null, new String[]{});
        buildingBlocks.uses(launcher, "Is added to the runtime classpath using", "Spring", null, new String[]{});

        return workspace;
    }
}
