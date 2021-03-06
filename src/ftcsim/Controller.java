package ftcsim;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class Controller implements Initializable {
    @FXML
    private Canvas canvas;

    @FXML
    private Pane pane;

    private final int physicsUpdateInterval = 15; // slightly faster than 60 times per second

    private Robot bot = new Robot(220, 560, 20);

    private Renderable[] objects = new Renderable[]{
        this.bot
    };

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        Image image = new Image(String.valueOf(getClass().getClassLoader().getResource("resources/vvmap.png")));
        BackgroundImage backgroundImage = new BackgroundImage(image, null, null, null, null);
        Background fieldBackground = new Background(null, backgroundImage);
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        this.pane.setBackground(fieldBackground);

        // Animation update loop: we use different classes for the two loops for two reasons. AnimationTimer tries to
        // run the handle() method 60 times per second but does not guarantee that will be the case. Timer will run its
        // run() method on the period you given it, int this case, 10ms. Also, by seperating the two loops it is
        // possible to ensure that a change in the FPS does not affect the speed at which the physics calulcations
        // appear to be occurring.
        new AnimationTimer() {

            private long oldTime;

            // Render loop
            public void handle(long currentTime) {
                gc.clearRect(0, 0, 600, 600);
                
                for (Renderable obj : objects) {
                    obj.draw(gc);
                }
            }
        }.start();

        // physics update loop
        new Timer().schedule(new TimerTask() {
            public void run() {

                bot.setYSpeed(-2);

                for (Renderable obj : objects) {
                    obj.update(physicsUpdateInterval);
                }
            }
        }, 0, physicsUpdateInterval);
    }

}