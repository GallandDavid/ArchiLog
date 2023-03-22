package xshape.model;

import java.awt.geom.Point2D;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import xshape.observer.Iobserver;

public class RectangleFx extends Rectangle{

	javafx.scene.shape.Rectangle _adapted;
    Group _grp;
    

	public RectangleFx( Group grp, Iobserver obs) {
		this(_pos_x, _pos_y, _size_x, _size_y, false, grp, obs);
	}

    public RectangleFx(boolean selected, Group grp, Iobserver obs) {
		this(_pos_x, _pos_y, _size_x, _size_y, selected, grp, obs);
	}

    public RectangleFx(double posX, double posY, Group grp, Iobserver obs) {
		this(posX, posY, _size_x, _size_y, false, grp, obs);
	}

    public RectangleFx(double posX, double posY, boolean selected, Group grp, Iobserver obs) {
		this(posX, posY, _size_x, _size_y, selected, grp, obs);
	}

    public RectangleFx(double posX, double posY, double height, double width, Group grp, Iobserver obs) {
		this(posX, posY, height, width, false, grp, obs);
	}

    public RectangleFx(double posX, double posY, double height, double width, boolean selected, Group grp, Iobserver obs) {
		super(new Point2D.Double(posX, posY),new Point2D.Double(width, height), selected, obs);
		_adapted = new javafx.scene.shape.Rectangle();
		_adapted.setOnMouseDragged(new EventHandler <MouseEvent>()
        {
            public void handle(MouseEvent event)
            {
              notifyObservers("obj selected drag", event.getX(), event.getY(), getId());
            }
        });
    	_adapted.setOnMousePressed(new EventHandler <MouseEvent>()
        {
            public void handle(MouseEvent event)
            {
                notifyObservers("obj selected", event.getX(), event.getY(), getId());
                event.consume();
            }
        });
 
    	_adapted.setOnMouseReleased(new EventHandler <MouseEvent>()
        {
            public void handle(MouseEvent event)
            {
              notifyObservers("obj select place", event.getX(), event.getY(), getId());
              event.consume();
            }
        });
		_grp = grp;
		_grp.getChildren().add(_adapted);
	}

	@Override
	public void draw() {
		Point2D pos = position();
		Point2D size = size();
		_adapted.setX(pos.getX()- size.getX()/2);
		_adapted.setY(pos.getY()- size.getY()/2);
		_adapted.setWidth(size.getX());
		_adapted.setHeight(size.getY());
		_adapted.setFill(Color.BLUE);
		_adapted.toBack();
	}

	@Override
	public void remove() {
		_grp.getChildren().remove(_adapted);
	}
}
