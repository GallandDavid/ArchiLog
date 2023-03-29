package xshape.model.shape;

import java.awt.geom.Point2D;

public abstract class Rectangle extends Shape {

    public Rectangle(Point2D pos, Point2D size, boolean selected){
        super(pos, size, selected, false);
    }

    public Rectangle(Point2D pos, Point2D size, Point2D visible_pos, Point2D visible_size, boolean selected, double prev_mouse_pos_X, double prev_mouse_pos_Y, String ID, boolean placed, int deepth){
        super(pos, size, visible_pos, visible_size, selected, prev_mouse_pos_X, prev_mouse_pos_Y, ID, placed, deepth, false);
    }

    @Override public boolean equals(Object obj){ return super.equals(obj); }

    
}
