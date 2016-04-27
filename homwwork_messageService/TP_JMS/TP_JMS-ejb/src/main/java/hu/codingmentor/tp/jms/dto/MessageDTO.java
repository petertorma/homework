package hu.codingmentor.tp.jms.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Torma Péter
 */
public class MessageDTO implements Serializable {

    private int number = 0;
    private boolean isReady = false;
    private boolean isSuccesCreation = false;
    private Date creationTime = null;
    private Date finishTime = null;

    public MessageDTO(int number, boolean isReady, boolean isSuccesCreation, Date creationTime, Date finishTime) {
        this.number = number;
        this.isReady = isReady;
        this.isSuccesCreation = isSuccesCreation;
        this.creationTime = creationTime;
        this.finishTime = finishTime;
    }

    public MessageDTO(int number, boolean isReady) {
        this.number = number;
        this.isReady = isReady;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isIsReady() {
        return isReady;
    }

    public void setIsReady(boolean isReady) {
        this.isReady = isReady;
    }

    public boolean isIsSuccesCreation() {
        return isSuccesCreation;
    }

    public void setIsSuccesCreation(boolean isSuccesCreation) {
        this.isSuccesCreation = isSuccesCreation;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    @Override
    public String toString() {
        return "MessageDTO{" + "number=" + number + ", isReady=" + isReady + ", isSuccesCreation=" + isSuccesCreation + ", creationTime=" + creationTime + ", finishTime=" + finishTime + '}';
    }

}
