package com.valerycrane.music.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "melodies")
public class Melody {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "blueprint")
    private String blueprint;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "keyboard_id")
    private Keyboard keyboard;

    public Melody() {
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

    public String getBlueprint() {
        return blueprint;
    }

    public void setBlueprint(String blueprint) {
        this.blueprint = blueprint;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }
}
