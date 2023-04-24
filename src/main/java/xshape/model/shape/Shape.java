package xshape.model.shape;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.UUID;
import xshape.model.Interface.IColorable;
import xshape.model.Interface.IManipulable;
import xshape.model.Interface.IMovable;
import xshape.model.Interface.IPlaceable;
import xshape.model.Interface.IResizeable;
import xshape.model.Interface.IRotatable;
import xshape.model.Interface.IShape;
import xshape.model.visitor.CreateEditToolBarVisitable;

public abstract class Shape implements IShape, IColorable, IRotatable, IManipulable, IPlaceable, IMovable, CreateEditToolBarVisitable, IResizeable{
    private static int _max_deepth = -1;
    protected static double _pos_x = 200;
    protected static double _pos_y = 200;
    

    protected final String ID;
    private Point2D _pos;

    private int _rotation;
    private Color _color;


    private boolean _grouped;
    protected boolean _selected;
    private int _deepth;
    private boolean _placed;
    private Point2D _visible_pos;

    public Shape(Point2D pos, boolean selected, boolean grouped, Color color){
        _grouped = grouped;
        _max_deepth ++;
        ID = UUID.randomUUID().toString();
        _pos  = pos;
        _visible_pos  = pos;
        _selected = selected;
        _placed = true;
        _deepth = _max_deepth;
        _rotation = 0;
        _color = color;
    }

    public Shape(Point2D pos, boolean selected, boolean grouped){
        _grouped = grouped;
        _max_deepth ++;
        ID = UUID.randomUUID().toString();
        _pos  = pos;
        _visible_pos  = pos;
        _selected = selected;
        _placed = true;
        _deepth = _max_deepth;
        _rotation = 0;
        _color = Color.BLUE;
    }

    public Shape(Point2D pos, Point2D visible_pos, boolean selected, String ID, boolean placed, int deepth, boolean grouped, int rotation, Color color){
        _grouped = grouped;
        _pos  = pos;
        _visible_pos  = visible_pos;
        _selected = selected;
        this.ID = ID;
        _placed = placed;
        _deepth = deepth;
        _rotation = rotation;
        _color = color;
    }

	abstract Shape visibleSize(Point2D vec);
    @Override public void color(Color color){ _color = color; }
    @Override public Color color(){ return _color; }
    @Override public Point2D centreRotation(){ return _pos; }
    @Override public int rotation(){ return _rotation; }
    @Override public void rotation(int rotation){ _rotation = rotation; }
    @Override public boolean grouped() { return _grouped; }
    @Override public Point2D position() { return (Point2D) _pos.clone(); }
    @Override public Shape position(Point2D position) { _pos = (Point2D) position.clone(); return this; }
    @Override public Shape translate(Point2D vec) { _pos =  new Point2D.Double(_pos.getX() + vec.getX(),_pos.getY() + vec.getY()); return this; }
    @Override public Shape visibleTranslate(Point2D vec) { _visible_pos = new Point2D.Double(_visible_pos.getX() + vec.getX(),_visible_pos.getY() + vec.getY()); return this; }
	@Override public Point2D visiblePosition() { return (Point2D) _visible_pos.clone(); }
	@Override public Shape visiblePosition(Point2D position) { _visible_pos = (Point2D) position.clone(); return this; }
    @Override public String getId(){ return ID; }
    @Override public boolean selected() { return _selected; }
    @Override public void selected(boolean selected) { _selected = selected; }
    @Override public boolean isPlaced() { return _placed; }
    @Override public void setPlaced(boolean placed) { _placed = placed; }
    @Override public int deepth(){ return _deepth; }

    @Override public String toString(){
        String str = "Rectangle :\n";
        str += "Pos : (" + _pos.getX() + ", " + _pos.getY() + ")   |   ";
        str += "Visble Pos : (" + _visible_pos.getX() + ", " + _visible_pos.getY() + ")   |   ";
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
        if(!_visible_pos.equals(object._visible_pos)){ ret = false; }
        return ret;
    }

    @Override public void duplicate(Shape shape){
        this._deepth = shape.deepth();
        this.position(shape.position());
        this.visiblePosition(shape.visiblePosition());
        this.selected(shape.selected());
        this._placed = shape.isPlaced();
        _grouped = shape.grouped();
    }
}
