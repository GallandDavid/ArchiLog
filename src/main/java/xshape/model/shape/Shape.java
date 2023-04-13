package xshape.model.shape;

import java.awt.geom.Point2D;
import java.util.UUID;

import xshape.model.Interface.IShape;

public abstract class Shape implements IShape{
    private static int _max_deepth = -1;
    protected static double _pos_x = 200;
    protected static double _pos_y = 200;
    protected static double _size_x = 100;
    protected static double _size_y = 100;

    protected final String ID;
    public Point2D _pos = null;
    protected Point2D _size = null;

    protected boolean _grouped;
    protected boolean _selected;
    protected int _deepth;
    protected boolean _placed;
    protected Point2D _visible_pos = null;
    protected Point2D _visible_size = null;

	public Shape(Point2D pos, Point2D size, boolean selected, boolean grouped){
        _grouped = grouped;
        _max_deepth ++;
        ID = UUID.randomUUID().toString();
        _pos  = pos;
        _size = size;
        _visible_pos  = pos;
        _visible_size = size;
        _selected = selected;
        _placed = true;
        _deepth = _max_deepth;
    }

    public Shape(Point2D pos, Point2D size, Point2D visible_pos, Point2D visible_size, boolean selected, String ID, boolean placed, int deepth, boolean grouped){
        _grouped = grouped;
        _pos  = pos;
        _size = size;
        _visible_pos  = visible_pos;
        _visible_size = visible_size;
        _selected = selected;
        this.ID = ID;
        _placed = placed;
        _deepth = deepth;
    }

    @Override public boolean grouped() { return _grouped; }
	@Override public Point2D size() { return (Point2D) _size.clone(); }
    @Override public Shape size(Point2D vec) { _size = (Point2D) vec.clone(); return this; }
    @Override public Point2D position() { return (Point2D) _pos.clone(); }
    @Override public Shape position(Point2D position) { _pos = (Point2D) position.clone(); return this; }
    @Override public Shape translate(Point2D vec) { _pos =  new Point2D.Double(_pos.getX() + vec.getX(),_pos.getY() + vec.getY()); return this; }
    @Override public Shape visibleTranslate(Point2D vec) { _visible_pos = new Point2D.Double(_visible_pos.getX() + vec.getX(),_visible_pos.getY() + vec.getY()); return this; }
	@Override public Point2D visiblePosition() { return (Point2D) _visible_pos.clone(); }
	@Override public Shape visiblePosition(Point2D position) { _visible_pos = (Point2D) position.clone(); return this; }
	@Override public Point2D visibleSize() { return (Point2D) _visible_size.clone(); }
	@Override public Shape visibleSize(Point2D vec) { _visible_size = (Point2D) vec.clone(); return this; }
    @Override public String getId(){ return ID; }
    @Override public boolean selected() { return _selected; }
    @Override public void selected(boolean selected) { _selected = selected; }
    @Override public boolean isPlaced() { return _placed; }
    @Override public void setPlaced(boolean placed) { _placed = placed; }
    @Override public int deepth(){ return _deepth; }
    @Override public void remove() { }

    @Override public String toString(){
        String str = "Rectangle :\n";
        str += "Pos : (" + _pos.getX() + ", " + _pos.getY() + ")   |   ";
        str += "Size : (" + _size.getX() + ", " + _size.getY() + ")   |   ";
        str += "Visble Pos : (" + _visible_pos.getX() + ", " + _visible_pos.getY() + ")   |   ";
        str += "Visble Size : (" + _visible_size.getX() + ", " + _visible_size.getY() + ")\n";
        str += "Ref : " + getId() + "   |   ";
        str += "Selected : " + _selected;
        str += "Deepth : " + _deepth;
        return str;
    }

    @Override public boolean equals(Object obj){
        if(obj == this) return true;
        Shape object = (Shape) obj;
        boolean ret = true;
        if(!object.getId().equals(this.getId())){ ret = false; }
        if(!_pos.equals(object._pos)){ ret = false; }
        if(!_size.equals(object._size)){ ret = false; }
        if(!_visible_pos.equals(object._visible_pos)){ ret = false; }
        if(!_visible_size.equals(object._visible_size) ){ ret = false; }
        return ret;
    }

    @Override public void duplicate(Shape shape){
        this._deepth = shape.deepth();
        this._pos = (shape.position());
        this._size =(shape.size());
        this._visible_pos =(shape.visiblePosition());
        this._visible_size =(shape.visibleSize());
        this._selected = (shape.selected());
        this._placed = shape.isPlaced();
        _grouped = shape.grouped();
    }
}
