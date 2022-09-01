package ru.azor.mri_constructor.enums;

import lombok.Getter;

@Getter
public enum Lordosis {
    NORM("Норма", "сформирован лордоз физиологической высоты", ""),
    LOWERED("Снижен", "лордоз незначительно снижен", ", нарушения статики отдела"),
    STRAIGHTENED_OUT("Выпрямлен", "лордоз выпрямлен", ", нарушения статики отдела (выпрямленный лордоз)"),
    TENDENCY_TO_KYPHOSIS("Тенденция к кифозу", "прослеживается тенденция к формированию кифоза", ", нарушения статики отдела (тенденция к формированию кифоза)"),
    KYRHOSIS("Кифоз", "сформирован кифоз", ", нарушения статики отдела (кифоз)");

    private final String state;
    private final String protocol;
    private final String conclusion;

    Lordosis(String state, String protocol, String conclusion) {
        this.state = state;
        this.protocol = protocol;
        this.conclusion = conclusion;
    }
}
