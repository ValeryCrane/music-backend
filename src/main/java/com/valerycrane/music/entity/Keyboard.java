package com.valerycrane.music.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "keyboards")
public class Keyboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "keyboard_id")
    private List<KeyboardSample> keyboardSamples;

    public Keyboard() {
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

    public List<KeyboardSample> getKeyboardSamples() {
        return keyboardSamples;
    }

    public void setKeyboardSamples(List<KeyboardSample> keyboardSamples) {
        this.keyboardSamples = keyboardSamples;
    }
}
