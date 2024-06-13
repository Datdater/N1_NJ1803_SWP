package com.letitbee.diamondvaluationsystem.enums;

public enum Shape {
    ROUND("Round"),
    PRINCESS("Princess"),
    OVAL("Oval"),
    MARQUISE("Marquise"),
    PEAR("Pear"),
    CUSHION("Cushion"),
    EMERALD("Emerald"),
    ASSCHER("Asscher"),
    RADIANT("Radiant"),
    HEART("Heart");

    private final String displayName;

    Shape(String displayName) {
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