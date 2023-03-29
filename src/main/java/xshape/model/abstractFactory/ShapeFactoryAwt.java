package xshape.model.abstractFactory;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import xshape.model.shape.Group;
import xshape.model.shape.GroupAwt;
import xshape.model.shape.Rectangle;
import xshape.model.shape.RectangleAwt;
import xshape.model.shape.Shape;

public class ShapeFactoryAwt implements ShapeFactory {
    public ShapeFactoryAwt() {
    }
    
    @Override
    public Rectangle createRectangle() {
        return new RectangleAwt();
    }
    
    @Override
    public Rectangle createRectangle(boolean selected) {
        return new RectangleAwt(selected);
    }
    
    @Override
    public Rectangle createRectangle(double posX, double posY) {
        return new RectangleAwt(posX, posY);
    }
    
    @Override
    public Rectangle createRectangle(double posX, double posY, boolean selected) {
        return new RectangleAwt(posX, posY, selected);
    }

    @Override
    public Rectangle createRectangle(double posX, double posY, double height, double width) {
        return new RectangleAwt(posX, posY, height, width);
    }

    @Override
    public Rectangle createRectangle(double posX, double posY, double height, double width, boolean selected) {
        return new RectangleAwt(posX, posY, height, width, selected);
    }

    @Override
    public Group createGroup(Point2D pos, Point2D size, boolean selected, ArrayList<Shape> group) {
        return new GroupAwt(pos, size, selected, group);
    }
}
