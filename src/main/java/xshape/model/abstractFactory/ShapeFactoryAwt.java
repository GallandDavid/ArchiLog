package xshape.model.abstractFactory;

import xshape.model.Rectangle;
import xshape.model.RectangleAwt;
import xshape.observer.Iobserver;

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
}
