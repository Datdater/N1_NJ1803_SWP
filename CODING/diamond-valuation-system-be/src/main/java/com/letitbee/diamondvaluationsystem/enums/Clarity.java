package com.letitbee.diamondvaluationsystem.enums;

public enum Clarity {
    FL("FL"),
    IF("IF"),
    VVS1("VVS1"),
    VVS2("VVS2"),
    VS1("VS1"),
    VS2("VS2"),
    SI1("SI1"),
    SI2("SI2"),
    I1("I1"),
    I2("I2"),
    I3("I3");

    private final String displayName;

    Clarity() {
        this.displayName = name();
    }

    Clarity(String displayName) {
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
