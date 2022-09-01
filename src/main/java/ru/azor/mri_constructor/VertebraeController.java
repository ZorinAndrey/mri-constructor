package ru.azor.mri_constructor;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class VertebraeController implements Initializable {
    @FXML
    public RadioButton norm, deviation;
    @FXML
    public TextArea wedgeDeformityTextArea, rearContourTextArea;
    @FXML
    public CheckBox rearContour, wedgeDeformity, sizeReduction, d0, d1, d2, d3, d4, d5, d6, d7, d8, r0, r1, r2, r3, r4, r5, r6, r7, r8;
    @FXML
    public VBox deviationBox, rearContourBox, wedgeDeformityBox;
    @FXML
    private Label vertebraeLabel, axisLabel, lordosisLabel, spondylodiscitisLabel, sagittalLabel;
    private ToggleGroup group;
    private List<CheckBox> rSet;
    private List<CheckBox> dSet;
    private List<String> vertebraeStateList;
    private List<String> vertebraeProtocolList;
    private List<String> vertebraeConclusionList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vertebraeStateList = new CopyOnWriteArrayList<>();
        vertebraeConclusionList = new CopyOnWriteArrayList<>();
        vertebraeProtocolList = new CopyOnWriteArrayList<>();
        group = new ToggleGroup();
        group.getToggles().add(norm);
        group.getToggles().add(deviation);
        group.selectToggle(norm);

        rSet = List.of(r0, r1, r2, r3, r4, r5, r6, r7, r8);
        dSet = List.of(d0, d1, d2, d3, d4, d5, d6, d7, d8);

        sizeReduction.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals(true) && !vertebraeStateList.contains("Снижение размера")) {
                vertebraeStateList.add("Снижение размера");
            } else if (newValue.equals(false)) {
                vertebraeStateList.remove("Снижение размера");
            }
            vertebraeLabel.setText("Позвонки: " + String.join(",\n", vertebraeStateList));
        });
        wedgeDeformity.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals(true)) {
                wedgeDeformityTextArea.setText("деформации (ДОПИСАТЬ)");
            } else {
                wedgeDeformityTextArea.clear();
            }
            if (newValue.equals(true) && !vertebraeStateList.contains("Клиновидная деформация")) {
                vertebraeStateList.add("Клиновидная деформация");
            } else if (newValue.equals(false)) {
                vertebraeStateList.remove("Клиновидная деформация");
            }
            vertebraeLabel.setText("Позвонки: " + String.join(",\n", vertebraeStateList));
            dSet.forEach(d -> d.setSelected(false));
            wedgeDeformityBox.setVisible(newValue);
        });
        rearContour.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals(true)) {
                rearContourTextArea.setText("дополнительного образования (ДОПИСАТЬ)");
            } else {
                rearContourTextArea.clear();
            }
            if (newValue.equals(true) && !vertebraeStateList.contains("Задний контур")) {
                vertebraeStateList.add("Задний контур");
            } else if (newValue.equals(false)) {
                vertebraeStateList.remove("Задний контур");
            }
            vertebraeLabel.setText("Позвонки: " + String.join(",\n", vertebraeStateList));
            rSet.forEach(r -> r.setSelected(false));
            rearContourBox.setVisible(newValue);
        });

        lordosisLabel.setText("Лордоз: " + Application.INSTANCE.getStudy().getLordosis().getState());
        axisLabel.setText("Ось: " + Application.INSTANCE.getStudy().getAxis().getState());
        sagittalLabel.setText("Сагиттальный размер: " + Application.INSTANCE.getStudy().getSagittalSize().getState());
        if (!Application.INSTANCE.getStudy().getSpondylodiscitis().getState().equals("Норма")) {
            spondylodiscitisLabel.setText("Спондилодисцит: присутствует");
        } else {
            spondylodiscitisLabel.setText("Спондилодисцит: отсутствует");
        }
        if (!Application.INSTANCE.getStudy().getVertebrae().getState().get(0).equals("Норма")) {
            group.selectToggle(deviation);
            vertebraeLabel.setText("Позвонки: " + String.join(",\n", Application.INSTANCE.getStudy().getVertebrae().getState()));
            deviationBox.setVisible(true);
            if (!wedgeDeformity.isSelected()) {
                wedgeDeformityBox.setVisible(false);
            }
            if (!rearContour.isSelected()) {
                rearContourBox.setVisible(false);
            }
            wedgeDeformityTextArea.setText(Application.INSTANCE.getStudy().getVertebrae().getConclusion().stream().filter(s -> s.startsWith("деформации")).collect(Collectors.joining()));
            rearContourTextArea.setText(Application.INSTANCE.getStudy().getVertebrae().getConclusion().stream().filter(s -> s.startsWith("дополнительного образования")).collect(Collectors.joining()));
        } else {
            vertebraeLabel.setText("Позвонки: физиологической конфигурации");
            group.selectToggle(norm);
            deviationBox.setVisible(false);
        }
    }

    @FXML
    protected void prevWindow() throws IOException {
        if (group.getSelectedToggle().equals(norm)) {
            Application.INSTANCE.getStudy().getVertebrae().setState(List.of("Норма"));
        } else {
            if (sizeReduction.isSelected()) {
                vertebraeProtocolList.add("сниженного размера");
                vertebraeConclusionList.add("остеохондроза");
            }
            if (wedgeDeformity.isSelected()) {
                vertebraeProtocolList.add("прослеживается клиновидная деформация позвонка " + String.join(", ", dSet.stream().filter(CheckBox::isSelected).map(Labeled::getText).toList()));
                vertebraeConclusionList.add(wedgeDeformityTextArea.getText());
            }
            if (rearContour.isSelected()) {
                vertebraeProtocolList.add("прослеживается дополнительный сигнал позвонка " + String.join(", ", rSet.stream().filter(CheckBox::isSelected).map(Labeled::getText).toList()));
                vertebraeConclusionList.add(rearContourTextArea.getText());
            }
            Application.INSTANCE.getStudy().getVertebrae().setState(List.copyOf(vertebraeStateList));
            Application.INSTANCE.getStudy().getVertebrae().setProtocol(List.copyOf(vertebraeProtocolList));
            Application.INSTANCE.getStudy().getVertebrae().setConclusion(List.copyOf(vertebraeConclusionList));
        }
        Application.INSTANCE.initSpondylodiscitisWindow();
    }

    @FXML
    public void nextWindow() throws IOException {
        if (group.getSelectedToggle().equals(norm)) {
            Application.INSTANCE.getStudy().getVertebrae().setState(List.of("Норма"));
        } else {
            if (sizeReduction.isSelected()) {
                vertebraeProtocolList.add("сниженного размера");
                vertebraeConclusionList.add("остеохондроза");
            }
            if (wedgeDeformity.isSelected()) {
                vertebraeProtocolList.add("прослеживается клиновидная деформация позвонка " + String.join(", ", dSet.stream().filter(CheckBox::isSelected).map(Labeled::getText).toList()));
                vertebraeConclusionList.add(wedgeDeformityTextArea.getText());
            }
            if (rearContour.isSelected()) {
                vertebraeProtocolList.add("прослеживается дополнительный сигнал позвонка " + String.join(", ", rSet.stream().filter(CheckBox::isSelected).map(Labeled::getText).toList()));
                vertebraeConclusionList.add(rearContourTextArea.getText());
            }
            Application.INSTANCE.getStudy().getVertebrae().setState(List.copyOf(vertebraeStateList));
            Application.INSTANCE.getStudy().getVertebrae().setProtocol(List.copyOf(vertebraeProtocolList));
            Application.INSTANCE.getStudy().getVertebrae().setConclusion(List.copyOf(vertebraeConclusionList));
        }
        Application.INSTANCE.initFinalWindow();
    }

    public void selectNorm() {
        deviationBox.setVisible(false);
        rearContour.setSelected(false);
        wedgeDeformity.setSelected(false);
        sizeReduction.setSelected(false);
        vertebraeLabel.setText("Позвонки: физиологической конфигурации");
    }

    public void selectDeviation() {
        deviationBox.setVisible(true);
        wedgeDeformityBox.setVisible(false);
        rearContourBox.setVisible(false);
        wedgeDeformityBox.setPrefHeight(0);
        rearContourBox.setPrefHeight(0);
        rearContour.setSelected(false);
        wedgeDeformity.setSelected(false);
        sizeReduction.setSelected(false);
        vertebraeLabel.setText("Позвонки: " + String.join(",\n", vertebraeStateList));
    }

    public void selectSizeReduction() {
    }

    public void selectWedgeDeformity() {

    }

    public void selectRearContour() {

    }
}