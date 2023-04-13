package xshape.model.abstractFactory;

import xshape.model.shape.Rectangle;
import xshape.model.shape.RectangleFx;
import xshape.model.shape.Shape;
import xshape.model.shape.WhiteBoardFx;
import xshape.model.shape.group.GroupFx;
import xshape.model.shape.tools.popup.PopUpMenu;
import xshape.model.shape.tools.popup.PopUpMenuFx;
import xshape.model.shape.tools.toolbar.shapestb.ShapeToolBar;
import xshape.model.shape.tools.toolbar.shapestb.ShapeToolBarFx;
import xshape.model.shape.tools.toolbar.systemtb.SystemToolBar;
import xshape.model.shape.tools.toolbar.systemtb.SystemToolBarFx;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import javafx.scene.Group;

public class ShapeFactoryFx implements ShapeFactory {
    public static Group _grp;
    public ShapeFactoryFx(Group root) {
        _grp = root;
    }

    @Override public Rectangle createRectangle() { return new RectangleFx(_grp); }
    @Override public Rectangle createRectangle(boolean selected) { return new RectangleFx(selected, _grp); }
    @Override public Rectangle createRectangle(double posX, double posY) { return new RectangleFx(posX, posY, _grp); }
    @Override public Rectangle createRectangle(double posX, double posY, boolean selected) { return new RectangleFx(posX, posY, selected, _grp); }
    @Override public Rectangle createRectangle(double posX, double posY, double height, double width) { return new RectangleFx(posX, posY, height, width, _grp); }
    @Override public Rectangle createRectangle(double posX, double posY, double height, double width, boolean selected) { return new RectangleFx(posX, posY, height, width, selected, _grp); }
    @Override public Rectangle createWhiteBoard(double posX, double posY, double height, double width, boolean selected) { return new WhiteBoardFx(posX, posY, height, width, selected, _grp); }
    @Override public xshape.model.shape.group.Group createGroup(Point2D pos, Point2D size, boolean selected, ArrayList<Shape> group ) { return new GroupFx(pos, size, selected, group, _grp); }
    @Override public SystemToolBar createSystemToolBar(Point2D pos, Point2D size, boolean selected) { return new SystemToolBarFx(pos, size, selected, _grp); }
    @Override public ShapeToolBar createShapeToolBar(Point2D pos, Point2D size, boolean selected, ArrayList<Shape> addons) { return new ShapeToolBarFx(pos, size, selected, addons, _grp);    }
    @Override public PopUpMenu createPopUpMenu(Point2D pos, int selected, boolean grouped) { return new PopUpMenuFx(pos, selected, grouped, _grp); }

    @Override
    public xshape.model.shape.group.Group createGroup(ArrayList<Shape> group) {
        return new GroupFx(group, _grp);
    }

    @Override
    public xshape.model.shape.group.Group createGroup(xshape.model.shape.group.Group grp) {
        return new GroupFx((GroupFx) grp); }
}
