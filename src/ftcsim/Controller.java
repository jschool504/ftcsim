package ftcsim;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class Controller implements Initializable {
    @FXML
    private Canvas canvas;

    private Robot bot = new Robot(220, 560, 20);

    private Renderable[] objects = new Renderable[]{
        this.bot
    };

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        Image image = new Image(String.valueOf(getClass().getClassLoader().getResource("resources/vvmap.png")));
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.drawImage(image, 0, 0);

        new AnimationTimer() {

            private long oldTime;

            // Render loop
            public void handle(long currentTime) {
                for (Renderable obj : objects) {
                    obj.draw(gc);
                }
            }
        }.start();
        
        new Timer().schedule(new TimerTask() {
            public void run() {
                for (Renderable obj : objects) {
                    obj.update(10);
                }
            }
        }, 0, 10);
    }

}