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
    public Label sagittalLabel;
    @FXML
    public Label spondylodiscitisLabel;
    public Label vertebraeLabel;
    @FXML
    private Label label;
    @FXML
    public Label axisLabel;
    @FXML
    public Label lordosisLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
    protected void prevWindow() throws IOException {
        label.setText("");
        Application.INSTANCE.initVertebraeWindow();
    }

    @FXML
    public void addNewStudy() {
        DocumentUtil.createDocument();
        label.setText("Готово");
        System.out.println(Application.INSTANCE.getStudy());
    }
}