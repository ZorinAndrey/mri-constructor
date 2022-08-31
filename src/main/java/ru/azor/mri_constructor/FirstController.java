package ru.azor.mri_constructor;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import ru.azor.mri_constructor.enums.ScoliosisType;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class FirstController implements Initializable {
    @FXML
    public RadioButton missing;
    @FXML
    public RadioButton present;
    @FXML
    public ComboBox<String> typeOfScoliosis;
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
        ToggleGroup group = new ToggleGroup();
        present.setToggleGroup(group);
        missing.setToggleGroup(group);
        typeOfScoliosis.setVisible(false);
        typeOfScoliosis.getItems().addAll(Arrays.stream(ScoliosisType.values()).map(ScoliosisType::getRus).toList());

        if (Application.INSTANCE.getStudy().getScoliosisType() != null) {
            group.selectToggle(present);
            typeOfScoliosis.setVisible(true);
            typeOfScoliosis.getSelectionModel().select(Application.INSTANCE.getStudy().getScoliosisType().getRus());
        }

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
    public void prevWindow() throws IOException {
        if (typeOfScoliosis.getSelectionModel().getSelectedItem() == null) {
            Application.INSTANCE.getStudy().setScoliosisType(null);
            Application.INSTANCE.getStudy().setBendingAngle(0);
            Application.INSTANCE.getStudy().setApexOfTheBend(0);
        } else {
            Application.INSTANCE.getStudy().setScoliosisType(Arrays.stream(ScoliosisType.values())
                    .filter(s -> s.getRus().equals(typeOfScoliosis.getSelectionModel().getSelectedItem()))
                    .findAny().orElse(null));
        }
        Application.INSTANCE.initInitWindow();
    }

    @FXML
    protected void nextWindow() throws IOException {
        if (typeOfScoliosis.getSelectionModel().getSelectedItem() != null) {
            Application.INSTANCE.getStudy().setScoliosisType(Arrays.stream(ScoliosisType.values())
                    .filter(s -> s.getRus().equals(typeOfScoliosis.getSelectionModel().getSelectedItem()))
                    .findAny().orElse(null));
            Application.INSTANCE.initSecondWindow();
        } else {
            Application.INSTANCE.getStudy().setScoliosisType(null);
            Application.INSTANCE.getStudy().setBendingAngle(0);
            Application.INSTANCE.getStudy().setApexOfTheBend(0);
            Application.INSTANCE.initThirdWindow();
        }
    }

    public void isMissing() {
        scoliosisTypeLabel.setText("Сколиоз: нет");
        bendingAngleLabel.setText("");
        apexOfTheBendLabel.setText("");
        typeOfScoliosis.setVisible(false);
        typeOfScoliosis.getSelectionModel().clearSelection();
    }

    public void isPresent() {
        scoliosisTypeLabel.setText("Сколиоз: ");
        typeOfScoliosis.setVisible(true);
    }


    public void selectScoliosisType() {
        if (typeOfScoliosis.getSelectionModel().getSelectedItem() != null) {
            scoliosisTypeLabel.setText("Сколиоз: " + typeOfScoliosis.getSelectionModel().getSelectedItem());
        }
    }
}