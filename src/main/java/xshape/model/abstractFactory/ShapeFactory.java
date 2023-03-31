package xshape.model.abstractFactory;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import xshape.model.shape.Rectangle;
import xshape.model.shape.Shape;
import xshape.model.shape.group.Group;

public interface ShapeFactory {
    Rectangle createRectangle();
    Rectangle createRectangle(boolean selected);
    Rectangle createRectangle(double posX, double posY);
    Rectangle createRectangle(double posX, double posY, boolean selected);
    Rectangle createRectangle(double posX, double posY, double height, double width);
    Rectangle createRectangle(double posX, double posY, double height, double width, boolean selected);
    Group createGroup(Point2D pos, Point2D size, boolean selected, ArrayList<Shape> group);
}

