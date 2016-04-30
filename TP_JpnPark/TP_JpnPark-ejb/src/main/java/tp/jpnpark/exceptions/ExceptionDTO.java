package tp.jpnpark.exceptions;


/**
 *
 * @author Torma Péter
 */
public class ExceptionDTO {

    private String errorCode;
    private String errorMessage;

    public ExceptionDTO() {
    }

    public ExceptionDTO(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ExceptionDTO(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
