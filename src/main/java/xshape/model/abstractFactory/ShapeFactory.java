package xshape.model.abstractFactory;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import xshape.model.shape.Rectangle;
import xshape.model.shape.Shape;
import xshape.model.shape.group.Group;
import xshape.model.shape.tools.popup.PopUpMenu;
import xshape.model.shape.tools.toolbar.shapestb.ShapeToolBar;
import xshape.model.shape.tools.toolbar.systemtb.SystemToolBar;

public interface ShapeFactory {
    Rectangle createRectangle();
    Rectangle createRectangle(boolean selected);
    Rectangle createRectangle(double posX, double posY);
    Rectangle createRectangle(double posX, double posY, boolean selected);
    Rectangle createRectangle(double posX, double posY, double height, double width);
    Rectangle createRectangle(double posX, double posY, double height, double width, boolean selected);
    Rectangle createWhiteBoard(double posX, double posY, double height, double width, boolean selected);
    Group createGroup(Point2D pos, Point2D size, boolean selected, ArrayList<Shape> group);
    SystemToolBar createSystemToolBar(Point2D pos, Point2D size, boolean selected);
    ShapeToolBar createShapeToolBar(Point2D pos, Point2D size, boolean selected, ArrayList<Shape> addons);
    PopUpMenu createPopUpMenu(Point2D pos, int selected, boolean grouped);
}

