package com.letitbee.diamondvaluationsystem.enums;

public enum RequestDetailStatus {
    PENDING("Pending"),
    CANCEL("Cancel"),
    ASSESSING("Assessing"),
    ASSESSED("Assessed"),
    VALUATING("Valuating"),
    VALUATED("Valuated"),
    APPROVED("Approved"),;

    private final String displayName;

    RequestDetailStatus(String displayName) {
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
