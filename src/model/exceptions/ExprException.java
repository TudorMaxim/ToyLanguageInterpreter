package model.exceptions;

public class ExprException extends Exception {
    String message;
    public ExprException(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
