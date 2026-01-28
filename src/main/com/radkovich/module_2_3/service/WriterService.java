package main.com.radkovich.module_2_3.service;

import main.com.radkovich.module_2_3.model.Writer;
import main.com.radkovich.module_2_3.repository.WriterRepository;

import java.util.List;

public class WriterService {

    private final WriterRepository writerRepository;

    public WriterService(WriterRepository writerRepository) {
        this.writerRepository = writerRepository;
    }

    public Writer getWriterById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Writer id must be positive!");
        }
        return writerRepository.getById(id);
    }

    public List<Writer> getAllWriters() {
        return writerRepository.getAll();
    }

    public Writer saveWriter(Writer writer) {
        validateWriter(writer);
        return writerRepository.save(writer);
    }

    public Writer updateWriter(Writer writer) {
        if (writer.getId() <= 0) {
            throw new IllegalArgumentException("Writer id must be positive for update!");
        }
        validateWriter(writer);
        return writerRepository.update(writer);
    }

    public void deleteWriterById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Writer id must be positive for delete!");
        }
        writerRepository.deleteById(id);
    }

    private void validateWriter(Writer writer) {
        if (writer == null) {
            throw new IllegalArgumentException("Writer cannot be null!");
        }
        if (writer.getFirstName() == null || writer.getFirstName().trim().isEmpty()) {
            throw new IllegalArgumentException("Writer first name cannot be empty!");
        }
        if (writer.getLastName() == null || writer.getLastName().trim().isEmpty()) {
            throw new IllegalArgumentException("Writer last name cannot be empty!");
        }
        if (writer.getStatus() == null) {
            throw new IllegalArgumentException("Writer status cannot be null!");
        }
    }
}
