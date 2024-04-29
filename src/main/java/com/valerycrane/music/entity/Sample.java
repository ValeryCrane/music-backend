package com.valerycrane.music.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "samples")
public class Sample {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "beats")
    private Integer beats;

    public Sample() {
    }

    public Sample(String name) {
        this.name = name;
    }

    public Sample(String name, Integer beats) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBeats() {
        return beats;
    }

    public void setBeats(Integer beats) {
        this.beats = beats;
    }
}
