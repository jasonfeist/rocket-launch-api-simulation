package com.jasonfeist.rocket.model;

public class IntegerKeyValue {
    private String label;
    private Integer value;

    public IntegerKeyValue(String label, Integer value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public Integer getValue() {
        return value;
    }
}
