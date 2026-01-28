import main.com.radkovich.module_2_3.model.Post;
import main.com.radkovich.module_2_3.model.Status;
import main.com.radkovich.module_2_3.repository.PostRepository;
import main.com.radkovich.module_2_3.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class PostServiceTest {

    @Mock
    private PostRepository postRepository;
    private PostService postService;
    private Post testPost;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        postService = new PostService(postRepository);
        testPost = new Post("Test content", LocalDateTime.now(), Status.ACTIVE);
    }

    @Test
    public void testGetById() {
        when(postRepository.getById(anyLong())).thenReturn(testPost);

        Post post = postService.getPostById(1L);

        assertNotNull(post);
        assertEquals("Test content", post.getContent());
        assertEquals(testPost.getCreated(), post.getCreated());
        assertEquals(Status.ACTIVE, post.getStatus());
    }

    @Test
    public void testGetAll() {
        List<Post> posts = new ArrayList<>();
        posts.add(testPost);

        when(postRepository.getAll()).thenReturn(posts);

        List<Post> result = postService.getAllPosts();

        assertNotNull(result);
        assertEquals(posts.size(), result.size());
        assertEquals("Test content", result.get(0).getContent());
        assertEquals(Status.ACTIVE, result.get(0).getStatus());
    }

    @Test
    public void testSave() {
        when(postRepository.save(any(Post.class))).thenReturn(testPost);

        Post post = postService.savePost(testPost);

        assertNotNull(post);
        assertEquals("Test content", post.getContent());
        assertEquals(testPost.getCreated(), post.getCreated());
        assertEquals(Status.ACTIVE, post.getStatus());

        verify(postRepository, times(1)).save(testPost);
    }

    @Test
    public void testUpdate() {
        Post post = new Post(1L, "Test content", LocalDateTime.now(), LocalDateTime.now(), new HashSet<>(), Status.ACTIVE);

        when(postRepository.update(any(Post.class))).thenReturn(post);

        Post postToUpdate = postService.updatePost(post);

        assertNotNull(postToUpdate);
        assertEquals(post.getId(), postToUpdate.getId());
        assertEquals("Test content", postToUpdate.getContent());
        assertEquals(Status.ACTIVE, postToUpdate.getStatus());

        verify(postRepository, times(1)).update(post);
    }

    @Test
    public void testDelete() {
        postService.deletePostById(1L);
        verify(postRepository, times(1)).deleteById(1L);

    }
}
