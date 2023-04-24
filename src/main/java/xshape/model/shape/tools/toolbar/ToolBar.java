package xshape.model.shape.tools.toolbar;

import java.awt.geom.Point2D;

import xshape.model.Interface.IResizeable;
import xshape.model.Interface.IShape;
import xshape.model.shape.Rectangle;

public abstract class ToolBar implements IShape, IResizeable{
    Rectangle _rect = null;
    public ToolBar(Point2D pos, Point2D size, boolean selected){
        _rect = new Rectangle(pos,size,selected);
    }

    public abstract boolean isInItem(Point2D pos);

    public abstract void resize(double w, double h);

    @Override public boolean isInside(Point2D pos) { return _rect.isInside(pos); }
    @Override public void selected(boolean sel) { }
    @Override public boolean selected() { return _rect.selected(); }
    @Override public Point2D size() { return _rect.size(); }
    @Override public IShape size(Point2D vec) { _rect.size(vec); return _rect;}
    @Override public Point2D position() { return _rect.position(); }
    @Override public String toString(){ return _rect.toString(); }
    
}
