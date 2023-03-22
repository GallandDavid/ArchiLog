package xshape.model;

import java.awt.geom.Point2D;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import xshape.controleur.XShape;
import xshape.model.Command.ShapeDragCommand;
import xshape.model.Command.ShapeSelectCommand;
import xshape.model.Command.ShapeTranslateCommand;
import xshape.observer.Iobserver;

public class RectangleFx extends Rectangle{

	javafx.scene.shape.Rectangle _adapted;
    Group _grp;
    
	public RectangleFx(Shape shape){
		this(shape.position().getX(), shape.position().getY(), shape.size().getY(), shape.size().getX(), shape.isSelected(), ((RectangleFx)shape)._grp, shape._app);
		ID = shape.ID;
		visiblePosition(shape.visiblePosition());
		visibleSize(shape.visibleSize());
		_prev_mouse_pos_X = shape._prev_mouse_pos_X;
		_prev_mouse_pos_Y = shape._prev_mouse_pos_Y;
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
		RectangleFx rfx = this;
		_adapted = new javafx.scene.shape.Rectangle();
		_grp = grp;
		_grp.getChildren().add(_adapted);
    	_adapted.setOnMousePressed(new EventHandler <MouseEvent>(){
            public void handle(MouseEvent event){
                notifyObservers(new ShapeSelectCommand((XShape) _app, rfx, event.getX(), event.getY()));
				_selected = true;
				event.consume();
            }
        });
		_adapted.setOnMouseDragged(new EventHandler <MouseEvent>(){
            public void handle(MouseEvent event){
              	notifyObservers(new ShapeDragCommand((XShape) _app, rfx, event.getX(), event.getY()));
              	event.consume();
            }
        });
    	_adapted.setOnMouseReleased(new EventHandler <MouseEvent>(){
            public void handle(MouseEvent event){
              	notifyObservers(new ShapeTranslateCommand((XShape) _app, rfx, event.getX(), event.getY()));
				_selected = false;
              	event.consume();
            }
        });
	}

	@Override
	public void draw() {
		if(!_grp.getChildren().contains(_adapted))
			_grp.getChildren().add(_adapted);
		Point2D p = visiblePosition();
		Point2D	s = visibleSize();
		
		_adapted.setX(p.getX()- s.getX()/2);
		_adapted.setY(p.getY()- s.getY()/2);
		_adapted.setWidth(s.getX());
		_adapted.setHeight(s.getY());
		_adapted.setFill(Color.BLUE);
		_adapted.toBack();
	}

	@Override
	public void remove() {
		_grp.getChildren().remove(_adapted);
		System.gc();
	}
}
