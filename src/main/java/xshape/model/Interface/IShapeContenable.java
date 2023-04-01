package xshape.model.Interface;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.SortedMap;

import xshape.model.shape.Shape;

public interface IShapeContenable {
    Shape getShape(String ref);
    Shape[] getShapes();
    SortedMap<Integer, Shape> orderShapes();
    void addShape(Shape shape);
    void removeShape(String ref);
    Shape topShape(Point2D pos);
    void addShapeToPlaced(Shape shape);
    Shape placedShape();
    void selection(boolean sel);
    boolean selection();
    boolean asSelected();
    ArrayList<Shape> getSelected();
    int nbSelected();
}
