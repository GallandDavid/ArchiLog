package xshape.model.visitor;

import java.awt.geom.Point2D;

import xshape.model.shape.Group;
import xshape.model.shape.Polygone;
import xshape.model.shape.Rectangle;
import xshape.model.shape.tools.EditItem;
import xshape.model.shape.tools.toolbar.editToolBar.EditToolBar;

public class CreateEditToolBarVisitor {
    public EditToolBar groupEditToolBar(Group grp, Point2D pos, Point2D size, double x, double y, double w, double h, double r, double g, double b, double orientation){
        EditItem[] items = new EditItem[4];
        String[] title0 = {"x :", "y :"};
        double[] input0 = {x,y};
        items[0] = new EditItem(new Point2D.Double(pos.getX(), pos.getY() - size.getY() / 2 + 30), new Point2D.Double(size.getX() - 2, 60), title0, input0);
        String[] title1 = {"width :", "height :"};
        double[] input1 = {w,h};
        items[1] = new EditItem(new Point2D.Double(pos.getX(), pos.getY() - size.getY() / 2 + 90), new Point2D.Double(size.getX() - 2, 60), title1, input1);
        String[] title2 = {"r :", "g :", "b :"};
        double[] input2 = {r,g,b};
        items[2] = new EditItem(new Point2D.Double(pos.getX(), pos.getY() - size.getY() / 2 + 165), new Point2D.Double(size.getX() - 2, 90), title2, input2);
        String[] title3 = {"orientation :"};
        double[] input3 = {orientation};
        items[3] = new EditItem(new Point2D.Double(pos.getX(), pos.getY() - size.getY() / 2 + 225), new Point2D.Double(size.getX() - 2, 30), title3, input3);

        return new EditToolBar(pos,size,false,items);

    }

    public EditToolBar rectangleEditToolBar(Rectangle grp, Point2D pos, Point2D size, double x, double y, double w, double h, double r, double g, double b, double orientation, double round){
    
        EditItem[] items = new EditItem[5];
        String[] title0 = {"x :", "y :"};
        double[] input0 = {x,y};
        items[0] = new EditItem(new Point2D.Double(pos.getX(), pos.getY() - size.getY() / 2 + 30), new Point2D.Double(size.getX() - 2, 60), title0, input0);
        String[] title1 = {"width :", "height :"};
        double[] input1 = {w,h};
        items[1] = new EditItem(new Point2D.Double(pos.getX(), pos.getY() - size.getY() / 2 + 90), new Point2D.Double(size.getX() - 2, 60), title1, input1);
        String[] title2 = {"r :", "g :", "b :"};
        double[] input2 = {r,g,b};
        items[2] = new EditItem(new Point2D.Double(pos.getX(), pos.getY() - size.getY() / 2 + 165), new Point2D.Double(size.getX() - 2, 90), title2, input2);
        String[] title3 = {"orientation :"};
        double[] input3 = {orientation};
        items[3] = new EditItem(new Point2D.Double(pos.getX(), pos.getY() - size.getY() / 2 + 225), new Point2D.Double(size.getX() - 2, 30), title3, input3);
        String[] title4 = {"round :"};
        double[] input4 = {orientation};
        items[4] = new EditItem(new Point2D.Double(pos.getX(), pos.getY() - size.getY() / 2 + 255), new Point2D.Double(size.getX() - 2, 30), title4, input4);

        return new EditToolBar(pos,size,false,items);
    }

    public EditToolBar polygoneEditToolBar(Polygone grp, Point2D pos, Point2D size, double x, double y, double side, double lenght, double r, double g, double b, double orientation){
        EditItem[] items = new EditItem[4];
        String[] title0 = {"x :", "y :"};
        double[] input0 = {x,y};
        items[0] = new EditItem(new Point2D.Double(pos.getX(), pos.getY() - size.getY() / 2 + 30), new Point2D.Double(size.getX() - 2, 60), title0, input0);
        String[] title1 = {"side :", "lenght :"};
        double[] input1 = {side,lenght};
        items[1] = new EditItem(new Point2D.Double(pos.getX(), pos.getY() - size.getY() / 2 + 90), new Point2D.Double(size.getX() - 2, 60), title1, input1);
        String[] title2 = {"r :", "g :", "b :"};
        double[] input2 = {r,g,b};
        items[2] = new EditItem(new Point2D.Double(pos.getX(), pos.getY() - size.getY() / 2 + 165), new Point2D.Double(size.getX() - 2, 90), title2, input2);
        String[] title3 = {"orientation :"};
        double[] input3 = {orientation};
        items[3] = new EditItem(new Point2D.Double(pos.getX(), pos.getY() - size.getY() / 2 + 225), new Point2D.Double(size.getX() - 2, 30), title3, input3);

        return new EditToolBar(pos,size,false,items);
    }
}
