package xshape.model.shape;

import java.awt.geom.Point2D;

import xshape.model.observer.Iobserver;

public abstract class Rectangle extends Shape {

    public Rectangle(Point2D pos, Point2D size, boolean selected, Iobserver obs){
        super(pos, size, selected, obs);
    }

    public Rectangle(Point2D pos, Point2D size, Point2D visible_pos, Point2D visible_size, boolean selected, double prev_mouse_pos_X, double prev_mouse_pos_Y, String ID, boolean placed, int deepth, Iobserver obs){
        super(pos, size, visible_pos, visible_size, selected, prev_mouse_pos_X, prev_mouse_pos_Y, ID, placed, deepth, obs);
    }

    @Override public boolean equals(Object obj){ return super.equals(obj); }

    
}
