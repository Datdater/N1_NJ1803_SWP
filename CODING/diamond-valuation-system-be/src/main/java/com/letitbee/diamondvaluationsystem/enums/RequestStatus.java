package com.letitbee.diamondvaluationsystem.enums;

public enum RequestStatus {
    PENDING("Pending"),
    CANCEL("Cancel"),
    PROCESSING("Processing"),
    RECEIVED("Received"),
    VALUATING("Valuating"),
    COMPLETED("Completed"),
    SEALED("Sealed"),
    FINISHED("Finished");

    private final String displayName;

    RequestStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

    
    public boolean equals(RequestStatus requestStatus) {
        return this.toString().equalsIgnoreCase(requestStatus.toString());
    }
}
