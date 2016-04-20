package hu.codingmentor.RestService;

import hu.codingmentor.AsyncService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/asyncCall")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class AsyncRestService {
    
    @Inject
    private AsyncService asyncTestService;
    
    @Inject
    private Logger LOGGER;
    
    @GET
    @Path("/a")
    public void testAsync(@Context HttpServletRequest request) throws InterruptedException, ExecutionException {
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(400);
        LOGGER.info("i'm gonna call an asyncron method.");
        Future<String> res = asyncTestService.asyncronTest();
        LOGGER.info("i will continue my work during asynron method works in another thread");
        for (int i = 0; i < 5; i++) {
            Thread.sleep(50);
            LOGGER.info("not asyncron   " + i);
        }
        LOGGER.info("I have finished my work");
        String result = res.get();
        LOGGER.info(result);
    }
    
}
