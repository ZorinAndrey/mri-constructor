package ru.azor.mri_constructor;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import ru.azor.mri_constructor.entities.MRIStudy;
import ru.azor.mri_constructor.services.DBService;

import java.io.IOException;

@Getter
@Setter
public class Application extends javafx.application.Application {

    public static Application INSTANCE;
    private static final String INIT_FXML = "init.fxml";
    private static final String FIRST_FXML = "first.fxml";
    private static final String SECOND_FXML = "second.fxml";
    private static final String THIRD_FXML = "final.fxml";
    private Stage initStage;
    private Stage firstStage;
    private Stage secondStage;
    private Stage thirdStage;
    private FXMLLoader initLoader;
    private FXMLLoader firstLoader;
    private FXMLLoader secondLoader;
    private FXMLLoader thirdLoader;
    private Parent root;
    private MRIStudy study;
    private DBService dbService;

    @Override
    public void init() {
        INSTANCE = this;
        study = new MRIStudy();
        dbService = new DBService();
    }

    @Override
    public void start(Stage stage) throws IOException {
        initStage = stage;
        initInitWindow();
    }

    @Override
    public void stop() throws Exception {
        dbService.closeConnection();
        super.stop();
    }

    void initInitWindow() throws IOException {
        initLoader = new FXMLLoader();
        initLoader.setLocation(getClass().getResource(INIT_FXML));
        root = initLoader.load();
        if (!initStage.isShowing()) {
            initStage = new Stage();
        }
        initStage.setScene(new Scene(root));
        initStage.setTitle("Пациент");
        initStage.getScene().getStylesheets().add(getClass().getResource("style/sky.css").toString());
        initStage.setOnCloseRequest(we -> {

        });
        initStage.show();
        if (firstStage != null && firstStage.isShowing()) {
            firstStage.close();
        }
    }

    void initFirstWindow() throws IOException {
        firstLoader = new FXMLLoader();
        firstLoader.setLocation(getClass().getResource(FIRST_FXML));
        root = firstLoader.load();
        firstStage = new Stage();
        firstStage.setScene(new Scene(root));
        firstStage.setTitle("Статика");
        firstStage.getScene().getStylesheets().add(getClass().getResource("style/sky.css").toString());
        firstStage.setOnCloseRequest(we -> {

        });
        firstStage.show();
        if (initStage != null && initStage.isShowing()) {
            initStage.close();
        }
        if (secondStage != null && secondStage.isShowing()) {
            secondStage.close();
        }
        if (thirdStage != null && thirdStage.isShowing()) {
            thirdStage.close();
        }
    }

    void initSecondWindow() throws IOException {
        secondLoader = new FXMLLoader();
        secondLoader.setLocation(getClass().getResource(SECOND_FXML));
        root = secondLoader.load();
        secondStage = new Stage();
        secondStage.setScene(new Scene(root));
        secondStage.setTitle("Сколиоз");
        secondStage.getScene().getStylesheets().add(getClass().getResource("style/sky.css").toString());
        secondStage.setOnCloseRequest(we -> {

        });
        secondStage.show();
        if (firstStage != null && firstStage.isShowing()) {
            firstStage.close();
        }
        if (thirdStage != null && thirdStage.isShowing()) {
            thirdStage.close();
        }
    }

    void initThirdWindow() throws IOException {
        thirdLoader = new FXMLLoader();
        thirdLoader.setLocation(getClass().getResource(THIRD_FXML));
        root = thirdLoader.load();
        thirdStage = new Stage();
        thirdStage.setScene(new Scene(root));
        thirdStage.setTitle("Third");
        thirdStage.getScene().getStylesheets().add(getClass().getResource("style/sky.css").toString());
        thirdStage.setOnCloseRequest(we -> {

        });
        thirdStage.show();
        if (firstStage != null && firstStage.isShowing()) {
            firstStage.close();
        }
        if (secondStage != null && secondStage.isShowing()) {
            secondStage.close();
        }
//        if (fourthStage != null && fourthStage.isShowing()){
//            fourthStage.close();
//        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}