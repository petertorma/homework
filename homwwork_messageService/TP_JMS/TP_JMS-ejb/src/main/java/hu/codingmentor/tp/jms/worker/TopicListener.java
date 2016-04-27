package hu.codingmentor.tp.jms.worker;

import hu.codingmentor.tp.jms.dto.MessageDTO;
import hu.codingmentor.tp.jms.schedule.StatisticsBean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Torma Péter
 */
@MessageDriven(mappedName = "dzsobTopik")
public class TopicListener implements MessageListener {

    private static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(TopicListener.class);

    @Inject
    private StatisticsBean stat;

    public void putInResult(MessageDTO message) {
        if (message != null && stat.getResult().containsKey(message.getNumber())) {
            stat.getResult().remove(message.getNumber());
        }
        stat.getResult().put(message.getNumber(), message);
    }

    @Override
    public void onMessage(Message message) {
        MessageDTO tempMessage = null;
        try {
            tempMessage = message.getBody(MessageDTO.class);
            if (tempMessage.isIsSuccesCreation() && tempMessage.isIsReady()) {
                LOGGER.info("Job with this number :" + tempMessage.getNumber() + " - successfully created");
            } else if (tempMessage.isIsReady() && tempMessage.isIsSuccesCreation()) {
                LOGGER.info("Job with this number :" + tempMessage.getNumber() + " - was NOT successfully created");
            }

        } catch (JMSException ex) {
            Logger.getLogger(StatisticsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        putInResult(tempMessage);
    }

}
