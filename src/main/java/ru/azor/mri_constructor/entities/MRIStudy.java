package ru.azor.mri_constructor.entities;

import lombok.Getter;
import lombok.Setter;
import ru.azor.mri_constructor.enums.Axis;
import ru.azor.mri_constructor.enums.Lordosis;
import ru.azor.mri_constructor.enums.SagittalSize;

@Getter
@Setter
public class MRIStudy {
    private int id;
    private Axis axis;
    private Lordosis lordosis;
    private SagittalSize sagittalSize;
    private Spondylodiscitis spondylodiscitis;
    private Vertebrae vertebrae;

    @Override
    public String toString() {
        return "MRIStudy{" +
                "id=" + id +
                ", axis=" + axis +
                ", lordosis=" + lordosis +
                ", sagittalSize=" + sagittalSize +
                ", spondylodiscitis=" + spondylodiscitis +
                ", vertebrae=" + vertebrae +
                '}';
    }
}
