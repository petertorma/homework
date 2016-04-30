package tp.jpnpark.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 *
 * @author Torma Péter
 */
@Entity
//@NamedQuery(name = "logByVisitor",
//        query = "SELECT gb FROM Guestbook gb WHERE gb.amusementPark.parkId = :parkId AND gb.visitor.visitorId = :visitorId")
public class GuestBook implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "park_fk")
    private Park park;

    @ManyToOne
    @JoinColumn(name = "visitor_fk")
    private Visitor visitorId;

    private Map<LocalDate, String> logs = new HashMap<>();

    public GuestBook() {
    }

    public Map<LocalDate, String> getLogs() {
        return logs;
    }

    public void setLogs(Map<LocalDate, String> logs) {
        this.logs = logs;
    }

    public Park getPark() {
        return park;
    }

    public void setPark(Park park) {
        this.park = park;
    }

    public Visitor getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(Visitor visitorId) {
        this.visitorId = visitorId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
