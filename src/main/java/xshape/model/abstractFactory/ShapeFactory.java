package xshape.model.abstractFactory;

import xshape.model.Rectangle;

public interface ShapeFactory {
    Rectangle createRectangle(double posX, double posY, double height, double width);

}

