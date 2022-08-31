package ru.azor.mri_constructor.enums;

import lombok.Getter;

@Getter
public enum ScoliosisType {

    RIGHT("Правосторонний"), LEFT("Левосторонний"), S_SHAPED("S-образный");

    private final String rus;

    ScoliosisType(String rus) {
        this.rus = rus;
    }
}
