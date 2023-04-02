package xshape.model.abstractFactory;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import xshape.model.shape.Rectangle;
import xshape.model.shape.RectangleAwt;
import xshape.model.shape.Shape;
import xshape.model.shape.WhiteBoardAwt;
import xshape.model.shape.group.Group;
import xshape.model.shape.group.GroupAwt;
import xshape.model.shape.tools.popup.PopUpMenu;
import xshape.model.shape.tools.popup.PopUpMenuAwt;
import xshape.model.shape.tools.toolbar.shapestb.ShapeToolBar;
import xshape.model.shape.tools.toolbar.shapestb.ShapeToolBarAwt;
import xshape.model.shape.tools.toolbar.systemtb.SystemToolBar;
import xshape.model.shape.tools.toolbar.systemtb.SystemToolBarAwt;

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

    @Override
    public Rectangle createWhiteBoard(double posX, double posY, double height, double width, boolean selected) {
        return new WhiteBoardAwt(posX, posY, height, width, selected);
    }

    @Override
    public Group createGroup(Point2D pos, Point2D size, boolean selected, ArrayList<Shape> group) {
        return new GroupAwt(pos, size, selected, group);
    }

    @Override public SystemToolBar createSystemToolBar(Point2D pos, Point2D size, boolean selected) { return new SystemToolBarAwt(pos, size, selected); }
    @Override public ShapeToolBar createShapeToolBar(Point2D pos, Point2D size, boolean selected, ArrayList<Shape> addons) { return new ShapeToolBarAwt(pos, size, selected, addons);    }
    @Override public PopUpMenu createPopUpMenu(Point2D pos, int selected, boolean grouped) { return new PopUpMenuAwt(pos, selected, grouped); }

}
