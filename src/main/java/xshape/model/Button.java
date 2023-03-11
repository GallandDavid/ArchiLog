package xshape.model;
import java.awt.geom.Point2D;


public abstract class Button implements Shape{
    private Point2D _pos;
    private Point2D _size;
    private String _title;
    
    public Button(){
        _pos  = new Point2D.Double(0, 0);
        _size = new Point2D.Double(1, 1);
        _title = "Button";
    }

    public Button(Point2D pos, Point2D size, String title){
        this._pos  = pos;
        this._size = size;
        this._title = title;
    }

    @Override
    public Point2D size() {
        return (Point2D) _size.clone();
    }

    @Override
    public Shape size(Point2D vec) {
        _size = (Point2D) vec.clone();
        return this;
    }

    @Override
    public Point2D position() {
        return (Point2D) _pos.clone();
    }

    @Override
    public Shape position(Point2D position) {
        _pos = (Point2D) position.clone();
        return this;
    }

    @Override
    public Shape translate(Point2D vec) {
        _pos.setLocation(_pos.getX() + vec.getX(),
                _pos.getY() + vec.getY());
        return this;
    }

    public String title(){
        return _title;
    }
}
