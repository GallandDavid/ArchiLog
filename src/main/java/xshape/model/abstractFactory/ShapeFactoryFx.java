package xshape.model.abstractFactory;

import xshape.model.shape.Rectangle;
import xshape.model.shape.RectangleFx;
import xshape.observer.Iobserver;
import javafx.scene.Group;

public class ShapeFactoryFx implements ShapeFactory {
    public static Group _grp;
    public ShapeFactoryFx(Group root) {
        _grp = root;
    }

    @Override
    public Rectangle createRectangle(Iobserver obs) {
        return new RectangleFx(_grp, obs);
    }
    
    @Override
    public Rectangle createRectangle(boolean selected, Iobserver obs) {
        return new RectangleFx(selected, _grp, obs);
    }
    
    @Override
    public Rectangle createRectangle(double posX, double posY, Iobserver obs) {
        return new RectangleFx(posX, posY, _grp, obs);
    }
    
    @Override
    public Rectangle createRectangle(double posX, double posY, boolean selected, Iobserver obs) {
        return new RectangleFx(posX, posY, selected, _grp, obs);
    }

    @Override
    public Rectangle createRectangle(double posX, double posY, double height, double width, Iobserver obs) {
        return new RectangleFx(posX, posY, height, width, _grp, obs);
    }

    @Override
    public Rectangle createRectangle(double posX, double posY, double height, double width, boolean selected, Iobserver obs) {
        return new RectangleFx(posX, posY, height, width, selected, _grp, obs);
    }
}
