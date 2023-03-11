package xshape.model;
import java.awt.geom.Point2D;


public class Button{
    private Point2D _pos;
    private Point2D _size;
    private String _title;
    
    public Button(){
        _pos  = new Point2D.Double(0, 0);
        _size = new Point2D.Double(1, 1);
        _title = "Button";
    }

    public Button(double posX, double posY, double height, double width, String title){
        this._pos  = new Point2D.Double(posX, posY);
        this._size = new Point2D.Double(width, height);
        this._title = title;
    }

    public Point2D size() {
        return (Point2D) _size.clone();
    }

    public Button size(Point2D vec) {
        _size = (Point2D) vec.clone();
        return this;
    }

    public Point2D position() {
        return (Point2D) _pos.clone();
    }

    public Button position(Point2D position) {
        _pos = (Point2D) position.clone();
        return this;
    }

    public Button translate(Point2D vec) {
        _pos.setLocation(_pos.getX() + vec.getX(),
                _pos.getY() + vec.getY());
        return this;
    }

    public String title(){
        return _title;
    }
}
