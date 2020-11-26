package me.frenchline.corewebmvc;

/**
 * API용 예외
 */
public class EventApiException extends RuntimeException {

    public EventApiException() {
        super();
    }

    public EventApiException(String message) {
        super(message);
    }

    public EventApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public EventApiException(Throwable cause) {
        super(cause);
    }

    protected EventApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
