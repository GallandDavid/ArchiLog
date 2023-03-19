package xshape.model.abstractFactory;

import xshape.model.Rectangle;

public interface ShapeFactory {
    Rectangle createRectangle();
    Rectangle createRectangle(boolean selected);
    Rectangle createRectangle(double posX, double posY);
    Rectangle createRectangle(double posX, double posY, boolean selected);
    Rectangle createRectangle(double posX, double posY, double height, double width);
    Rectangle createRectangle(double posX, double posY, double height, double width, boolean selected);

}

