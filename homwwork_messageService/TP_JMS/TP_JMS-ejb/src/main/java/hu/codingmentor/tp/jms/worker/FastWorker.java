package hu.codingmentor.tp.jms.worker;

import hu.codingmentor.tp.jms.dto.JobDTO;
import hu.codingmentor.tp.jms.dto.MessageDTO;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import javax.annotation.Resource;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Topic;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Torma Péter
 */
@MessageDriven(mappedName = "dzsobKju")
public class FastWorker implements MessageListener {

    @Resource(lookup = "dzsobTopik")
    private Topic topic;

    @Inject
    private JMSContext jmsContext;

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(FastWorker.class);

    public FastWorker() {
        //default
    }

    @Override
    public void onMessage(Message message) {
        try {
            JobDTO tempJob = message.getBody(JobDTO.class);
            Date startDate = Calendar.getInstance().getTime();
            long worktime = tempJob.getPeriod();
            Thread.sleep(worktime * 1000);
            LOGGER.info("Fastworker finished the job with the number: " + tempJob.getNumber());
            Date finishDate = Calendar.getInstance().getTime();
            jmsContext.createProducer().send(topic, new MessageDTO(tempJob.getNumber(), true, true, startDate, finishDate));
        } catch (InterruptedException | JMSException ex) {
            java.util.logging.Logger.getLogger(FastWorker.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
