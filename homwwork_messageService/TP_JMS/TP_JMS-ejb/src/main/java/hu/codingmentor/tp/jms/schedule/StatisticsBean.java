package hu.codingmentor.tp.jms.schedule;

import hu.codingmentor.tp.jms.dto.MessageDTO;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Singleton;
import javax.inject.Inject;

/**
 *
 * @author Torma Péter
 */
@Singleton
public class StatisticsBean {

    @Inject
    JobScheduler js;

    private final Map<Integer, MessageDTO> result = new HashMap<>();

    public StatisticsBean() {
        //default   
    }

    public Map getResult() {
        return result;
    }

    public String getJobs() {
        String tempResult = "";
        for (int i = 0; i < js.getNumber(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("the detaiuls of job number:  ").append(i).append("  :").append(result.get(i).toString()).append("\n");
            tempResult = sb.toString();
        }
        return tempResult;
    }

}
