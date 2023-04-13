package xshape.model.shape;

import java.awt.geom.Point2D;

import xshape.model.Interface.IMovable;
import xshape.model.Interface.IResizeable;
import xshape.model.Interface.IShape;
import xshape.model.visitor.DrawVisitor;

public class WhiteBoard implements IShape, IResizeable, IMovable{
    Rectangle rect = null;

    public WhiteBoard(double posX, double posY, double height, double width, boolean selected) {
        rect = new Rectangle(new Point2D.Double(posX, posY),new Point2D.Double(width, height), selected);
    }

    @Override public boolean isInside(Point2D pos) { return rect.isInside(pos); }
    @Override public void selected(boolean sel) { }
    @Override public boolean selected() { return rect.selected(); }
    @Override public Point2D size() { return rect.size(); }
    @Override public Point2D position() { return rect.position(); }
    @Override public Shape size(Point2D pos) { rect.size(pos);   return null; }
    @Override public void accept(DrawVisitor dv) { dv.drawWhiteBoard(this); }

    @Override
    public Shape translate(Point2D vec) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'translate'");
    }

    @Override
    public Shape position(Point2D position) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'position'");
    }
    
    
}
