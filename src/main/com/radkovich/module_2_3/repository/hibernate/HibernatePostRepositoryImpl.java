package main.com.radkovich.module_2_3.repository.hibernate;

import main.com.radkovich.module_2_3.exception.EntityNotFoundException;
import main.com.radkovich.module_2_3.exception.HibernateRepositoryException;
import main.com.radkovich.module_2_3.model.Label;
import main.com.radkovich.module_2_3.model.Post;
import main.com.radkovich.module_2_3.model.Status;
import main.com.radkovich.module_2_3.model.Writer;
import main.com.radkovich.module_2_3.repository.PostRepository;
import main.com.radkovich.module_2_3.util.HibernateFactory;
import org.hibernate.Session;

import java.util.List;

public class HibernatePostRepositoryImpl implements PostRepository {

    @Override
    public Post getById(Long id) {
        try (Session session = HibernateFactory.openSession()) {
            Post post = session.find(Post.class, id);

            if (post == null) {
                throw new EntityNotFoundException(String.valueOf(Post.class), id);
            }
            return post;
        } catch (HibernateRepositoryException e) {
            throw new HibernateRepositoryException("Could not get post by id: " + id, e);
        }
    }

    @Override
    public List<Post> getAll() {
        try (Session session = HibernateFactory.openSession()) {
            return session.createSelectionQuery("SELECT p FROM Post p JOIN FETCH p.labels", Post.class).getResultList();

        } catch (HibernateRepositoryException e) {
            throw new HibernateRepositoryException("Could not get all posts from DB!", e);
        }
    }

    @Override
    public Post save(Post post) {
        try (Session session = HibernateFactory.openSession()) {
            session.beginTransaction();

            session.persist(post);

            session.getTransaction().commit();

            return post;
        } catch (HibernateRepositoryException e) {
            throw new HibernateRepositoryException("Could not save post!", e);
        }
    }

    @Override
    public Post update(Post post) {
        try (Session session = HibernateFactory.openSession()) {
            session.beginTransaction();

            session.merge(post);

            session.getTransaction().commit();

            return post;
        } catch (HibernateRepositoryException e) {
            throw new HibernateRepositoryException("Could not update post!", e);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = HibernateFactory.openSession()) {
            session.beginTransaction();

            Post post = session.find(Post.class, id);

            if (post == null) {
                throw new EntityNotFoundException(String.valueOf(Post.class), id);
            }

            post.setStatus(Status.DELETED);
            session.merge(post);

            session.getTransaction().commit();
        } catch (HibernateRepositoryException e) {
            throw new HibernateRepositoryException("Could not delete post!", e);
        }
    }

    @Override
    public void addLabelToPost(Long postId, Long labelId) {
        try (Session session = HibernateFactory.openSession()) {
            session.beginTransaction();
            try {
                Post post = session.createSelectionQuery(
                                "SELECT p FROM Post p LEFT JOIN FETCH p.labels WHERE p.id = :id", Post.class)
                        .setParameter("id", postId)
                        .getSingleResultOrNull();

                Label label = session.find(Label.class, labelId);

                if (post == null || label == null) {
                    throw new EntityNotFoundException("Post or Label not found in adding Label to Post!", postId);
                }
                post.addLabel(label);
                session.getTransaction().commit();
            } catch (Exception e) {
                if (session.getTransaction().getStatus().canRollback()) {
                    session.getTransaction().rollback();
                }
                throw new HibernateRepositoryException("Transaction failure!", e);
            }
        }
    }

    @Override
    public Post addPostToWriter(Long writerId, Post post) {
        try (Session session = HibernateFactory.openSession()) {
            session.beginTransaction();

            try {
                Writer writer = session.find(Writer.class, writerId);
                if (writer == null) {
                    throw new EntityNotFoundException(String.valueOf(Writer.class), writerId);
                }

                post.setWriter(writer);

                session.persist(post);
                session.getTransaction().commit();
                return post;
            } catch (Exception e) {
                if (session.getTransaction().getStatus().canRollback()) {
                    session.getTransaction().rollback();
                }
                throw new HibernateRepositoryException("Transaction failure!", e);
            }
        }
    }
}
