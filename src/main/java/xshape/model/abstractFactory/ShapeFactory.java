package xshape.model.abstractFactory;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import xshape.model.observer.Iobserver;
import xshape.model.shape.Group;
import xshape.model.shape.Rectangle;
import xshape.model.shape.Shape;

public interface ShapeFactory {
    Rectangle createRectangle(Iobserver obs);
    Rectangle createRectangle(boolean selected, Iobserver obs);
    Rectangle createRectangle(double posX, double posY, Iobserver obs);
    Rectangle createRectangle(double posX, double posY, boolean selected, Iobserver obs);
    Rectangle createRectangle(double posX, double posY, double height, double width, Iobserver obs);
    Rectangle createRectangle(double posX, double posY, double height, double width, boolean selected, Iobserver obs);
    Group createGroup(Point2D pos, Point2D size, boolean selected, Iobserver obs, ArrayList<Shape> group);
}

