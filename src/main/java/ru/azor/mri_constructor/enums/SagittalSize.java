package ru.azor.mri_constructor.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum SagittalSize {
    NORM("Норма", "не снижен", ""),
    LOWERED("Снижен", "снижен", "");

    private final String state;
    private final String protocol;
    @Setter
    private String conclusion;

    SagittalSize(String state, String protocol, String conclusion) {
        this.state = state;
        this.protocol = protocol;
        this.conclusion = conclusion;
    }
}
