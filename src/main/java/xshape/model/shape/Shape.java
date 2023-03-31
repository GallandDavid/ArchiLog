package xshape.model.shape;

import java.awt.geom.Point2D;
import java.util.UUID;

public abstract class Shape implements IShape{
    private static int _max_deepth = -1;
    protected static double _pos_x = 200;
    protected static double _pos_y = 200;
    protected static double _size_x = 100;
    protected static double _size_y = 100;

    protected final String ID;
    private Point2D _pos;
    private Point2D _size;

    private boolean _grouped;
    private boolean _selected;
    private int _deepth;
    private boolean _placed;
    private Point2D _visible_pos;
    private Point2D _visible_size;
    protected double _prev_mouse_pos_X;
    protected double _prev_mouse_pos_Y;

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

    public Shape(Point2D pos, Point2D size, Point2D visible_pos, Point2D visible_size, boolean selected, double prev_mouse_pos_X, double prev_mouse_pos_Y, String ID, boolean placed, int deepth, boolean grouped){
        _grouped = grouped;
        _pos  = pos;
        _size = size;
        _visible_pos  = visible_pos;
        _visible_size = visible_size;
        _selected = selected;
        _prev_mouse_pos_X = prev_mouse_pos_X;
        _prev_mouse_pos_Y = prev_mouse_pos_Y;
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
    @Override public void setPrevMousePosX(double X){ _prev_mouse_pos_X = X; }
    @Override public void setPrevMousePosY(double Y){ _prev_mouse_pos_Y = Y; }
    @Override public void setPrevMouse(double x, double y){ _prev_mouse_pos_X = x; _prev_mouse_pos_Y = y; }
    @Override public double getPrevMousePosX(){ return _prev_mouse_pos_X; }
    @Override public double getPrevMousePosY(){ return _prev_mouse_pos_Y; }
    @Override public Point2D getMouseVec(double x, double y){ return new Point2D.Double(x - getPrevMousePosX(), y - getPrevMousePosY()); }

    public void setSelected(Point2D mouse_pos){
        selected(true);
        setPrevMouse(mouse_pos.getX(), mouse_pos.getY());
    }

    @Override
    public String toString(){
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

    @Override
    public boolean equals(Object obj){
        if(obj == this) return true;
        Shape object = (Shape) obj;
        boolean ret = true;
        if(!object.getId().equals(this.getId())){
            System.out.println("Different ID");
            ret = false;
        }
        if(!_pos.equals(object._pos)){
            System.out.println("Different pos");
            ret = false;
        }
        if(!_size.equals(object._size)){
            System.out.println("Different size");
            ret = false;
        }
        if(!_visible_pos.equals(object._visible_pos)){
            System.out.println("Different visible pos");
            ret = false;
        }
        if(!_visible_size.equals(object._visible_size) ){
            System.out.println("Different visible size");
            ret = false;
        }

        if(_prev_mouse_pos_X != object._prev_mouse_pos_X ){
            System.out.println("Different mouse X");
            ret = false;
        }
        if(_prev_mouse_pos_Y != object._prev_mouse_pos_Y){
            System.out.println("Different mouse Y");
            ret = false;
        }
        return ret;
    }

    @Override
    public void duplicate(Shape shape){
        this._deepth = shape.deepth();
        this.position(shape.position());
        this.size(shape.size());
        this.visiblePosition(shape.visiblePosition());
        this.visibleSize(shape.visibleSize());
        this.selected(shape.selected());
        this._placed = shape.isPlaced();
        this._prev_mouse_pos_X = shape.getPrevMousePosX();
        this._prev_mouse_pos_Y = shape.getPrevMousePosY();
    }

    /**
     * @return boolean return the _placed
     */
    public boolean isPlaced() { return _placed; }

    /**
     * @param _placed the _placed to set
     */
    public void setPlaced(boolean placed) { _placed = placed; }
    public int deepth(){ return _deepth; }

    public boolean selected() {
        return _selected;
    }

    public void selected(boolean selected) {
        _selected = selected;
    }

}
