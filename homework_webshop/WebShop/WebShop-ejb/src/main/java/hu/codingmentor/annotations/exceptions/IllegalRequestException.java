
package hu.codingmentor.annotations.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException
public class IllegalRequestException extends RuntimeException {
    public IllegalRequestException() {
        super();
    }
 
    public IllegalRequestException(String string) {
        super(string);
    }
}
