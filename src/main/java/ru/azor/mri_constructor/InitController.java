package ru.azor.mri_constructor;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InitController implements Initializable {
    @FXML
    public TextField fullName;
    @FXML
    public TextField dataOfBirth;
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
        fullName.setText(Application.INSTANCE.getStudy().getFullName());
        dataOfBirth.setText(Application.INSTANCE.getStudy().getDateOfBirth());

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
    protected void nextWindow() throws IOException {
        Application.INSTANCE.getStudy().setFullName(fullName.getText());
        Application.INSTANCE.getStudy().setDateOfBirth(dataOfBirth.getText());
        Application.INSTANCE.initFirstWindow();
    }

    public void typedFullName() {
        fullNameLabel.setText("Ф.И.О.: " + fullName.getText());
    }

    public void typedDateOfBirth() {
        dateOfBirthLabel.setText("Дата рождения: " + dataOfBirth.getText());
    }
}