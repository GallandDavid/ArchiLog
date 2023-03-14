package xshape.model.abstractFactory;

import xshape.model.Rectangle;
import xshape.model.RectangleFx;
import javafx.scene.Group;

public class ShapeFactoryFx implements ShapeFactory {
    Group grp;
    public ShapeFactoryFx(Group root) {
        grp = root;
    }
    @Override
    public Rectangle createRectangle(double posX, double posY, double height, double width) {
        return new RectangleFx(posX, posY, height, width, grp);
    }
}
