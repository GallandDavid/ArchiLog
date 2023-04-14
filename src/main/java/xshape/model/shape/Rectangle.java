package xshape.model.shape;

import java.awt.geom.Point2D;

import xshape.model.visitor.DrawVisitor;

public class Rectangle extends Shape {

    public Rectangle(Point2D pos, Point2D size, boolean selected){
        super(pos, size, selected, false);
    }
    public Rectangle(Rectangle rect){
        super(rect.position(), rect.size(), rect.visiblePosition(), rect.visibleSize(), rect.selected(), rect.getId(), rect.isPlaced(), rect.deepth(), rect.grouped(), rect.rotation());
    }
    public Rectangle(Point2D pos, boolean selected){
        super(pos, selected, false);
    }

    @Override public boolean equals(Object obj){ return super.equals(obj); }
    @Override public boolean isInside(Point2D pos){ return pos.getX() > position().getX() - size().getX() / 2 && pos.getX() < position().getX() + size().getX() / 2 && pos.getY() > position().getY() - size().getY() / 2 && pos.getY() < position().getY() + size().getY() / 2; }

    @Override
    public void accept(DrawVisitor dv) {
        dv.drawRectangle(this);
    }


    
}
