package tp.jpnpark.exceptions;

/**
 *
 * @author Torma Péter
 */
public class InvalidValues extends RuntimeException {

    public static final String ERRORMSG = "you enterted wrong values";
    public InvalidValues(String errorMessage) {
        super(errorMessage);
    }

    public InvalidValues() {
        //default
    }

}
