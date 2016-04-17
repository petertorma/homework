package hu.codingmentor;

import java.util.concurrent.Future;
import java.util.logging.Logger;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AsyncService {

    @Inject
    private Logger LOGGER;

    @Asynchronous
    public Future<String> asyncronTest() throws InterruptedException {
        LOGGER.info("asnyron method is being called.");
        for (int i = 0; i < 10; i++) {
            Thread.sleep(300);
            LOGGER.info("asyncron call  " + i);
        }
        return new AsyncResult<>("Asyncron method finished working");

    }
}
