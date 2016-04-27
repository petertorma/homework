package hu.codingmentor.tp.jms.worker;

import hu.codingmentor.tp.jms.schedule.StatisticsBean;
import javax.ejb.Stateless;
import javax.inject.Inject;


/**
 *
 * @author Torma Péter
 */
@Stateless
public class JobService {

    @Inject
    StatisticsBean statisticsBean;

    public String jobs() {
        return statisticsBean.getJobs();
        
    }
}
