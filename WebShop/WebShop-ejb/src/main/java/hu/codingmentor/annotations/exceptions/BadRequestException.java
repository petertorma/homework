
package hu.codingmentor.annotations.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException() {
        super();
    }
 
    public BadRequestException(String string) {
        super(string);
    }
}
