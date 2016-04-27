package com.mycompany.rest;

import hu.codingmentor.tp.jms.worker.JobService;
import java.io.Serializable;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Torma Péter
 */
@Path("/jobs")
@Produces(MediaType.TEXT_XML)
public class JobRESTService implements Serializable {

    @Inject
    private JobService jobService;

    // ezen az url en tudod tesztelni a metódus kérést:  http://localhost:8080/TP_JMS-web/webresources/jobs 
    @GET
    public String getJobResults() {
        return jobService.jobs();
    }

    @GET
    @Path("/check")
    public String check(){
        return "the program is running.";
    }
}
