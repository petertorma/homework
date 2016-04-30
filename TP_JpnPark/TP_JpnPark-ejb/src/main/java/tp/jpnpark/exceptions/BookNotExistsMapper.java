package tp.jpnpark.exceptions;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Torma Péter
 */
@Provider
public class BookNotExistsMapper implements ExceptionMapper<BookNotExists> {

    @Override
    public Response toResponse(BookNotExists exception) {
        return Response.status(Response.Status.BAD_REQUEST).entity(new ExceptionDTO(BookNotExists.ERRORMSG, exception.getMessage())).type(MediaType.APPLICATION_JSON).build();
    }

}
