package xshape.model.shape.group;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import xshape.model.shape.Shape;

public class GroupFx extends Group{
    javafx.scene.Group _grp;
    Rectangle _adapted;

    public GroupFx(){
        super();
    }

    public GroupFx(ArrayList<Shape> shapes, javafx.scene.Group grp){
		super(shapes);
        _grp = grp;
        _adapted = new javafx.scene.shape.Rectangle();
        for (Shape s : group())
            _grp.getChildren().add((javafx.scene.shape.Shape) s.adapted());
	}

    public GroupFx(Point2D pos, Point2D size, boolean selected, ArrayList<Shape> group, javafx.scene.Group grp) {
        super(pos, size, selected, group);
        _grp = grp;
        _adapted = new javafx.scene.shape.Rectangle();
        for (Shape s : group())
            _grp.getChildren().add((javafx.scene.shape.Shape) s.adapted());
	}

    public GroupFx(Group group, javafx.scene.Group grp) {
        super(group);
        _grp = grp;
    }

    @Override public void draw() { 
        if(!_grp.getChildren().contains(_adapted)) _grp.getChildren().add(_adapted);
		Point2D p = visiblePosition();
		Point2D	s = visibleSize();
		_adapted.setX(p.getX()- s.getX()/2);
		_adapted.setY(p.getY()- s.getY()/2);
		_adapted.setWidth(s.getX());
		_adapted.setHeight(s.getY());
		_adapted.setFill(Color.LIGHTBLUE);
		
		if(selected()) {
			Light.Distant light = new Light.Distant();
			light.setAzimuth(0.0);
			light.setElevation(90.0);
	
			Lighting lighting = new Lighting();
			lighting.setLight(light);
			lighting.setSurfaceScale(1.0);
	
			_adapted.setEffect(lighting);
		}
		else{
			_adapted.setEffect(null);
		}
        _adapted.toFront();

        for (Shape shape : group()) shape.draw(); 
    }
    @Override public void remove() {
        for (Shape s : group())
            s.remove();
        _grp.getChildren().remove(_adapted); 
    }
    @Override public Object adapted() { return _adapted; }
    
}
