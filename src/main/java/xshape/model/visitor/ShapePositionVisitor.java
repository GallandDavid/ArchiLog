package xshape.model.visitor;

import java.awt.geom.Point2D;
import xshape.model.shape.Rectangle;
import xshape.model.shape.Shape;
import xshape.model.shape.WhiteBoard;
import xshape.model.shape.group.Group;
import xshape.model.shape.tools.menus.Menu;
import xshape.model.shape.tools.popup.PopUpMenu;
import xshape.model.shape.tools.toolbar.shapestb.ShapeToolBar;
import xshape.model.shape.tools.toolbar.systemtb.SystemToolBar;

public class ShapePositionVisitor{

    public void position(Rectangle rect, Point2D pos) {
        rect.position(pos);
    }

    public void position(WhiteBoard wb, Point2D pos) {
        
    }

    public void position(ShapeToolBar stb, Point2D pos) {
        
    }

    public void position(SystemToolBar stb, Point2D pos) {
        
    }

    public void position(PopUpMenu pum, Point2D pos) {
        
    }

    public void position(Menu mn, Point2D pos) {
        
    }

    public void position(Group grp, Point2D pos) {
        //if(grp._pos == null) grp._pos = pos;
        //else{
            Point2D vec = new Point2D.Double(pos.getX() - grp.position().getX(), pos.getY() - grp.position().getY());
            for (Shape s : grp.group()) s.translate(vec); // correction
            grp.translate(vec); // correctioin
        //}
    }

    

    
}