package com.letitbee.diamondvaluationsystem.enums;

public enum DiamondOrigin {
    NATURAL("Natural"),
    LAB_GROWN("Lab Grown");

    private final String displayName;

    DiamondOrigin(String displayName) {
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
