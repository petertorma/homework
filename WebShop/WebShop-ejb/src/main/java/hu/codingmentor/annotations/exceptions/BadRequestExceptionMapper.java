/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.codingmentor.annotations.exceptions;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class BadRequestExceptionMapper implements ExceptionMapper<BadRequestException> {

    @Inject
    private Logger logger;

    @Override
    public Response toResponse(BadRequestException exception) {
        logger.log(Level.ALL, "Illegal Request ", exception);
        return Response.status(Response.Status.BAD_REQUEST).entity(
                new ExceptionDTO(exception.getMessage())
        ).type(MediaType.APPLICATION_JSON).build();

    }

}
