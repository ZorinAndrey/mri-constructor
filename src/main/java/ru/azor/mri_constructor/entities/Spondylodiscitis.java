package ru.azor.mri_constructor.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Spondylodiscitis {
    private String state;
    private String protocol;
    private String conclusion;
    private String plot;
    private String localization;
    private double size;

    public Spondylodiscitis(String state) {
        this.state = state;
    }

    public void setState(String state) {
        this.state = state;
        if (state.equals("Норма")){
            protocol = "";
            conclusion = "";
            plot = "";
            localization = "";
            size = 0;
        }
    }

    @Override
    public String toString() {
        return "Spondylodiscitis{" +
                "state='" + state + '\'' +
                ", protocol='" + protocol + '\'' +
                ", conclusion='" + conclusion + '\'' +
                ", plot='" + plot + '\'' +
                ", localization='" + localization + '\'' +
                ", size=" + size +
                '}';
    }
}
