package com.valerycrane.music.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Table(name = "composition_blueprints")
public class Blueprint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time_created")
    private Date timeCreated;

    @Column(name = "value")
    private String value;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "parent_id")
    private Blueprint parent;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "creator_id")
    private User creator;

    public Blueprint() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Blueprint getParent() {
        return parent;
    }

    public void setParent(Blueprint parent) {
        this.parent = parent;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }
}
