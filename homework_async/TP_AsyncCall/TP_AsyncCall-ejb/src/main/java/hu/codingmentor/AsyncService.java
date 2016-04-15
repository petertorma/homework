package hu.codingmentor;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

@Stateless
public class AsyncService {

    @Asynchronous
    public void asyncronTest() throws InterruptedException {
        System.out.println("asnyron method is being called.");
        for (int i = 0; i < 10; i++) {
            Thread.sleep(300);
            System.out.println("asyncron call  "+ i);
        }
        System.out.println("asyncron method is finished running.");

    }
}
