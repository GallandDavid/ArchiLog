package xshape.model.shape;

import java.awt.geom.Point2D;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class SystemToolBarFx extends SystemToolBar{
    private javafx.scene.shape.Rectangle _adapted;
    Group _grp;

    public SystemToolBarFx(Point2D pos, Point2D size, boolean selected, Group grp) {
        super(pos, size, selected,
                new MenuFx("Files", pos, size, false, grp),
                new MenuFx("Edit", pos, size, false, grp),
                new MenuFx("", pos, size, false, grp));
        _grp = grp;
        _adapted = new Rectangle();
        _grp.getChildren().add(_adapted);
    }

    @Override
    public void draw() {
        if(!_grp.getChildren().contains(_adapted)) _grp.getChildren().add(_adapted);
		Point2D p = visiblePosition();
		Point2D	s = visibleSize();
		_adapted.setWidth(s.getX() - 10);
		_adapted.setHeight(s.getY() - 10);
        _adapted.setX(p.getX()- s.getX()/2);
		_adapted.setY(p.getY()- s.getY()/2);
		_adapted.setWidth(s.getX());
		_adapted.setHeight(s.getY());
		_adapted.setFill(Color.GRAY);
		_adapted.toBack();
		if(selected()) _adapted.setOpacity(0.5); else _adapted.setOpacity(1.0);
    
        files().draw();
        edit().draw();
        trashbin().draw();
    }

    @Override
    public void remove() {
        files().remove();
        edit().remove();
        trashbin().remove();
        _grp.getChildren().remove(_adapted);
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
