package gui.sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        Parent root = FXMLLoader.load(getClass().getResource("menhera-popup.fxml"));
        System.out.println(height + " " + width);
        Scene scene = new Scene(root, 330, 368, Color.TRANSPARENT);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("TEST");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setX(width - 322);
        primaryStage.setY(height - 364);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
