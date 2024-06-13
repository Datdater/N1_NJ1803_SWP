package com.letitbee.diamondvaluationsystem.enums;

public enum Role {
    CUSTOMER("Customer"),
    MANAGER("Manager"),
    CONSULTANT_STAFF("Consultant Staff"),
    VALUATION_STAFF("Valuation Staff"),
    ADMIN("Admin");

    private final String displayName;

    Role(String displayName) {
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
