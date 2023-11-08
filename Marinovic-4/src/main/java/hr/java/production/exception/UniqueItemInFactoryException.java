package hr.java.production.exception;

public class UniqueItemInFactoryException extends Exception{
    public UniqueItemInFactoryException() {
    }

    public UniqueItemInFactoryException(String message) {
        super(message);
    }

    public UniqueItemInFactoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public UniqueItemInFactoryException(Throwable cause) {
        super(cause);
    }

    public UniqueItemInFactoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
