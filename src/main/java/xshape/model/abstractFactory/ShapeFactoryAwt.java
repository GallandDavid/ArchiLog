package xshape.model.abstractFactory;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import xshape.model.observer.Iobserver;
import xshape.model.shape.Group;
import xshape.model.shape.GroupAwt;
import xshape.model.shape.Rectangle;
import xshape.model.shape.RectangleAwt;
import xshape.model.shape.Shape;

public class ShapeFactoryAwt implements ShapeFactory {
    public ShapeFactoryAwt() {
    }
    
    @Override
    public Rectangle createRectangle(Iobserver obs) {
        return new RectangleAwt(obs);
    }
    
    @Override
    public Rectangle createRectangle(boolean selected, Iobserver obs) {
        return new RectangleAwt(selected, obs);
    }
    
    @Override
    public Rectangle createRectangle(double posX, double posY, Iobserver obs) {
        return new RectangleAwt(posX, posY, obs);
    }
    
    @Override
    public Rectangle createRectangle(double posX, double posY, boolean selected, Iobserver obs) {
        return new RectangleAwt(posX, posY, selected, obs);
    }

    @Override
    public Rectangle createRectangle(double posX, double posY, double height, double width, Iobserver obs) {
        return new RectangleAwt(posX, posY, height, width, obs);
    }

    @Override
    public Rectangle createRectangle(double posX, double posY, double height, double width, boolean selected, Iobserver obs) {
        return new RectangleAwt(posX, posY, height, width, selected, obs);
    }

    @Override
    public Group createGroup(Point2D pos, Point2D size, boolean selected, Iobserver obs, ArrayList<Shape> group) {
        return new GroupAwt(pos, size, selected, obs, group);
    }
}
