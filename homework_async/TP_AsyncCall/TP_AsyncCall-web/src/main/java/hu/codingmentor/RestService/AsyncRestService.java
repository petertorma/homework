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
    public void testAsync(@Context HttpServletRequest request) throws InterruptedException, ExecutionException {
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(400);
        System.out.println("i'm gonna call an asyncron method.");
        try{
        asyncTestService.asyncronTest();
        }catch(InterruptedException e){
            System.out.println("error" +e.getMessage());
        }
        System.out.println("i will continue my work during asynron method works in another thread");
        for (int i = 0; i < 10; i++) {
            Thread.sleep(50);
            System.out.println("not asyncron   " +i);
        }
        System.out.println("I have finished my work");
    }

}
