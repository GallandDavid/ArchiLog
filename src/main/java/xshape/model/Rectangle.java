package xshape.model;

import java.awt.geom.Point2D;
import java.util.UUID;

public abstract class Rectangle implements Shape {
    private final UUID ID = UUID.randomUUID();
    private Point2D _pos;
    private Point2D _size;
    protected boolean _selected;
    private Point2D _default_pos = new Point2D.Double(200, 200);
    private Point2D _default_size = new Point2D.Double(100, 100);
    
    
    public Rectangle(){
        _pos  = _default_pos;
        _size = _default_size;
        _selected = false;
    }

    public Rectangle(boolean selected){
        _pos  = _default_pos;
        _size = _default_size;
        _selected = selected;
    }

    public Rectangle(Point2D pos){
        _pos  = pos;
        _size = _default_size;
        _selected = false;
    }

    public Rectangle(Point2D pos, boolean selected){
        _pos  = pos;
        _size = _default_size;
        _selected = selected;
    }

    public Rectangle(Point2D pos, Point2D size){
        _pos  = pos;
        _size = size;
        _selected = false;
    }

    public Rectangle(Point2D pos, Point2D size, boolean selected){
        _pos  = pos;
        _size = size;
        _selected = selected;
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

    @Override
    public String getId(){
        return ID.toString();
    }

    @Override
    public boolean equals(Object obj){
        Rectangle object = (Rectangle) obj;
        if(object.getId() == this.getId())
            return true;
        return false;
    }

    @Override
    public boolean isSelected(){
        return _selected;
    }
}
