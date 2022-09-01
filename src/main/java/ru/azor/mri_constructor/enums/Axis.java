package ru.azor.mri_constructor.enums;

import lombok.Getter;

@Getter
public enum Axis {
    NORM("Норма", "ось позвоночника сохранена", ""),
    RIGHT_SIDE_SCOLIOSIS("П/с сколиоз", "сформирован правосторонний сколиоз", ", нарушения статики отдела (правосторонний сколиоз)"),
    LEFT_SIDE_SCOLIOSIS("Л/с сколиоз", "сформирован левосторонний сколиоз", ", нарушения статики отдела (левосторонний сколиоз)"),
    S_SHAPED_SCOLIOSIS("S-образный сколиоз", "сформирован S-образный сколиоз", ", нарушения статики отдела (S-образный сколиоз)");

    private final String state;
    private final String protocol;
    private final String conclusion;

    Axis(String state, String protocol, String conclusion) {
        this.state = state;
        this.protocol = protocol;
        this.conclusion = conclusion;
    }
}
