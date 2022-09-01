package ru.azor.mri_constructor;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import ru.azor.mri_constructor.enums.Axis;
import ru.azor.mri_constructor.enums.Lordosis;
import ru.azor.mri_constructor.enums.SagittalSize;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class StaticsController implements Initializable {
    @FXML
    public Label axisLabel;
    @FXML
    public Label lordosisLabel;
    @FXML
    public ComboBox<String> lordosis;
    @FXML
    public ComboBox<String> axis;
    @FXML
    public ComboBox<String> sagittalSize;
    @FXML
    public Label sagittalLabel;
    @FXML
    public TextArea sagittalTextArea;
    @FXML
    public Label sagittalTextAreaLabel;
    @FXML
    public Label spondylodiscitisLabel;
    public Label vertebraeLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lordosis.getItems().addAll(Arrays.stream(Lordosis.values()).map(Lordosis::getState).collect(Collectors.toSet()));
        axis.getItems().addAll(Arrays.stream(Axis.values()).map(Axis::getState).collect(Collectors.toSet()));
        sagittalSize.getItems().addAll(Arrays.stream(SagittalSize.values()).map(SagittalSize::getState).collect(Collectors.toSet()));
        if (Application.INSTANCE.getStudy().getSagittalSize().equals(SagittalSize.NORM)) {
            sagittalTextArea.setVisible(false);
        } else {
            sagittalTextAreaLabel.setText("в заключение при сниженном сагиттальном размере:");
            sagittalTextArea.setText(Application.INSTANCE.getStudy().getSagittalSize().getConclusion());
        }

        lordosisLabel.setText("Лордоз: " + Application.INSTANCE.getStudy().getLordosis().getState());
        axisLabel.setText("Ось: " + Application.INSTANCE.getStudy().getAxis().getState());
        sagittalLabel.setText("Сагиттальный размер: " + Application.INSTANCE.getStudy().getSagittalSize().getState());
        if (! Application.INSTANCE.getStudy().getSpondylodiscitis().getState().equals("Норма")){
            spondylodiscitisLabel.setText("Спондилодисцит: присутствует");
        }else {
            spondylodiscitisLabel.setText("Спондилодисцит: отсутствует");
        }
        if (!Application.INSTANCE.getStudy().getVertebrae().getState().get(0).equals("Норма")) {
            vertebraeLabel.setText("Позвонки: " + String.join(",\n", Application.INSTANCE.getStudy().getVertebrae().getState()));
        }else {
            vertebraeLabel.setText("Позвонки: физиологической конфигурации");
        }
    }

    @FXML
    protected void nextWindow() throws IOException {
        if (lordosis.getSelectionModel().getSelectedItem() != null) {
            Application.INSTANCE.getStudy().setLordosis(Arrays.stream(Lordosis.values())
                    .filter(l -> l.getState().equals(lordosis.getSelectionModel().getSelectedItem())).findFirst()
                    .orElse(Lordosis.NORM));
        }
        if (axis.getSelectionModel().getSelectedItem() != null) {
            Application.INSTANCE.getStudy().setAxis(Arrays.stream(Axis.values())
                    .filter(a -> a.getState().equals(axis.getSelectionModel().getSelectedItem())).findFirst()
                    .orElse(Axis.NORM));
        }
        if (sagittalSize.getSelectionModel().getSelectedItem() != null) {
            Application.INSTANCE.getStudy().setSagittalSize(Arrays.stream(SagittalSize.values())
                    .filter(s -> {
                        if (s.equals(SagittalSize.LOWERED)) {
                            s.setConclusion(sagittalTextArea.getText());
                        } else {
                            s.setConclusion("");
                        }
                        return s.getState().equals(sagittalSize.getSelectionModel().getSelectedItem());
                    }).findFirst()
                    .orElse(SagittalSize.NORM));
        }
        Application.INSTANCE.initSpondylodiscitisWindow();
    }

    public void selectLordosis() {
        if (lordosis.getSelectionModel().getSelectedItem() != null) {
            lordosisLabel.setText("Лордоз: " + lordosis.getSelectionModel().getSelectedItem());
        }
    }

    public void selectAxis() {
        if (axis.getSelectionModel().getSelectedItem() != null) {
            axisLabel.setText("Ось: " + axis.getSelectionModel().getSelectedItem());
        }
    }

    public void selectSagittalSize() {
        if (sagittalSize.getSelectionModel().getSelectedItem() != null) {
            sagittalLabel.setText("Сагиттальный размер: " + sagittalSize.getSelectionModel().getSelectedItem());
            if (sagittalSize.getSelectionModel().getSelectedItem().equals(SagittalSize.LOWERED.getState())) {
                sagittalTextArea.setVisible(true);
                sagittalTextAreaLabel.setText("в заключение при сниженном сагиттальном размере:");
                if (Application.INSTANCE.getStudy().getSagittalSize().getConclusion().startsWith(", стеноза")){
                    sagittalTextArea.setText(Application.INSTANCE.getStudy().getSagittalSize().getConclusion());
                }else {
                    sagittalTextArea.setText(", стеноза (ДОПИСАТЬ)");
                }
            } else {
                sagittalTextAreaLabel.setText("");
                sagittalTextArea.setText("");
                sagittalTextArea.setVisible(false);
            }
        }
    }
}