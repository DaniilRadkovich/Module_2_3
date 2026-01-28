package main.com.radkovich.module_2_3.controller;

import main.com.radkovich.module_2_3.model.Label;
import main.com.radkovich.module_2_3.model.Status;
import main.com.radkovich.module_2_3.service.LabelService;

import java.util.List;

public class LabelController {
    private final LabelService labelService;

    public LabelController(LabelService labelService) {
        this.labelService = labelService;
    }

    public Label createLabel(String name) {
        Label newLabel = new Label(name, Status.ACTIVE);
        return labelService.saveLabel(newLabel);
    }

    public Label getLabelById(Long id) {
        return labelService.getLabelById(id);
    }

    public List<Label> getAllLabels() {
        return labelService.getAllLabels();
    }

    public Label updateLabel(Long id, String name) {
        Label existingLabel = labelService.getLabelById(id);
        existingLabel.setName(name);
        return labelService.updateLabel(existingLabel);
    }

    public void deleteLabelById(Long id) {
        labelService.deleteLabelById(id);
    }
}