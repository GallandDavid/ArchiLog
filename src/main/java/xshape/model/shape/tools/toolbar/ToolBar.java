package xshape.model.shape.tools.toolbar;

import java.awt.geom.Point2D;

import xshape.model.shape.Rectangle;

public abstract class ToolBar extends Rectangle{
    public ToolBar(Point2D pos, Point2D size, boolean selected){
        super(pos, size, selected);
    }

    public abstract boolean isInItem(Point2D pos);
    
}
