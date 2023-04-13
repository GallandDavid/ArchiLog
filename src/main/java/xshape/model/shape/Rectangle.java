package xshape.model.shape;

import java.awt.geom.Point2D;

import xshape.model.visitor.DrawVisitor;

public class Rectangle extends Shape {

    public Rectangle(Point2D pos, Point2D size, boolean selected){
        super(pos, size, selected, false);
    }
    public Rectangle(Point2D pos, boolean selected){
        super(pos, selected, false);
    }

    public Rectangle(Point2D pos, Point2D size, Point2D visible_pos, Point2D visible_size, boolean selected, String ID, boolean placed, int deepth){
        super(pos, size, visible_pos, visible_size, selected, ID, placed, deepth, false);
    }

    @Override public boolean equals(Object obj){ return super.equals(obj); }
    @Override public boolean isInside(Point2D pos){ return pos.getX() > position().getX() - size().getX() / 2 && pos.getX() < position().getX() + size().getX() / 2 && pos.getY() > position().getY() - size().getY() / 2 && pos.getY() < position().getY() + size().getY() / 2; }

    @Override
    public void accept(DrawVisitor dv) {
        dv.drawRectangle(this);
    }


    
}
