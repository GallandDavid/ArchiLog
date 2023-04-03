package xshape.model.shape.group;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import xshape.model.shape.Shape;

public class GroupAwt extends Group{
    public GroupAwt(Point2D pos, Point2D size, boolean selected, ArrayList<Shape> group) {
        super(pos, size, selected, group);
    }

    public GroupAwt(ArrayList<Shape> group) {
        super(group);
    }

    public GroupAwt(Group grp) {
        super(grp);
    }

    public GroupAwt(){
        super();
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
        return null;
    }
    
}
