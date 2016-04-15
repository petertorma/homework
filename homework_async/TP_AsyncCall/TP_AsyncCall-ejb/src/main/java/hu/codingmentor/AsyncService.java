
package hu.codingmentor;

import java.util.concurrent.Future;
import javax.annotation.Resource;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

@Asynchronous
@Stateless
public class AsyncService {

    @Resource
    private SessionContext context;

    public Future<Long> testMethod() {
        Long max = 99999999L;
        Long start = 0L;
        for (Long i = 0L; i <= max; i++) {
            if (i.equals(max)) {
                start = i;
            }
        }
        if (context.wasCancelCalled()) {
            return new AsyncResult<>(-1L);
        } else {
            return new AsyncResult<>(start);
        }
    }
}
