package main.com.radkovich.module_2_3.controller;

import main.com.radkovich.module_2_3.model.Post;
import main.com.radkovich.module_2_3.model.Status;
import main.com.radkovich.module_2_3.model.Writer;
import main.com.radkovich.module_2_3.repository.PostRepository;
import main.com.radkovich.module_2_3.service.WriterService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class WriterController {
    private final WriterService writerService;
    private final PostRepository postRepository;

    public WriterController(WriterService writerService, PostRepository postRepository) {
        this.writerService = writerService;
        this.postRepository = postRepository;
    }

    public Writer createWriter(String firstName, String lastName) {
        Writer newWriter = new Writer(firstName, lastName, new ArrayList<>(), Status.ACTIVE);
        return writerService.saveWriter(newWriter);
    }

    public Writer getWriterById(Long id) {
        return writerService.getWriterById(id);
    }

    public List<Writer> getAllWriters() {
        return writerService.getAllWriters();
    }

    public Writer updateWriterById(Long id, String firstName, String lastName) {
        Writer existingWriter = writerService.getWriterById(id);
        existingWriter.setFirstName(firstName);
        existingWriter.setLastName(lastName);
        return writerService.updateWriter(existingWriter);
    }

    public void deleteWriterById(Long id) {
        writerService.deleteWriterById(id);
    }

    public Post addPostToWriter(Long writerId, String postContent) {
        writerService.getWriterById(writerId);
        Post post = new Post(postContent, LocalDateTime.now(), new HashSet<>(), Status.ACTIVE);
        return postRepository.addPostToWriter(writerId, post);
    }
}
