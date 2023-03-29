package xshape.model.shape;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class GroupAwt extends Group{
    public GroupAwt(Point2D pos, Point2D size, boolean selected, ArrayList<Shape> group) {
        super(pos, size, selected, group);
    }

    @Override
    public void draw() {
        for (Shape shape : group())
            shape.draw();
    }

    @Override
    public void remove() {
    }

    @Override
    public Object adapted() {
        return adapted();
    }
    
}
