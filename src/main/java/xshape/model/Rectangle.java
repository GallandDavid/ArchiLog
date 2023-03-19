package xshape.model;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.UUID;

import xshape.observer.Iobservable;
import xshape.observer.Iobserver;

public abstract class Rectangle implements Shape,Iobservable {

	ArrayList<Iobserver> _observers = new ArrayList<>();
    private final UUID ID = UUID.randomUUID();
    private Point2D _pos;
    private Point2D _size;
    protected boolean _selected;
    protected static double _pos_x = 200;
    protected static double _pos_y = 200;
    protected static double _size_x = 100;
    protected static double _size_y = 100;
    

    public Rectangle(Point2D pos, Point2D size, boolean selected, Iobserver obs){
        registerOberver(obs);
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

    @Override
	public void registerOberver(Iobserver obs) {
		_observers.add(obs);
	}

	@Override
	public void unRegisterObserver(Iobserver obs) {
		if(_observers.contains(obs))
			_observers.remove(obs);
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
		for(Iobserver obs : _observers)
			obs.update(code,X,Y,ref);
	}
}
