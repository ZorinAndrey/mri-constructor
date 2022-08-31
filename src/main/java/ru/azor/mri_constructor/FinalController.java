package ru.azor.mri_constructor;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import ru.azor.mri_constructor.utils.DocumentUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FinalController implements Initializable {
    @FXML
    private Label label;
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
        label.setText("");
        if (Application.INSTANCE.getStudy().getScoliosisType() == null) {
            Application.INSTANCE.initFirstWindow();
        } else {
            Application.INSTANCE.initSecondWindow();
        }
    }

    @FXML
    public void addNewStudy() {
        if (Application.INSTANCE.getStudy().getId() == 0) {
            Application.INSTANCE.setStudy(Application.INSTANCE.getDbService().addNewStudy(Application.INSTANCE.getStudy()));
        } else {
            Application.INSTANCE.getDbService().updateStudy(Application.INSTANCE.getStudy());
        }
        DocumentUtil.createDocument();
        label.setText("Готово");
    }
}