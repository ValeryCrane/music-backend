package com.valerycrane.music.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

public class KeyboardSampleId implements Serializable {

    private int sample;
    private int keyboard;

    public int getSample() {
        return sample;
    }

    public void setSample(int sample) {
        this.sample = sample;
    }

    public int getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(int keyboard) {
        this.keyboard = keyboard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyboardSampleId that = (KeyboardSampleId) o;
        return sample == that.sample && keyboard == that.keyboard;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sample, keyboard);
    }
}
