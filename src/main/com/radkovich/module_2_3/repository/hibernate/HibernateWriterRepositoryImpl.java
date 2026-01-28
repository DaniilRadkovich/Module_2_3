package main.com.radkovich.module_2_3.repository.hibernate;

import main.com.radkovich.module_2_3.exception.EntityNotFoundException;
import main.com.radkovich.module_2_3.exception.HibernateRepositoryException;
import main.com.radkovich.module_2_3.model.Post;
import main.com.radkovich.module_2_3.model.Status;
import main.com.radkovich.module_2_3.model.Writer;
import main.com.radkovich.module_2_3.repository.WriterRepository;
import main.com.radkovich.module_2_3.util.HibernateFactory;
import org.hibernate.Session;

import java.util.List;

public class HibernateWriterRepositoryImpl implements WriterRepository {
    @Override
    public Writer getById(Long id) {
        try (Session session = HibernateFactory.openSession()) {

            Writer writer = session.find(Writer.class, id);
            if (writer == null) {
                throw new EntityNotFoundException(String.valueOf(Writer.class), id);
            }

            return writer;
        } catch (HibernateRepositoryException e) {
            throw new HibernateRepositoryException("Could not get writer by id: " + id, e);
        }
    }

    @Override
    public List<Writer> getAll() {
        try (Session session = HibernateFactory.openSession()) {

            List<Writer> writers = session
                    .createSelectionQuery("SELECT DISTINCT w FROM Writer w LEFT JOIN FETCH w.posts", Writer.class)
                    .getResultList();

            session.createSelectionQuery(
                            "SELECT DISTINCT p FROM Post p LEFT JOIN FETCH p.labels", Post.class)
                    .getResultList();

            return writers;
        } catch (HibernateRepositoryException e) {
            throw new HibernateRepositoryException("Could not get all writers from DB!", e);
        }
    }

    @Override
    public Writer save(Writer writer) {
        try (Session session = HibernateFactory.openSession()) {
            session.beginTransaction();

            session.persist(writer);

            session.getTransaction().commit();

            return writer;
        } catch (HibernateRepositoryException e) {
            throw new HibernateRepositoryException("Could not save writer!", e);
        }
    }

    @Override
    public Writer update(Writer writer) {
        try (Session session = HibernateFactory.openSession()) {
            session.beginTransaction();

            session.merge(writer);

            session.getTransaction().commit();

            return writer;
        } catch (HibernateRepositoryException e) {
            throw new HibernateRepositoryException("Could not update writer!", e);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = HibernateFactory.openSession()) {
            session.beginTransaction();

            Writer writer = session.find(Writer.class, id);

            if (writer == null) {
                throw new EntityNotFoundException(String.valueOf(Writer.class), id);
            }

            writer.setStatus(Status.DELETED);
            session.merge(writer);

            session.getTransaction().commit();
        } catch (HibernateRepositoryException e) {
            throw new HibernateRepositoryException("Could not delete writer!", e);
        }
    }
}
