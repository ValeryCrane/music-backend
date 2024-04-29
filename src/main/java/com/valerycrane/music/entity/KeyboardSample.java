package com.valerycrane.music.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "keyboard_samples")
@IdClass(KeyboardSampleId.class)
public class KeyboardSample {

    @Column(name = "key_index")
    private int keyIndex;

    @Id
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "sample_id")
    private Sample sample;

    @Id
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "keyboard_id")
    private Keyboard keyboard;

    public KeyboardSample() {
    }

    public KeyboardSample(int keyIndex, Sample sample, Keyboard keyboard) {
        this.keyIndex = keyIndex;
        this.sample = sample;
        this.keyboard = keyboard;
    }

    public int getKeyIndex() {
        return keyIndex;
    }

    public void setKeyIndex(int keyIndex) {
        this.keyIndex = keyIndex;
    }

    public Sample getSample() {
        return sample;
    }

    public void setSample(Sample sample) {
        this.sample = sample;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }
}
