package ru.azor.mri_constructor.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Vertebrae {
    private List<String> state;
    private List<String> protocol;
    private List<String> conclusion;

    public Vertebrae(List<String> state) {
        this.state = state;
    }

    public void setState(List<String> state) {
        this.state = state;
        if (state.get(0).equals("Норма")){
            protocol = List.of("физиологической конфигурации");
            conclusion = List.of("");
        }
    }

    @Override
    public String toString() {
        return "Vertebrae{" +
                "state=" + state +
                ", protocol=" + protocol +
                ", conclusion=" + conclusion +
                '}';
    }
}
