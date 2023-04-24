package xshape.model.shape.tools;

import java.awt.geom.Point2D;

import xshape.model.Interface.IShape;
import xshape.model.shape.Rectangle;
import xshape.model.visitor.DrawVisitor;

public class Menu implements IShape{
    Rectangle _rect = null;
    private String _title;


    public Menu(String title, Point2D pos, Point2D size, boolean selected){
        _rect = new Rectangle(pos, size, selected);
        _title = title;
    }
    

    /**
     * @return String return the _title
     */
    public String title() { return _title; }

    /**
     * @param _title the _title to set
     */
    public void title(String title) { _title = title; }


    @Override public boolean isInside(Point2D pos) { return _rect.isInside(pos); }
    @Override public void selected(boolean sel) { _rect.selected(sel); }
    @Override public boolean selected() { return _rect.selected(); }
    @Override public Point2D size() { return _rect.size(); }
    @Override public Point2D position() { return _rect.position(); }
    @Override public void accept(DrawVisitor dv) { dv.drawMenu(this); }
    @Override public IShape position(Point2D position) { _rect.position(position); return this;}

}
