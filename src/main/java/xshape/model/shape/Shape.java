package xshape.model.shape;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.UUID;

import xshape.model.Interface.IBoundsRoundable;
import xshape.model.Interface.IColorable;
import xshape.model.Interface.IManipulable;
import xshape.model.Interface.IMovable;
import xshape.model.Interface.IPlaceable;
import xshape.model.Interface.IResizeable;
import xshape.model.Interface.IRotatable;
import xshape.model.Interface.IShape;
import xshape.model.Interface.IVisible;

public abstract class Shape implements IShape, IColorable, IBoundsRoundable, IRotatable, IVisible, IManipulable, IPlaceable, IMovable, IResizeable{
    private static int _max_deepth = -1;
    protected static double _pos_x = 200;
    protected static double _pos_y = 200;
    protected static double _size_x = 100;
    protected static double _size_y = 100;
    

    protected final String ID;
    private Point2D _pos;
    private Point2D _size;

    private int _rotation;
    private int _rounde;
    private Color _color;


    private boolean _grouped;
    protected boolean _selected;
    private int _deepth;
    private boolean _placed;
    private Point2D _visible_pos;
    private Point2D _visible_size;

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
        _rotation = 0;
        _rounde = 0;
        _color = Color.BLUE;
    }

    public Shape(Point2D pos, boolean selected, boolean grouped){
        _grouped = grouped;
        _max_deepth ++;
        ID = UUID.randomUUID().toString();
        _pos  = pos;
        _size = new Point2D.Double(_size_x,_size_y);
        _visible_pos  = pos;
        _visible_size = _size;
        _selected = selected;
        _placed = true;
        _deepth = _max_deepth;
        _rotation = 0;
        _rounde = 0;
        _color = Color.BLUE;
    }

    public Shape(Point2D pos, Point2D size, Point2D visible_pos, Point2D visible_size, boolean selected, String ID, boolean placed, int deepth, boolean grouped, int rotation){
        _grouped = grouped;
        _pos  = pos;
        _size = size;
        _visible_pos  = visible_pos;
        _visible_size = visible_size;
        _selected = selected;
        this.ID = ID;
        _placed = placed;
        _deepth = deepth;
        _rotation = rotation;
        _rounde = 0;
        _color = Color.BLUE;
    }

    @Override public void color(Color color){ _color = color; }
    @Override public Color color(){ return _color; }
    @Override public int rounded(){ return _rounde; }
    @Override public void rounded(int rounde){ _rounde = rounde; }
    @Override public Point2D centreRotation(){ return _pos; }
    @Override public int rotation(){ return _rotation; }
    @Override public void rotation(int rotation){ _rotation = rotation; }
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
        this.position(shape.position());
        this.size(shape.size());
        this.visiblePosition(shape.visiblePosition());
        this.visibleSize(shape.visibleSize());
        this.selected(shape.selected());
        this._placed = shape.isPlaced();
        _grouped = shape.grouped();
    }
}
