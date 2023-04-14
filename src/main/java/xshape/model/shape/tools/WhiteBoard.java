package xshape.model.shape.tools;

import java.awt.geom.Point2D;

import xshape.model.Interface.IMovable;
import xshape.model.Interface.IResizeable;
import xshape.model.visitor.DrawVisitor;
import xshape.model.Interface.IShape;
import xshape.model.shape.Rectangle;

public class WhiteBoard implements IShape, IResizeable, IMovable{
    public Rectangle rect = null;

    public WhiteBoard(double posX, double posY, double height, double width, boolean selected) {
        rect = new Rectangle(new Point2D.Double(posX, posY),new Point2D.Double(width, height), selected);
    }

    @Override public boolean isInside(Point2D pos) { return rect.isInside(pos); }
    @Override public void selected(boolean sel) { rect.selected(sel); }
    @Override public boolean selected() { return rect.selected(); }
    @Override public Point2D size() { return rect.size(); }
    @Override public Point2D position() { return rect.position(); }
    @Override public IShape size(Point2D pos) { rect.size(pos);   return null; }
    @Override public void accept(DrawVisitor dv) { dv.drawWhiteBoard(this); }

    @Override
    public IShape translate(Point2D vec) {
        rect.translate(vec);
        return this;
    }

    @Override
    public IShape position(Point2D position) {
        rect.position(position);
        return this;}
    
    
}
