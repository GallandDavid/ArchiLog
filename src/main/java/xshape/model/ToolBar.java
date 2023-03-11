package xshape.model;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public abstract class ToolBar{
    private Point2D _pos;
    private Point2D _size;
    private Button _rectbutton;
    public ToolBar(){
        _pos  = new Point2D.Double(400, 0);
        _size = new Point2D.Double(100, 500);
    }

    public ToolBar(Point2D pos, Point2D size, Button rect_button){
        this._pos  = pos;
        this._size = size;
        this._rectbutton = rect_button;
    }
    
    public Point2D size() {
        return (Point2D) _size.clone();
    }


    public ToolBar size(Point2D vec) {
        _size = (Point2D) vec.clone();
        return this;
    }


    public Point2D position() {
        return (Point2D) _pos.clone();
    }


    public ToolBar position(Point2D position) {
        _pos = (Point2D) position.clone();
        return this;
    }


    public ToolBar translate(Point2D vec) {
        _pos.setLocation(_pos.getX() + vec.getX(),
                _pos.getY() + vec.getY());
        return this;
    }

    public Button rectButton(){
        return _rectbutton;
    }
}
