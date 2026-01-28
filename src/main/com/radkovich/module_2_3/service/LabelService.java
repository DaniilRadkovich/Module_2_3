package main.com.radkovich.module_2_3.service;

import main.com.radkovich.module_2_3.model.Label;
import main.com.radkovich.module_2_3.repository.LabelRepository;

import java.util.List;

public class LabelService {
    private final LabelRepository labelRepository;

    public LabelService(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    public Label getLabelById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Label id must be positive!");
        }
        return labelRepository.getById(id);
    }

    public List<Label> getAllLabels() {
        return labelRepository.getAll();
    }

    public Label saveLabel(Label label) {
        validateLabel(label);
        return labelRepository.save(label);
    }

    public Label updateLabel(Label label) {
        if (label.getId() <= 0) {
            throw new IllegalArgumentException("Label id must be positive for update!");
        }
        validateLabel(label);
        return labelRepository.update(label);
    }

    public void deleteLabelById(Long id) {
        if (id == null ||id <= 0) {
            throw new IllegalArgumentException("Label id must be positive for delete!");
        }
        labelRepository.deleteById(id);
    }

    private void validateLabel(Label label) {
        if (label == null) {
            throw new IllegalArgumentException("Label cannot be null!");
        }
        if (label.getName() == null || label.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Label name cannot be empty!");
        }
        if (label.getStatus() == null) {
            throw new IllegalArgumentException("Label status cannot be null!");
        }
    }
}
