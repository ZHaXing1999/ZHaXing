package test.managergui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        HelloApplication.stage=stage;
        stage.setTitle("SNMP管理系统");
        changeView("GUI.fxml");
        stage.show();
    }
    public static void changeView(String fxml){
        Parent root= null;
        try{
            root=FXMLLoader.load(HelloApplication.class.getResource(fxml));
            stage.setScene(new Scene(root));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch();
    }
}