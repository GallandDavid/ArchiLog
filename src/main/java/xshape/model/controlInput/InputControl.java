package xshape.model.controlInput;

import java.awt.geom.Point2D;

public class InputControl {
    private Button _right = new Button();
    private Button _left = new Button();
    private Button _ctrl = new Button();
    private boolean _moved = false;
    private double _x;
    private double _y;
    private char _c;
    private boolean _write = false;

    public InputControl(){}

    /**
     * @return MouseButton return the right
     */
    public Button right() { return _right; }

    /**
     * @return MouseButton return the left
     */
    public Button left() { return _left; }

    /**
     * @return MouseButton return the left
     */
    public Button ctrl() { return _ctrl; }

    public void writeChar(char c){ _c = c; }
    public char writeChar(){ return _c; }
    public void write(boolean write){ _write = write; }
    public boolean write(){ return _write; }
    public boolean rightPressed() { return right().pressEvent(); }
    public void rightPressed(boolean b) { right().pressEvent(b); }
    public boolean rightReleased() { return right().releaseEvent(); }
    public void rightReleased(boolean b) { right().releaseEvent(b); }
    public boolean leftPressed() { return left().pressEvent(); }
    public void leftPressed(boolean b) { left().pressEvent(b); }
    public boolean leftReleased() { return left().releaseEvent(); }
    public void leftReleased(boolean b) { left().releaseEvent(b); }
    public boolean ctrlPressed() { return ctrl().pressEvent(); }
    public void ctrlPressed(boolean b) { ctrl().pressEvent(b); }
    public boolean ctrlReleased() { return ctrl().releaseEvent(); }
    public void ctrlReleased(boolean b) { ctrl().releaseEvent(b); }

    /**
     * @return boolean return the _moved
     */
    public boolean mouseMoved() { return _moved; }

    /**
     * @param moved the _moved to set
     */
    public void moved(boolean moved) { _moved = moved; }

    /**
     * @return boolean return the _x and _y as Point2D
     */
    public Point2D position() { return new Point2D.Double(_x, _y); }

    /**
     * @param x the _x to set
     * @param y the _y to set
     */
    public void position(double x, double y) { 
        _x = x;
        _y = y;
    }

    public void print(){
        System.out.println("------------\nInput Control :\n" + 
        "Left : \n" +
        "Pressed : " + leftPressed() + " || Released : " + leftReleased() + " || Now : " + left().now() + "\n" +
        "Right : \n" +
        "Pressed : " + rightPressed() + " || Released : " + rightReleased() + " || Now : " + right().now() + "\n" +
        "Control : \n" +
        "Pressed : " + ctrlPressed() + " || Released : " + ctrlReleased() + " || Now : " + ctrl().now() + "\n" +
        "Moved : " + mouseMoved() + "\n");
    }
}
