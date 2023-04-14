package xshape.model.shape.tools.toolbar;

import java.awt.geom.Point2D;

import xshape.model.Interface.IShape;
import xshape.model.shape.Rectangle;

public abstract class ToolBar implements IShape{
    Rectangle _rect = null;
    public ToolBar(Point2D pos, Point2D size, boolean selected){
        _rect = new Rectangle(pos,size,selected);
    }

    public abstract boolean isInItem(Point2D pos);

    @Override public boolean isInside(Point2D pos) { return _rect.isInside(pos); }
    @Override public void selected(boolean sel) { }
    @Override public boolean selected() { return _rect.selected(); }
    @Override public Point2D size() { return _rect.size(); }
    @Override public Point2D position() { return _rect.position(); }
    
}
