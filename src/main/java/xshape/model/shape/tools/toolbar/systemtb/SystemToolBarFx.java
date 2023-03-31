package xshape.model.shape.tools.toolbar.systemtb;

import java.awt.geom.Point2D;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import xshape.model.shape.tools.menus.MenuFx;


public class SystemToolBarFx extends SystemToolBar{
    private javafx.scene.shape.Rectangle _adapted;
    Group _grp;
    private static double _width = 60;

    public SystemToolBarFx(Point2D pos, Point2D size, boolean selected, Group grp) {
        super(pos, size, selected,
                new MenuFx("Files", new Point2D.Double(_width / 2, size.getY() / 2), new Point2D.Double(_width, size.getY()), false, grp),
                new MenuFx("Edit", new Point2D.Double(_width + (_width / 2), size.getY() / 2), new Point2D.Double(_width, size.getY()), false, grp),
                new MenuFx("Bin", new Point2D.Double(2 * _width + (_width / 2), size.getY() / 2), new Point2D.Double(_width, size.getY()), false, grp));
        _grp = grp;
        _adapted = new Rectangle();
        _grp.getChildren().add(_adapted);
    }

    @Override
    public void draw() {
          
        files().draw();
        edit().draw();
        trashbin().draw();
        if(!_grp.getChildren().contains(_adapted)) _grp.getChildren().add(_adapted);
		Point2D p = visiblePosition();
		Point2D	s = visibleSize();
		_adapted.setWidth(s.getX());
		_adapted.setHeight(s.getY());
        _adapted.setX(p.getX()- s.getX()/2);
		_adapted.setY(p.getY()- s.getY()/2);
		_adapted.setWidth(s.getX());
		_adapted.setHeight(s.getY());
		_adapted.setFill(Color.LIGHTGRAY);
		_adapted.toBack();  
    }

    @Override
    public void remove() {
        files().remove();
        edit().remove();
        trashbin().remove();
        _grp.getChildren().remove(_adapted);
    }
    @Override public Object adapted() { return _adapted; }
}
