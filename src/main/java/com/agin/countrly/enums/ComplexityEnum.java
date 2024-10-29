package com.agin.countrly.enums;

public enum ComplexityEnum {
    EASY,
    MEDIUM,
    HARD;

    @Override
    public String toString() {
        return this.name(); // Sau returnezi direct name()
    }
}
