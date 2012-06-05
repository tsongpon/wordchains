package wordchains;

/**
 *
 */
public class WordChainsException extends RuntimeException {
    public WordChainsException(String message, Throwable e) {
        super(message, e);
    }

    public WordChainsException(String message) {
        super(message);
    }
}
