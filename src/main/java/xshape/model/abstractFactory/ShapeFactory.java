package xshape.model.abstractFactory;

import xshape.model.shape.Rectangle;
import xshape.observer.Iobserver;

public interface ShapeFactory {
    Rectangle createRectangle(Iobserver obs);
    Rectangle createRectangle(boolean selected, Iobserver obs);
    Rectangle createRectangle(double posX, double posY, Iobserver obs);
    Rectangle createRectangle(double posX, double posY, boolean selected, Iobserver obs);
    Rectangle createRectangle(double posX, double posY, double height, double width, Iobserver obs);
    Rectangle createRectangle(double posX, double posY, double height, double width, boolean selected, Iobserver obs);

}

