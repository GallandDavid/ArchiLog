package xshape.model;

import java.awt.geom.Point2D;

import xshape.observer.Iobserver;

public abstract class Rectangle extends Shape {

    public Rectangle(Point2D pos, Point2D size, boolean selected, Iobserver obs){
        super(pos, size, selected, obs);
    }
}
