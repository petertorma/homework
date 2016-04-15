package hu.codingmentor.annotations.exceptions;

public class LoggerException {

    private String errorMessage;

    public LoggerException() {
    }

    public LoggerException(String message) {
        this.errorMessage = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
