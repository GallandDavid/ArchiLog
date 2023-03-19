package xshape.model.abstractFactory;

import xshape.model.Rectangle;
import xshape.model.RectangleAwt;

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
}
