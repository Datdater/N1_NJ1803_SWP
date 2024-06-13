package com.letitbee.diamondvaluationsystem.enums;

public enum Symmetry {
    FAIR("Fair"),
    GOOD("Good"),
    VERY_GOOD("Very Good"),
    EXCELLENT("Excellent");

    private final String displayName;

    Symmetry(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}