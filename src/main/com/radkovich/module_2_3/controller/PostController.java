package main.com.radkovich.module_2_3.controller;

import main.com.radkovich.module_2_3.model.Label;
import main.com.radkovich.module_2_3.model.Post;
import main.com.radkovich.module_2_3.model.Status;
import main.com.radkovich.module_2_3.repository.PostRepository;
import main.com.radkovich.module_2_3.service.LabelService;
import main.com.radkovich.module_2_3.service.PostService;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

public class PostController {
    private final PostService postService;
    private final LabelService labelService;
    private final PostRepository postRepository;

    public PostController(PostService postService, LabelService labelService, PostRepository postRepository) {
        this.postService = postService;
        this.labelService = labelService;
        this.postRepository = postRepository;
    }

    public Post createPost(String content) {
        Post newPost = new Post(content, LocalDateTime.now(), new HashSet<>(), Status.ACTIVE);
        return postService.savePost(newPost);
    }

    public Post getPostById(Long id) {
        return postService.getPostById(id);
    }

    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    public Post updatePost(Long id, String content) {
        Post existingPost = postService.getPostById(id);
        existingPost.setContent(content);
        existingPost.setUpdated(LocalDateTime.now());
        return postService.updatePost(existingPost);
    }

    public void deletePostById(Long id) {
        postService.deletePostById(id);
    }

    public Post addLabelToPost(Long postId, String labelName) {
        Label label = labelService.saveLabel(new Label(labelName, Status.ACTIVE));
        postRepository.addLabelToPost(postId, label.getId());
        return postRepository.getById(postId);
    }
}