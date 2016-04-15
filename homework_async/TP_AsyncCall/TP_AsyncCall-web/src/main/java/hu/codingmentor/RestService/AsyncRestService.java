package hu.codingmentor.RestService;

import hu.codingmentor.AsyncService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/asyncCall")
@Produces(MediaType.APPLICATION_JSON)
public class AsyncRestService {

    @Inject
    private AsyncService asyncTestService;

    @GET
    @Path("/")
    public String testAsync(@Context HttpServletRequest request) throws InterruptedException, ExecutionException {
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(400);
        Future<Long> ret1 = asyncTestService.testMethod();
        Future<Long> ret2 = asyncTestService.testMethod();
        ret2.cancel(true);

        Long asynyCancelled = ret2.get();
        Long asyncGood = ret1.get();
        return ("The return of the cancelled method: : " + asynyCancelled.toString() + " the return of the other: " + asyncGood.toString());

    }

}
