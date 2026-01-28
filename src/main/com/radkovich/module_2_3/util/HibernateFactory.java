package main.com.radkovich.module_2_3.util;

import main.com.radkovich.module_2_3.model.Label;
import main.com.radkovich.module_2_3.model.Post;
import main.com.radkovich.module_2_3.model.Writer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class HibernateFactory {
    public static final SessionFactory sessionFactory;

    static {
        try {
            org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration()
                    .addAnnotatedClass(Label.class)
                    .addAnnotatedClass(Post.class)
                    .addAnnotatedClass(Writer.class);

            configuration.configure("hibernate.cfg.xml");

            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            throw new ExceptionInInitializerError("SessionFactory was not created!" + e);
        }
    }

    public static Session openSession() {
        return sessionFactory.openSession();
    }
}

