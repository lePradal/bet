package com.prads.bet.models;

public abstract class Founds {

    private Double value;

    private Founds() {}

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void addFound(Double valueToAdd) {
        setValue(value + valueToAdd);
    }

    public void subtractFound(Double valueToSubtract) {
        setValue(value - valueToSubtract);
    }
}
