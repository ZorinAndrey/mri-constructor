package ru.azor.mri_constructor;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SecondController implements Initializable {
    @FXML
    public Label bendingAngle;
    @FXML
    public Label topOfTheBend;
    @FXML
    public Slider bendingAngleSlider;
    @FXML
    public Slider apexOfTheBendSlider;
    @FXML
    public Label fullNameLabel;
    @FXML
    public Label dateOfBirthLabel;
    @FXML
    public Label scoliosisTypeLabel;
    @FXML
    public Label bendingAngleLabel;
    @FXML
    public Label apexOfTheBendLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bendingAngleSlider.valueProperty().addListener((observable, oldValue, newValue) -> bendingAngleLabel.setText("Угол изгиба: " + newValue.intValue() + " градусов"));
        apexOfTheBendSlider.valueProperty().addListener((observable, oldValue, newValue) -> apexOfTheBendLabel.setText("Вершина изгиба на " + newValue.intValue() + "-м позвонке"));

        bendingAngleSlider.setValue(Application.INSTANCE.getStudy().getBendingAngle());
        apexOfTheBendSlider.setValue(Application.INSTANCE.getStudy().getApexOfTheBend());

        fullNameLabel.setText("Ф.И.О.: " + Application.INSTANCE.getStudy().getFullName());
        dateOfBirthLabel.setText("Дата рождения: " + Application.INSTANCE.getStudy().getDateOfBirth());
        if (Application.INSTANCE.getStudy().getScoliosisType() != null) {
            scoliosisTypeLabel.setText("Сколиоз: " + Application.INSTANCE.getStudy().getScoliosisType().getRus());
            bendingAngleLabel.setText("Угол изгиба: " + Application.INSTANCE.getStudy().getBendingAngle() + " градусов");
            apexOfTheBendLabel.setText("Вершина изгиба на " + Application.INSTANCE.getStudy().getApexOfTheBend() + "-м позвонке");
        } else {
            scoliosisTypeLabel.setText("Сколиоз: нет");
        }
    }

    @FXML
    protected void prevWindow() throws IOException {
        Application.INSTANCE.getStudy().setBendingAngle(bendingAngleSlider.getValue());
        Application.INSTANCE.getStudy().setApexOfTheBend((int) apexOfTheBendSlider.getValue());
        Application.INSTANCE.initFirstWindow();
    }

    @FXML
    public void nextWindow() throws IOException {
        Application.INSTANCE.getStudy().setBendingAngle(bendingAngleSlider.getValue());
        Application.INSTANCE.getStudy().setApexOfTheBend((int) apexOfTheBendSlider.getValue());
        Application.INSTANCE.initThirdWindow();
    }

    public void selectBendingAngle() {
        bendingAngleLabel.setText("Угол изгиба: " + bendingAngleSlider.getValue());
    }

    public void selectApexOfTheBend() {
        apexOfTheBendLabel.setText("Вершина изгиба на " + apexOfTheBendSlider.getValue());
    }
}