package com.valerycrane.music.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "keyboard_samples")
public class KeyboardSample {

    @Column(name = "key_index")
    private int keyIndex;

    @Id
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "sample_id")
    private Sample sample;

    public KeyboardSample() {
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
}
