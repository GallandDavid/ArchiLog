package xshape.model.shape;

import java.awt.geom.Point2D;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public class WhiteBoardFx extends Rectangle{
    javafx.scene.shape.Rectangle _adapted;
    Group _grp;

    public WhiteBoardFx(double posX, double posY, double height, double width, boolean selected, Group grp) {
        super(new Point2D.Double(posX, posY),new Point2D.Double(width, height), selected);
        _adapted = new javafx.scene.shape.Rectangle();
        _grp = grp;
        _grp.getChildren().add(_adapted);
    }
    

	@Override public void draw() {
    if(!_grp.getChildren().contains(_adapted)) _grp.getChildren().add(_adapted);
		Point2D p = position();
		Point2D	s = size();
		_adapted.setX(p.getX()- s.getX()/2);
		_adapted.setY(p.getY()- s.getY()/2);
		_adapted.setWidth(s.getX());
		_adapted.setHeight(s.getY());
		_adapted.setFill(Color.WHITE);
        _adapted.toFront();
		}

	@Override public void remove() {
		_grp.getChildren().remove(_adapted);
		System.gc();
	}
    @Override public Object adapted() { return _adapted; }
    
}
