package com.letitbee.diamondvaluationsystem.enums;

public enum ClarityCharacteristic {
    LASER_DRILL_HOLE("Laser Drill Hole"),
    CRYSTAL("Crystal"),
    CAVITY("Cavity"),
    CLOUD("Cloud"),
    KNOT("Knot"),
    CHIP("Chip"),
    FEATHER("Feather"),
    TWINNING_WISP("Twinning Wisp"),
    PINPOINT("Pinpoint"),
    NEEDLE("Needle"),
    BRUISE("Bruise"),
    ETCH_CHANNEL("Etch Channel"),
    INDENTED_NATURAL("Indented Natural"),
    NATURAL("Natural");

    private final String displayName;

    ClarityCharacteristic(String displayName) {
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
