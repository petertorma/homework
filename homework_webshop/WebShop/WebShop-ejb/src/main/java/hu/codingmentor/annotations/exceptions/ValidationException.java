
package hu.codingmentor.annotations.exceptions;


public class ValidationException extends RuntimeException {
 
    public ValidationException() {
        super();
    }
 
    public ValidationException(String string) {
        super(string);
    }
}