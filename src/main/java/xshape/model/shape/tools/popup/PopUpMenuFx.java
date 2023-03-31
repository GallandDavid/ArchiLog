package xshape.model.shape.tools.popup;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import xshape.model.shape.tools.menus.Menu;
import xshape.model.shape.tools.menus.MenuFx;

public class PopUpMenuFx extends PopUpMenu {
    Rectangle _adapted;
    Group _grp;

    public PopUpMenuFx(Point2D pos, int selected, boolean grouped, ArrayList<Menu> menus, Group grp) {
        super(pos, selected, grouped, 
                new MenuFx("Edit", new Point2D.Double(pos.getX(), pos.getY() - (_size.getY() / 3)), new Point2D.Double(_size.getX(), _size.getY() / 3), grouped, grp),
                new MenuFx("Group", pos, new Point2D.Double(_size.getX(), _size.getY() / 3), grouped, grp),
                new MenuFx("Un-Group", new Point2D.Double(pos.getX(), pos.getY() + (_size.getY() / 3)), new Point2D.Double(_size.getX(), _size.getY() / 3), grouped, grp)
                );
        _adapted = new Rectangle();
        _grp = grp;
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
        _adapted.toFront();
        edit().draw();
        group().draw();
        ungroup().draw();
        
    }

    @Override
    public void remove() {
        _grp.getChildren().remove(_adapted);
        edit().remove();
        group().remove();
        ungroup().remove();
    }

    @Override
    public Object adapted() {
        return _adapted;
    }
    
}
