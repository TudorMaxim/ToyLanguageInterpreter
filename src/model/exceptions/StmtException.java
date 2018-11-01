package model.exceptions;

public class StmtException extends Exception {
    String message;

    public StmtException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
