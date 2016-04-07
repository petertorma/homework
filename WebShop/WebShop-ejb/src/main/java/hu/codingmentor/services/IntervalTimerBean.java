package hu.codingmentor.services;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.inject.Inject;

@Singleton
@LocalBean
@Startup
public class IntervalTimerBean {

    @Resource
    private TimerService timerService;
    private int counter;


    @PostConstruct
    private void init() {
        timerService.createTimer(1000, 30000, "LoginedUserInfo");
    }

    @Timeout
    public void execute(Timer timer) {
        System.out.println("Timer Service : "+ timer.getInfo());
        System.out.println("Current Time : "+ new Date());
        System.out.println("Until now "+counter+" user logged into the system");
    }

    public void add() {
        counter++;
    }

}
