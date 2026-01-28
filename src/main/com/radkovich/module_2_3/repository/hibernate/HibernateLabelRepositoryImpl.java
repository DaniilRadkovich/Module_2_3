package main.com.radkovich.module_2_3.repository.hibernate;

import main.com.radkovich.module_2_3.exception.EntityNotFoundException;
import main.com.radkovich.module_2_3.exception.HibernateRepositoryException;
import main.com.radkovich.module_2_3.model.Label;
import main.com.radkovich.module_2_3.model.Status;
import main.com.radkovich.module_2_3.repository.LabelRepository;
import main.com.radkovich.module_2_3.util.HibernateFactory;
import org.hibernate.Session;

import java.util.List;

public class HibernateLabelRepositoryImpl implements LabelRepository {
    @Override
    public Label getById(Long id) {
        try (Session session = HibernateFactory.openSession()) {
            Label label = session.find(Label.class, id);

            if (label == null) {
                throw new EntityNotFoundException(String.valueOf(Label.class), id);
            }

            return label;
        } catch (HibernateRepositoryException e) {
            throw new HibernateRepositoryException("Could not get label by id: " + id, e);
        }
    }

    @Override
    public List<Label> getAll() {
        try (Session session = HibernateFactory.openSession()) {
            return session.createSelectionQuery("FROM Label", Label.class).getResultList();

        } catch (HibernateRepositoryException e) {
            throw new HibernateRepositoryException("Could not get all labels from DB!", e);
        }
    }

    @Override
    public Label save(Label label) {
        try (Session session = HibernateFactory.openSession()) {
            session.beginTransaction();

            session.persist(label);

            session.getTransaction().commit();

            return label;
        } catch (HibernateRepositoryException e) {
            throw new HibernateRepositoryException("Could not save label!", e);
        }
    }

    @Override
    public Label update(Label label) {
        try (Session session = HibernateFactory.openSession()) {
            session.beginTransaction();

            session.merge(label);

            session.getTransaction().commit();

            return label;
        } catch (HibernateRepositoryException e) {
            throw new HibernateRepositoryException("Could not update label!", e);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = HibernateFactory.openSession()) {
            session.beginTransaction();

            Label label = session.find(Label.class, id);

            if (label == null) {
                throw new EntityNotFoundException(String.valueOf(Label.class), id);
            }

            label.setStatus(Status.DELETED);
            session.merge(label);

            session.getTransaction().commit();
        } catch (HibernateRepositoryException e) {
            throw new HibernateRepositoryException("Could not delete label!", e);
        }
    }
}
