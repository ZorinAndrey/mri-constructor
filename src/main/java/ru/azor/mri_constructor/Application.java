package ru.azor.mri_constructor;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import ru.azor.mri_constructor.entities.MRIStudy;
import ru.azor.mri_constructor.entities.Spondylodiscitis;
import ru.azor.mri_constructor.entities.Vertebrae;
import ru.azor.mri_constructor.enums.Axis;
import ru.azor.mri_constructor.enums.Lordosis;
import ru.azor.mri_constructor.enums.SagittalSize;

import java.io.IOException;
import java.util.List;

@Getter
@Setter
public class Application extends javafx.application.Application {

    public static Application INSTANCE;
    private static final String STATICS_FXML = "statics.fxml";
    private static final String SPONDYLODISCITIS_FXML = "spondylodiscitis.fxml";
    private static final String VERTEBRAE_FXML = "vertebrae.fxml";
    private static final String FINAL_FXML = "final.fxml";
    private Stage staticsStage;
    private Stage spondylodiscitisStage;
    private Stage vertebraeStage;
    private Stage finalStage;
    private FXMLLoader staticsLoader;
    private FXMLLoader spondylodiscitisLoader;
    private FXMLLoader vertebraeLoader;
    private FXMLLoader finalLoader;
    private Parent root;
    private MRIStudy study;

    @Override
    public void init() {
        INSTANCE = this;
        study = new MRIStudy();
        study.setLordosis(Lordosis.NORM);
        study.setAxis(Axis.NORM);
        study.setSagittalSize(SagittalSize.NORM);
        study.setSpondylodiscitis(new Spondylodiscitis("Норма"));
        study.setVertebrae(new Vertebrae(List.of("Норма")));
    }

    @Override
    public void start(Stage stage) throws IOException {
        staticsStage = stage;
        initStaticsWindow();
    }

    void initStaticsWindow() throws IOException {
        staticsLoader = new FXMLLoader();
        staticsLoader.setLocation(getClass().getResource(STATICS_FXML));
        root = staticsLoader.load();
        if (!staticsStage.isShowing()) {
            staticsStage = new Stage();
        }
        staticsStage.setScene(new Scene(root));
        staticsStage.setTitle("Статика отдела");
        staticsStage.getScene().getStylesheets().add(getClass().getResource("style/sky.css").toString());
        staticsStage.setOnCloseRequest(we -> {

        });
        staticsStage.show();
        if (spondylodiscitisStage != null && spondylodiscitisStage.isShowing()) {
            spondylodiscitisStage.close();
        }
    }

    void initSpondylodiscitisWindow() throws IOException {
        spondylodiscitisLoader = new FXMLLoader();
        spondylodiscitisLoader.setLocation(getClass().getResource(SPONDYLODISCITIS_FXML));
        root = spondylodiscitisLoader.load();
        spondylodiscitisStage = new Stage();
        spondylodiscitisStage.setScene(new Scene(root));
        spondylodiscitisStage.setTitle("Спондилодисцит");
        spondylodiscitisStage.getScene().getStylesheets().add(getClass().getResource("style/sky.css").toString());
        spondylodiscitisStage.setOnCloseRequest(we -> {

        });
        spondylodiscitisStage.show();
        if (staticsStage != null && staticsStage.isShowing()) {
            staticsStage.close();
        }
        if (vertebraeStage != null && vertebraeStage.isShowing()) {
            vertebraeStage.close();
        }
    }

    void initVertebraeWindow() throws IOException {
        vertebraeLoader = new FXMLLoader();
        vertebraeLoader.setLocation(getClass().getResource(VERTEBRAE_FXML));
        root = vertebraeLoader.load();
        vertebraeStage = new Stage();
        vertebraeStage.setScene(new Scene(root));
        vertebraeStage.setTitle("Позвонки");
        vertebraeStage.getScene().getStylesheets().add(getClass().getResource("style/sky.css").toString());
        vertebraeStage.setOnCloseRequest(we -> {

        });
        vertebraeStage.show();
        if (spondylodiscitisStage != null && spondylodiscitisStage.isShowing()) {
            spondylodiscitisStage.close();
        }
        if (finalStage != null && finalStage.isShowing()) {
            finalStage.close();
        }
    }

    void initFinalWindow() throws IOException {
        finalLoader = new FXMLLoader();
        finalLoader.setLocation(getClass().getResource(FINAL_FXML));
        root = finalLoader.load();
        finalStage = new Stage();
        finalStage.setScene(new Scene(root));
        finalStage.setTitle("Final");
        finalStage.getScene().getStylesheets().add(getClass().getResource("style/sky.css").toString());
        finalStage.setOnCloseRequest(we -> {

        });
        finalStage.show();
        if (vertebraeStage != null && vertebraeStage.isShowing()) {
            vertebraeStage.close();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}