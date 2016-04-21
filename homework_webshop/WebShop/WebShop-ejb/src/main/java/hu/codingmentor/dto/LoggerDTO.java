package hu.codingmentor.dto;

public class LoggerDTO {

    private String errorMessage;

    public LoggerDTO() {
        //default
    }

    public LoggerDTO(String message) {
        this.errorMessage = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
