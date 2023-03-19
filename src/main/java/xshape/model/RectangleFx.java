package xshape.model;

import java.awt.geom.Point2D;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class RectangleFx extends Rectangle {

	javafx.scene.shape.Rectangle _adapted;
    Group _grp;

	public RectangleFx( Group grp) {
		super();
		_adapted = new javafx.scene.shape.Rectangle();
		_grp = grp;
		_grp.getChildren().add(_adapted);
		EventHandler<MouseEvent> drag_event = new EventHandler<MouseEvent>() { 
			@Override 
			public void handle(MouseEvent e) { 
				position(new Point2D.Double(e.getX(), e.getY()));
				draw();
			} 
		 };  
		if(isSelected())
			_adapted.setOnMouseMoved(drag_event);
			_adapted.setOnMouseReleased(e -> {
				_selected = false;
				_adapted.removeEventFilter(MouseEvent.MOUSE_MOVED, drag_event);
			});
	}

    public RectangleFx(boolean selected, Group grp) {
		super(selected);
		_adapted = new javafx.scene.shape.Rectangle();
		_grp = grp;
		_grp.getChildren().add(_adapted);
		EventHandler<MouseEvent> drag_event = new EventHandler<MouseEvent>() { 
			@Override 
			public void handle(MouseEvent e) { 
				position(new Point2D.Double(e.getX(), e.getY()));
				draw();
			} 
		 };  
		if(isSelected())
			_adapted.setOnMouseMoved(drag_event);
			_adapted.setOnMouseReleased(e -> {
				_selected = false;
				_adapted.removeEventFilter(MouseEvent.MOUSE_MOVED, drag_event);
			});
	}

    public RectangleFx(double posX, double posY, Group grp) {
		super(new Point2D.Double(posX, posY));
		_adapted = new javafx.scene.shape.Rectangle();
		_grp = grp;
		_grp.getChildren().add(_adapted);
		EventHandler<MouseEvent> drag_event = new EventHandler<MouseEvent>() { 
			@Override 
			public void handle(MouseEvent e) { 
				position(new Point2D.Double(e.getX(), e.getY()));
				draw();
			} 
		 };  
		if(isSelected())
			_adapted.setOnMouseMoved(drag_event);
			_adapted.setOnMouseReleased(e -> {
				_selected = false;
				_adapted.removeEventFilter(MouseEvent.MOUSE_MOVED, drag_event);
			});
	}

    public RectangleFx(double posX, double posY, boolean selected, Group grp) {
		super(new Point2D.Double(posX, posY),selected);
		_adapted = new javafx.scene.shape.Rectangle();
		_grp = grp;
		_grp.getChildren().add(_adapted);
		EventHandler<MouseEvent> drag_event = new EventHandler<MouseEvent>() { 
			@Override 
			public void handle(MouseEvent e) { 
				position(new Point2D.Double(e.getX(), e.getY()));
				draw();
			} 
		 };  
		if(isSelected())
			_adapted.setOnMouseMoved(drag_event);
			_adapted.setOnMouseReleased(e -> {
				_selected = false;
				_adapted.removeEventFilter(MouseEvent.MOUSE_MOVED, drag_event);
			});
	}

    public RectangleFx(double posX, double posY, double height, double width, Group grp) {
		super(new Point2D.Double(posX, posY),new Point2D.Double(width, height));
		_adapted = new javafx.scene.shape.Rectangle();
		_grp = grp;
		_grp.getChildren().add(_adapted);
		EventHandler<MouseEvent> drag_event = new EventHandler<MouseEvent>() { 
			@Override 
			public void handle(MouseEvent e) { 
				position(new Point2D.Double(e.getX(), e.getY()));
				draw();
			} 
		 };  
		if(isSelected())
			_adapted.setOnMouseMoved(drag_event);
			_adapted.setOnMouseReleased(e -> {
				_selected = false;
				_adapted.removeEventFilter(MouseEvent.MOUSE_MOVED, drag_event);
			});
	}

    public RectangleFx(double posX, double posY, double height, double width, boolean selected, Group grp) {
		super(new Point2D.Double(posX, posY),new Point2D.Double(width, height), selected);
		_adapted = new javafx.scene.shape.Rectangle();
		_grp = grp;
		_grp.getChildren().add(_adapted);
		EventHandler<MouseEvent> drag_event = new EventHandler<MouseEvent>() { 
			@Override 
			public void handle(MouseEvent e) { 
				position(new Point2D.Double(e.getX(), e.getY()));
				draw();
			} 
		 };  
		if(isSelected())
			_adapted.setOnMouseMoved(drag_event);
			_adapted.setOnMouseReleased(e -> {
				_selected = false;
				_adapted.removeEventFilter(MouseEvent.MOUSE_MOVED, drag_event);
			});
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
	}
}
