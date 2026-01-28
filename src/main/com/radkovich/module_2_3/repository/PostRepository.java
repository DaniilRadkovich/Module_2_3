package main.com.radkovich.module_2_3.repository;

import main.com.radkovich.module_2_3.model.Post;

public interface PostRepository extends GenericRepository<Post, Long> {
    void addLabelToPost(Long postId, Long labelId);
    Post addPostToWriter(Long writerId, Post post);
}
