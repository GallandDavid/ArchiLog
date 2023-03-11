package xshape.model;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public abstract class ToolBar implements Shape{
    private Point2D _pos;
    private Point2D _size;
    private ArrayList<Button> buttons = new ArrayList<>();
    
    public ToolBar(){
        _pos  = new Point2D.Double(400, 0);
        _size = new Point2D.Double(100, 500);
    }

    public ToolBar(Point2D pos, Point2D size, Button rect_button){
        this._pos  = pos;
        this._size = size;
        buttons.add(rect_button);
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
}
