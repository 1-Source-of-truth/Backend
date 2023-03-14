package com.datavisualisation.services;

import org.springframework.stereotype.Service;

import com.structurizr.Workspace;
import com.structurizr.model.Model;

@Service
public class Core {
  Workspace workspace = new Workspace("1 Source Of Truth",
      "A application where we create a complete Documentation model for an entire company");
  Model model = workspace.getModel();

  public Model getModel() {
    return this.model;
  }
}
