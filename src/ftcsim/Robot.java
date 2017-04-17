package ftcsim;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Created by jscho on 4/16/2017.
 */
public class Robot implements Renderable {
    private float x;
    private float y;
    private float r;
    private float xSpeed;
    private float ySpeed;
    private float rSpeed;
    private Image image;

    public Robot(float initialX, float initialY, float initialR) {
        this.x = initialX;
        this.y = initialY;
        this.r = initialR;
        this.image = new Image(String.valueOf(getClass().getClassLoader().getResource("resources/robot.png")));
    }

    @Override
    public void update(int interval) {
        //System.out.println("update method");
    }

    @Override
    public void draw(GraphicsContext gc) {
        //System.out.println(gc.getClass());
        gc.save();
        gc.translate(this.x, this.y);
        gc.rotate(this.r);
        gc.drawImage(this.image, -this.image.getWidth() / 2, -this.image.getHeight() / 2);
        gc.restore();
    }
}
