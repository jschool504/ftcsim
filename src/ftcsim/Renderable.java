package ftcsim;

import javafx.scene.canvas.GraphicsContext;

/**
 * Created by jscho on 4/16/2017.
 */
public interface Renderable {
    /**
     * updates any necessary physics calculations
     * @param interval in milliseconds, time since last physics update, used to ensure that the animation speed does not change if fps changes
     */
    public void update(int interval);

    /**
     * Renders graphics onto the given GraphicsContext
     * @param gc GraphicsContext to use for rendering
     */
    public void draw(GraphicsContext gc);
}
