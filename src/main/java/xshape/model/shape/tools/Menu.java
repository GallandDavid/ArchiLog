package xshape.model.shape.tools;

import java.awt.geom.Point2D;

import xshape.model.Interface.IShape;
import xshape.model.shape.Rectangle;
import xshape.model.visitor.DrawVisitor;

public class Menu implements IShape{
    Rectangle rect = null;
    private String _title;


    public Menu(String title, Point2D pos, Point2D size, boolean selected){
        rect = new Rectangle(pos, size, selected);
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


    @Override public boolean isInside(Point2D pos) { return rect.isInside(pos); }
    @Override public void selected(boolean sel) { }
    @Override public boolean selected() { return rect.selected(); }
    @Override public Point2D size() { return rect.size(); }
    @Override public Point2D position() { return rect.position(); }
    @Override public void accept(DrawVisitor dv) { dv.drawMenu(this); }

}
