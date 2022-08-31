package ru.azor.mri_constructor.entities;

import lombok.Getter;
import lombok.Setter;
import ru.azor.mri_constructor.enums.ScoliosisType;

@Getter
@Setter
public class MRIStudy {
    private int id;
    private String fullName;
    private String dateOfBirth;
    private ScoliosisType scoliosisType;
    private double bendingAngle;
    private int apexOfTheBend;
}
