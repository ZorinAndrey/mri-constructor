package ru.azor.mri_constructor;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SpondylodiscitisController implements Initializable {
    @FXML
    public Label sagittalLabel;
    @FXML
    public Slider size;
    @FXML
    public RadioButton deviation;
    @FXML
    public RadioButton norm;
    @FXML
    public ComboBox<String> plot;
    @FXML
    public ComboBox<String> localization;
    @FXML
    public Label localLabel;
    @FXML
    public Label plotLabel;
    @FXML
    public Label spondylodiscitisLabel;
    public Label vertebraeLabel;
    @FXML
    private Label label;
    @FXML
    public Label axisLabel;
    @FXML
    public Label lordosisLabel;
    private ToggleGroup group;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        group = new ToggleGroup();
        group.getToggles().add(norm);
        group.getToggles().add(deviation);
        group.selectToggle(norm);

        plot.getItems().addAll(List.of("С0-С1", "С1-С2", "C2-C3", "С3-С4", "С4-С5", "C5-C6", "С6-С7", "C7-Th1"));

        lordosisLabel.setText("Лордоз: " + Application.INSTANCE.getStudy().getLordosis().getState());
        axisLabel.setText("Ось: " + Application.INSTANCE.getStudy().getAxis().getState());
        sagittalLabel.setText("Сагиттальный размер: " + Application.INSTANCE.getStudy().getSagittalSize().getState());
        if (!Application.INSTANCE.getStudy().getSpondylodiscitis().getState().equals("Норма")) {
            spondylodiscitisLabel.setText("Спондилодисцит: присутствует");
            group.selectToggle(deviation);
            if (Application.INSTANCE.getStudy().getSpondylodiscitis().getSize() != 0) {
                size.setValue(Application.INSTANCE.getStudy().getSpondylodiscitis().getSize());
            } else {
                size.setValue(0.4);
            }
            if (! Application.INSTANCE.getStudy().getSpondylodiscitis().getPlot().equals("")) {
                plot.getSelectionModel().select(Application.INSTANCE.getStudy().getSpondylodiscitis().getPlot());
            }
            if (! Application.INSTANCE.getStudy().getSpondylodiscitis().getLocalization().equals("")) {
                localization.getSelectionModel().select(Application.INSTANCE.getStudy().getSpondylodiscitis().getLocalization());
            }
        } else {
            spondylodiscitisLabel.setText("Спондилодисцит: отсутствует");
            group.selectToggle(norm);
            size.setVisible(false);
            plot.setVisible(false);
            localization.setVisible(false);
            localLabel.setVisible(false);
            plotLabel.setVisible(false);
        }
        if (!Application.INSTANCE.getStudy().getVertebrae().getState().get(0).equals("Норма")) {
            vertebraeLabel.setText("Позвонки: " + String.join(",\n", Application.INSTANCE.getStudy().getVertebrae().getState()));
        }else {
            vertebraeLabel.setText("Позвонки: физиологической конфигурации");
        }
    }

    @FXML
    protected void prevWindow() throws IOException {
        if (group.getSelectedToggle().equals(norm)) {
            Application.INSTANCE.getStudy().getSpondylodiscitis().setState("Норма");
        } else {
            Application.INSTANCE.getStudy().getSpondylodiscitis().setState("Не норма");
            Application.INSTANCE.getStudy().getSpondylodiscitis().setProtocol("В сопряженных отделах тел " + plot.getSelectionModel().getSelectedItem() + " позвонков, в межпозвонковом диске визуализируются участки измененного МР сигнала без четких границ, с неоднородной структурой, интенсивностью, повышенной на ИП t2, пониженной на ИП t1, с максимальной глубиной в теле позвонка " + localization.getSelectionModel().getSelectedItem() + " " + size.getValue() + " см.");
            Application.INSTANCE.getStudy().getSpondylodiscitis().setConclusion(", спондилодисцита на уровне " + plot.getSelectionModel().getSelectedItem() + " в теле позвонка " + localization.getSelectionModel().getSelectedItem() + " " + size.getValue() + " ПДС");
            Application.INSTANCE.getStudy().getSpondylodiscitis().setPlot(plot.getSelectionModel().getSelectedItem());
            Application.INSTANCE.getStudy().getSpondylodiscitis().setLocalization(localization.getSelectionModel().getSelectedItem());
            Application.INSTANCE.getStudy().getSpondylodiscitis().setSize(size.getValue());
        }
        Application.INSTANCE.initStaticsWindow();
    }

    @FXML
    public void nextWindow() throws IOException {
        if (group.getSelectedToggle().equals(norm)) {
            Application.INSTANCE.getStudy().getSpondylodiscitis().setState("Норма");
        } else {
            Application.INSTANCE.getStudy().getSpondylodiscitis().setState("Не норма");
            Application.INSTANCE.getStudy().getSpondylodiscitis().setProtocol("В сопряженных отделах тел " + plot.getSelectionModel().getSelectedItem() + " позвонков, в межпозвонковом диске визуализируются участки измененного МР сигнала без четких границ, с неоднородной структурой, интенсивностью, повышенной на ИП t2, пониженной на ИП t1, с максимальной глубиной в теле позвонка " + localization.getSelectionModel().getSelectedItem() + " " + size.getValue() + "см.");
            Application.INSTANCE.getStudy().getSpondylodiscitis().setConclusion(", спондилодисцита на уровне " + plot.getSelectionModel().getSelectedItem() + " в теле позвонка " + localization.getSelectionModel().getSelectedItem() + " " + size.getValue() + " ПДС");
            Application.INSTANCE.getStudy().getSpondylodiscitis().setPlot(plot.getSelectionModel().getSelectedItem());
            Application.INSTANCE.getStudy().getSpondylodiscitis().setLocalization(localization.getSelectionModel().getSelectedItem());
            Application.INSTANCE.getStudy().getSpondylodiscitis().setSize(size.getValue());
        }
        Application.INSTANCE.initVertebraeWindow();
    }

    public void selectNorm() {
        size.setVisible(false);
        plot.setVisible(false);
        localization.setVisible(false);
        localLabel.setVisible(false);
        plotLabel.setVisible(false);
        plot.getItems().clear();
        spondylodiscitisLabel.setText("");
        spondylodiscitisLabel.setText("Спондилодисцит: отсутствует");
    }

    public void selectDeviation() {
        size.setVisible(true);
        plot.setVisible(true);
        localization.setVisible(true);
        localLabel.setVisible(true);
        plotLabel.setVisible(true);
        plot.getItems().addAll(List.of("С0-С1", "С1-С2", "C2-C3", "С3-С4", "С4-С5", "C5-C6", "С6-С7", "C7-Th1"));
        plot.getSelectionModel().selectFirst();
        localization.getSelectionModel().selectFirst();
        size.setValue(0.4);
        spondylodiscitisLabel.setText("Спондилодисцит: присутствует");
    }

    public void selectPlot() {
        localization.getItems().clear();
        if (plot.getSelectionModel().getSelectedItem() != null) {
            localization.getItems().addAll(plot.getSelectionModel().getSelectedItem().split("-"));
        }
    }

    public void selectLocalization() {
    }
}