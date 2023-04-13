package xshape.model.abstractFactory;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import xshape.model.shape.Rectangle;
import xshape.model.shape.Shape;
import xshape.model.shape.WhiteBoard;
import xshape.model.shape.group.Group;
import xshape.model.shape.tools.popup.PopUpMenu;
import xshape.model.shape.tools.toolbar.shapestb.ShapeToolBar;
import xshape.model.shape.tools.toolbar.systemtb.SystemToolBar;

public class ShapeFactory {
    public Rectangle createRectangle(double posX, double posY, double height, double width, boolean selected){
        return new Rectangle(new Point2D.Double(posX, posY),new Point2D.Double(width, height), selected);
    }
    public Rectangle createRectangle(double posX, double posY, boolean selected){
        return new Rectangle(new Point2D.Double(posX, posY), selected);
    }
    public WhiteBoard createWhiteBoard(double posX, double posY, double height, double width, boolean selected){
        return new WhiteBoard(posX, posY, height, width, selected);
    }
    public Group createGroup(Point2D pos, Point2D size, boolean selected, ArrayList<Shape> group){
        return new Group(pos, size, selected, group);
    }
    public Group createGroup(ArrayList<Shape> group){
        return new Group(group);
    }
    public Group createGroup(Group grp){
        return new Group(grp);
    }
    public SystemToolBar createSystemToolBar(Point2D pos, Point2D size, boolean selected){
        return new SystemToolBar(pos, size, selected);
    }
    public ShapeToolBar createShapeToolBar(Point2D pos, Point2D size, boolean selected, ArrayList<Shape> addons){
        return new ShapeToolBar(pos, size, selected, addons);
    }
    public PopUpMenu createPopUpMenu(Point2D pos, int selected, boolean grouped){
        return new PopUpMenu(pos, selected, grouped);
    }
}

