package main.com.radkovich.module_2_3.exception;

public class HibernateRepositoryException extends RuntimeException {

    public HibernateRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

}
