package xshape.model.shape;

import java.awt.geom.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import xshape.model.observer.Iobserver;

public class RectangleFx extends Rectangle{

	javafx.scene.shape.Rectangle _adapted;
    Group _grp;
    
	public RectangleFx(RectangleFx shape){
		super((Point2D) shape.position().clone(), (Point2D) shape.size().clone(), (Point2D) shape.visiblePosition().clone(), (Point2D) shape.visibleSize().clone(), shape.isMovable(), shape.getPrevMousePosX(), shape.getPrevMousePosY(), shape.getId(), shape.isPlaced(), shape.deepth(), shape._app);
		_adapted = new javafx.scene.shape.Rectangle();
		_grp = shape._grp;
		_grp.getChildren().add(_adapted);
	}

	public RectangleFx( Group grp, Iobserver obs) {
		this(_pos_x, _pos_y, _size_y, _size_x, false, grp, obs);
	}
    public RectangleFx(boolean selected, Group grp, Iobserver obs) {
		this(_pos_x, _pos_y, _size_y, _size_x, selected, grp, obs);
	}
    public RectangleFx(double posX, double posY, Group grp, Iobserver obs) {
		this(posX, posY, _size_y, _size_x, false, grp, obs);
	}
    public RectangleFx(double posX, double posY, boolean selected, Group grp, Iobserver obs) {
		this(posX, posY, _size_y, _size_x, selected, grp, obs);
	}
    public RectangleFx(double posX, double posY, double height, double width, Group grp, Iobserver obs) {
		this(posX, posY, height, width, false, grp, obs);
	}
    public RectangleFx(double posX, double posY, double height, double width, boolean selected, Group grp, Iobserver obs) {
		super(new Point2D.Double(posX, posY),new Point2D.Double(width, height), selected, obs);
		_adapted = new javafx.scene.shape.Rectangle();
		_grp = grp;
		_grp.getChildren().add(_adapted);
	}

	@Override
	public void draw() {
		if(!_grp.
		getChildren().
		contains(
			_adapted))
			_grp.getChildren().add(_adapted);
		Point2D p = visiblePosition();
		Point2D	s = visibleSize();
		
		_adapted.setX(p.getX()- s.getX()/2);
		_adapted.setY(p.getY()- s.getY()/2);
		_adapted.setWidth(s.getX());
		_adapted.setHeight(s.getY());
		_adapted.setFill(Color.BLUE);
		_adapted.toBack();
		if(isMovable()) _adapted.setStroke(Color.RED); else _adapted.setStroke(null);
		if(selected()) _adapted.setOpacity(0.5); else _adapted.setOpacity(1.0);
	}

	@Override
	public void remove() {
		_grp.getChildren().remove(_adapted);
		System.gc();
	}

	@Override
    public boolean equals(Object obj){
		return super.equals(obj);
	}

	@Override
	public boolean isInside(Point2D pos){
        return pos.getX() > position().getX() - size().getX() / 2 && pos.getX() < position().getX() + size().getX() / 2 && pos.getY() > position().getY() - size().getY() / 2 && pos.getY() < position().getY() + size().getY() / 2;
    }

	@Override
	public Object adapted() {
		return _adapted;
	}
}
