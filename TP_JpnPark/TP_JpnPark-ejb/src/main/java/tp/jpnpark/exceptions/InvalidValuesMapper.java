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
public class InvalidValuesMapper implements ExceptionMapper<InvalidValues> {

    @Override
    public Response toResponse(InvalidValues exception) {
        return Response.status(Response.Status.BAD_REQUEST).entity(new ExceptionDTO(InvalidValues.ERRORMSG, exception.getMessage())).type(MediaType.APPLICATION_JSON).build();
    }

}
