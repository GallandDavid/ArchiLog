package xshape.model.abstractFactory;

import xshape.model.Rectangle;
import xshape.model.RectangleFx;
import javafx.scene.Group;

public class ShapeFactoryFx implements ShapeFactory {
    public static Group _grp;
    public ShapeFactoryFx(Group root) {
        _grp = root;
    }

    @Override
    public Rectangle createRectangle() {
        return new RectangleFx(_grp);
    }
    
    @Override
    public Rectangle createRectangle(boolean selected) {
        return new RectangleFx(selected, _grp);
    }
    
    @Override
    public Rectangle createRectangle(double posX, double posY) {
        return new RectangleFx(posX, posY, _grp);
    }
    
    @Override
    public Rectangle createRectangle(double posX, double posY, boolean selected) {
        return new RectangleFx(posX, posY, selected, _grp);
    }

    @Override
    public Rectangle createRectangle(double posX, double posY, double height, double width) {
        return new RectangleFx(posX, posY, height, width, _grp);
    }

    @Override
    public Rectangle createRectangle(double posX, double posY, double height, double width, boolean selected) {
        return new RectangleFx(posX, posY, height, width, selected, _grp);
    }
}
