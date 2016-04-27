package hu.codingmentor.tp.jms.dto;

import java.io.Serializable;

public class JobDTO implements Serializable {

    private int number;
    private int period;

    public JobDTO(int period, int number) {
        this.period = period;
        this.number = number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return "Details of the job: {" + "number= " + this.number + ", period=" + this.period + '}';
    }

}
