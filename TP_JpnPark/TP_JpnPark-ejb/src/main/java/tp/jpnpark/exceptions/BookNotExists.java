package tp.jpnpark.exceptions;

/**
 *
 * @author Torma Péter
 */
public class BookNotExists extends RuntimeException {

    public static final String ERRORMSG = "the book does not exists";
    public BookNotExists(String errorMessage) {
        super(errorMessage);
    }

    public BookNotExists() {
    }

}
