
package hu.codingmentor.annotations.exceptions;

public class IllegalRequestException extends RuntimeException {
    public IllegalRequestException() {
        super();
    }
 
    public IllegalRequestException(String string) {
        super(string);
    }
}
