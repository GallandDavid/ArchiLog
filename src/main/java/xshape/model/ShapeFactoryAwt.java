package xshape.model;

public class ShapeFactoryAwt implements ShapeFactory {
    public ShapeFactoryAwt() {
    }
    @Override
    public Rectangle createRectangle(double posX, double posY, 
    double height, double width) {
        return new RectangleAwt(posX, posY, height, width);
    }
}
