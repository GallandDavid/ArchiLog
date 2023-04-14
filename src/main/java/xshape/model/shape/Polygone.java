package xshape.model.shape;

import java.awt.geom.Point2D;

import xshape.model.visitor.DrawVisitor;

public class Polygone extends Shape{

    public Polygone(Point2D pos, boolean selected, boolean grouped) {
        super(pos, selected, grouped);
    }

    @Override
    public boolean isInside(Point2D pos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isInside'");
    }

    @Override
    public void accept(DrawVisitor dv) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'accept'");
    }
    
}
