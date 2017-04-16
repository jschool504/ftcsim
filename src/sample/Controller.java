package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Canvas canvas;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        System.out.println(canvas);
    }

}