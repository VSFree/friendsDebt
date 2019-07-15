package pl.coderslab.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Debt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private Double returned = 0.0;

    @ManyToOne
    @JoinColumn(name = "event_group_id")
    private EventGroups eventGroup;

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreditor(User creditor) {
        this.user = creditor;
    }

    public void setReturned(Double returned) {
        this.returned = returned;
    }

    public void setEventGroup(EventGroups eventGroup) {
        this.eventGroup = eventGroup;
    }

    public Long getId() {
        return id;
    }

    public User getCreditor() {
        return user;
    }

    public Double getReturned() {
        return returned;
    }

    public EventGroups getEventGroup() {
        return eventGroup;
    }
}
