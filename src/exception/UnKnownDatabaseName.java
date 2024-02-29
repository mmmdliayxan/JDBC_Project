package exception;

public class UnKnownDatabaseName extends RuntimeException {
    public UnKnownDatabaseName(String msg) {
        super(msg);
    }
}
