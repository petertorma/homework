package hu.codingmentor.tp.jms.schedule;

import hu.codingmentor.tp.jms.dto.JobDTO;
import hu.codingmentor.tp.jms.dto.MessageDTO;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.jms.Topic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Torma Péter
 */
@Singleton
@Startup
public class JobScheduler {

    @Inject
    private JMSContext jmsContext;

    private final Random r = new Random();
    private static final int START = 1;
    private static final int END = 6;
    private int randomNumber;
    private int number = 0;

    @Resource(lookup = "dzsobKju")
    private Queue queue;

    @Resource(lookup = "dzsobTopik")
    private Topic topic;

    @Resource
    private TimerService timerService;

    private static final Logger LOGGER = LoggerFactory.getLogger(JobScheduler.class);

    @PostConstruct
    private void setTimer() {
        ScheduleExpression se = new ScheduleExpression();
        se.dayOfMonth("*");
        se.hour("*");
        se.minute("*");
        se.second("*/25");
        Timer t = timerService.createCalendarTimer(se);

    }

    @Timeout
    public void jobSender(Timer t) throws InterruptedException {
        JMSProducer producer = jmsContext.createProducer();
        for (int i = 0; i < 10; i++) {
            randomNumber = r.nextInt(END - START) + START;
            JobDTO tempJob = new JobDTO(randomNumber, number);
            number++;
            producer.send(queue, tempJob);
            LOGGER.info("this job is ready and sent to the queue: " + tempJob.getNumber());
            Thread.sleep(200);
            producer.send(topic, new MessageDTO(number, false));
        }
    }

    public int getNumber() {
        return number;
    }
    
    
}
