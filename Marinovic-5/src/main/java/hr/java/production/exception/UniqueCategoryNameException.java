package hr.java.production.exception;

public class UniqueCategoryNameException extends Exception{
    public UniqueCategoryNameException() {
    }

    public UniqueCategoryNameException(String message) {
        super(message);
    }

    public UniqueCategoryNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public UniqueCategoryNameException(Throwable cause) {
        super(cause);
    }

    public UniqueCategoryNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
