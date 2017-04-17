package ftcsim;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Created by jscho on 4/16/2017.
 */
public class Robot implements Renderable {
    private double x;
    private double y;
    private double r;
    private double xSpeed = 0;
    private double ySpeed = 0;
    private double rSpeed = 0;
    private double frictionMultipler = 0.50f;
    private Image image;

    public Robot(double initialX, double initialY, double initialR) {
        this.x = initialX;
        this.y = initialY;
        this.r = Math.PI/initialR;
        this.image = new Image(String.valueOf(getClass().getClassLoader().getResource("resources/robot.png")));
    }

    public void setXSpeed(double newSpeed) {
        this.xSpeed = newSpeed;
    }

    public void setYSpeed(double newSpeed) {
        this.ySpeed = newSpeed;
    }

    @Override
    public void update(int interval) {
        float updatesPerSecond = 1000/interval;

        float xWall = 0;
        float yWall = 0;

        this.x += ((this.ySpeed + xWall)/2 * Math.cos(this.r));
        this.y += ((this.ySpeed + yWall)/2 * Math.sin(this.r));

        this.x -= (this.xSpeed/2 * Math.sin(this.r));
        this.y += (this.xSpeed/2 * Math.cos(this.r));
        this.r = (this.r + (this.rSpeed/updatesPerSecond));

        System.out.println(this.y);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.save();
        gc.translate(this.x, this.y);
        gc.rotate(this.r);
        gc.drawImage(this.image, -this.image.getWidth() / 2, -this.image.getHeight() / 2);
        gc.restore();
    }
}
