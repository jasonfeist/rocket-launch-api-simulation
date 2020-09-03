package com.jasonfeist.rocket.model;

public class FloatKeyValue {
    private String label;
    private Float value;

    public FloatKeyValue(String label, Float value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public Float getValue() {
        return value;
    }
}
