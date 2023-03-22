package xshape.model;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.UUID;

import xshape.model.Command.Command;
import xshape.observer.Iobservable;
import xshape.observer.Iobserver;

public abstract class Rectangle implements Shape,Iobservable {

	Iobserver _app;
    private final UUID ID = UUID.randomUUID();
    private Point2D _pos;
    private Point2D _size;
    protected static Point2D _visible_pos;
    protected static Point2D _visible_size;
    protected boolean _selected;
    protected static double _pos_x = 200;
    protected static double _pos_y = 200;
    protected static double _size_x = 100;
    protected static double _size_y = 100;
    private double _prev_mouse_pos_X;
    private double _prev_mouse_pos_Y;
    

    public Rectangle(Point2D pos, Point2D size, boolean selected, Iobserver obs){
        registerOberver(obs);
        _pos  = pos;
        _size = size;
        _visible_pos  = _pos;
        _visible_size = _size;
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
    public Shape visibleTranslate(Point2D vec) {
        System.out.println("visibleTranslate(.)"+ getId());
        _visible_pos.setLocation(_visible_pos.getX() + vec.getX(),
                _visible_pos.getY() + vec.getY());
        return this;
    }


	@Override
	public Point2D visiblePosition() {
        System.out.println("visiblePosition()"+ getId());
        return (Point2D) _visible_pos.clone();
	}
	@Override
	public Shape visiblePosition(Point2D position) {
        System.out.println("visiblePosition(.)"+ getId());
        _visible_pos = (Point2D) position.clone();
        return this;
	}


	@Override
	public Point2D visibleSize() {
        System.out.println("visibleSize()"+ getId());
        return (Point2D) _visible_size.clone();
	}
	@Override
	public Shape visibleSize(Point2D vec) {
        System.out.println("visibleSize(.)"+ getId());
        _visible_size = (Point2D) vec.clone();
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

    @Override
	public void registerOberver(Iobserver obs) {
		_app = obs;
	}

	@Override
	public void unRegisterObserver(Iobserver obs) {
		_app = null;
	}

    /* 
    @Override
    public void notifyObservers(Command command){
        _app.update(command);
    }*/

    @Override
    public String toString(){
        String str = "Rectangle :\n";
        str += "Pos : (" + _pos.getX() + ", " + _pos.getY() + ")\n";
        str += "Size : (" + _size.getX() + ", " + _size.getY() + ")\n";
        str += "Visble Pos : (" + _visible_pos.getX() + ", " + _visible_pos.getY() + ")\n";
        str += "Visble Size : (" + _visible_size.getX() + ", " + _visible_size.getY() + ")\n";
        str += "Ref : " + getId() + "\n";
        str += "Selected : " + _selected;
        return str;
    }

    @Override
    public void setPrevMousePosX(double X){
        _prev_mouse_pos_X = X;
    }

    @Override
    public void setPrevMousePosY(double Y){
        _prev_mouse_pos_Y = Y;
    }

    @Override
    public void setPrevMouse(double x, double y){
        _prev_mouse_pos_X = x;
        _prev_mouse_pos_Y = y;
    }

    @Override
    public double getPrevMousePosX(){
        return _prev_mouse_pos_X;
    }

    @Override
    public double getPrevMousePosY(){
        return _prev_mouse_pos_Y;
    }

    @Override
    public Point2D getMouseVec(double x, double y){
        return new Point2D.Double(x - getPrevMousePosX(), y - getPrevMousePosY());
    }

	@Override
	public void notifyObservers(String code) {
		
	}

	@Override
	public void notifyObservers(String code, int X, int Y) {
		
	}

	@Override
	public void notifyObservers(String code, double X, double Y) {
		
	}

	@Override
	public void notifyObservers(String code, double X, double Y, String ref) {
		_app.update(code,X,Y,ref);
	}
}
