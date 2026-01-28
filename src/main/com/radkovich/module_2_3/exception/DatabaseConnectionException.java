package main.com.radkovich.module_2_3.exception;

public class DatabaseConnectionException extends RepositoryException {
    public DatabaseConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
