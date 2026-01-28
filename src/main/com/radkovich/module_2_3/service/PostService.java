package main.com.radkovich.module_2_3.service;

import main.com.radkovich.module_2_3.model.Post;
import main.com.radkovich.module_2_3.repository.PostRepository;

import java.util.HashSet;
import java.util.List;

public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post getPostById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Post id must be positive!");
        }
        return postRepository.getById(id);
    }

    public List<Post> getAllPosts() {
        return postRepository.getAll();
    }

    public Post savePost(Post post) {
        validatePost(post);
        return postRepository.save(post);
    }

    public Post updatePost(Post post) {
        if (post.getId() <= 0) {
            throw new IllegalArgumentException("Post id must be positive for update!");
        }
        validatePost(post);
        return postRepository.update(post);
    }

    public void deletePostById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Post id must be positive for delete!");
        }
        postRepository.deleteById(id);
    }

    private void validatePost(Post post) {
        if (post == null) {
            throw new IllegalArgumentException("Post cannot be null!");
        }
        if (post.getContent() == null || post.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("Post content cannot be empty!");
        }
        if (post.getStatus() == null) {
            throw new IllegalArgumentException("Post status cannot be null!");
        }
        if (post.getId() > 0 && post.getLabels() == null) {
            post.setLabels(new HashSet<>());
        }
    }
}
