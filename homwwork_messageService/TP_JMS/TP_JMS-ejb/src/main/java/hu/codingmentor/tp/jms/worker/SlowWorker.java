/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Torma Péter
 */
@MessageDriven(mappedName = "dzsobKju")
public class SlowWorker implements MessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(SlowWorker.class);

    @Resource(lookup = "dzsobTopik")
    private Topic topic;
    
    @Inject
    private JMSContext jmsContext;

    public SlowWorker() {
        //default
    }

    @Override
    public void onMessage(Message message) {

        try {
            JobDTO tempJob = message.getBody(JobDTO.class);
            Date startDate = Calendar.getInstance().getTime();
            long worktime = (long)tempJob.getPeriod() * 2;
            Thread.sleep(worktime * 1000);
            LOGGER.info("SlowWorker finished the job with the number: " + tempJob.getNumber());
            boolean status = worktime < 5;
            Date finishDate = Calendar.getInstance().getTime();
            jmsContext.createProducer().send(topic, new MessageDTO(tempJob.getNumber(), true, status, startDate, finishDate));
        } catch (InterruptedException | JMSException ex) {
            java.util.logging.Logger.getLogger(SlowWorker.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

}
