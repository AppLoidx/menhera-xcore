package gui.sample;


import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class Controller {
    @FXML
    private ImageView menheraSprite;
    @FXML
    private Button menuBtn;

    @FXML
    private Button consoleBtn;

    @FXML
    private Button hideBtn;

    @FXML
    void menuClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../menu/menu.fxml"));
        Scene scene = new Scene(root, Color.TRANSPARENT);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Menu");
        primaryStage.show();
    }

    @FXML
    void consoleClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../console/console.fxml"));
        Scene scene = new Scene(root, Color.TRANSPARENT);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Console");
        primaryStage.show();
    }

    @FXML
    void hideClick(ActionEvent event) {
        hideBtn.getScene().getWindow().hide();
    }

    @FXML
    void spriteClick(MouseEvent event) {
        if (menuBtn.isDisabled()){
            showButtons();
            menheraSprite.setImage(new Image("resources/img/menhera/happy-ready.png"));
        } else {
            hideButtons();

            menheraSprite.setImage(new Image("resources/img/menhera/mm-thinking-cut.png"));
        }
    }

    @FXML
    void initialize() {
        hideButtons();
        menheraSprite.setOnMouseEntered(this::spriteMouseHandler);
        menheraSprite.setOnMouseExited((this::spriteMouseHandler));
    }

    @FXML
    private void spriteMouseHandler(MouseEvent event) {
        System.out.println(event.getEventType());
        if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
            final Timeline timeline = new Timeline();
            timeline.setCycleCount(2);
            timeline.setAutoReverse(true);
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(4000),
                    new KeyValue(menheraSprite.fitHeightProperty(), 190)));
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(4000),
                    new KeyValue(menheraSprite.fitWidthProperty(), 174)));
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(4000),
                    new KeyValue(menheraSprite.xProperty(), -20)));

            timeline.play();

       }
    }

    private void hideButtons() {
        menuBtn.setDisable(true);
        menuBtn.setOpacity(0);

        hideBtn.setDisable(true);
        hideBtn.setOpacity(0);

        consoleBtn.setDisable(true);
        consoleBtn.setOpacity(0);
    }

    private void showButtons(){
        menuBtn.setDisable(false);
        menuBtn.setOpacity(1);
        hideBtn.setDisable(false);
        hideBtn.setOpacity(1);
        consoleBtn.setDisable(false);
        consoleBtn.setOpacity(1);

    }


}
