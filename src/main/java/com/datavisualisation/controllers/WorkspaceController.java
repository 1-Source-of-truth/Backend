package com.datavisualisation.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.structurizr.Workspace;
import com.structurizr.export.Diagram;
import com.structurizr.export.plantuml.C4PlantUMLExporter;
import com.structurizr.model.Model;
import com.structurizr.model.Person;
import com.structurizr.model.SoftwareSystem;
import com.structurizr.view.SystemContextView;
import com.structurizr.view.ViewSet;

@RestController
@RequestMapping("api/v1/workspace")
public class WorkspaceController {

  @GetMapping
  public Workspace get() {
    Workspace workspace = new Workspace("1 Source Of Truth",
        "A application where we create a complete Documentation model for an entire company");
    Model model = workspace.getModel();
    Person user = model.addPerson("User", "A user of my software system.");
    SoftwareSystem softwareSystem = model.addSoftwareSystem("Software System", "My software system.");
    user.uses(softwareSystem, "Uses");

    ViewSet views = workspace.getViews();
    SystemContextView contextView = views.createSystemContextView(softwareSystem, "SystemContext",
        "An example of a System Context diagram.");

    contextView.addAllElements();

    C4PlantUMLExporter exporter = new C4PlantUMLExporter();
    var diagrams = exporter.export(workspace);
    diagrams.stream()
        .map(Diagram::getDefinition)
        .forEach(System.out::println);

    return workspace;
  }
}
