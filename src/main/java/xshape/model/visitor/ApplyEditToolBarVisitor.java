package xshape.model.visitor;

import java.awt.Color;
import java.awt.geom.Point2D;

import xshape.model.shape.Group;
import xshape.model.shape.Polygone;
import xshape.model.shape.Rectangle;
import xshape.model.shape.tools.toolbar.editToolBar.EditToolBar;

public class ApplyEditToolBarVisitor {
    public void applyGroup(Group grp, EditToolBar etb){
        if(!etb.items()[0].inputs()[0].isEmpty() && !etb.items()[0].inputs()[1].isEmpty())
            if(etb.items()[0].inputs()[0].isEmpty()) grp.position(new Point2D.Double(grp.position().getX(), Double.valueOf(etb.items()[0].inputs()[1])));
            else if(etb.items()[0].inputs()[0].isEmpty()) grp.position(new Point2D.Double(Double.valueOf(etb.items()[0].inputs()[0]), grp.position().getY()));
            else grp.position(new Point2D.Double(Double.valueOf(etb.items()[0].inputs()[0]), Double.valueOf(etb.items()[0].inputs()[1])));
        grp.visiblePosition(grp.position());
        if(!etb.items()[1].inputs()[0].isEmpty() && !etb.items()[1].inputs()[0].isEmpty())
        if(etb.items()[1].inputs()[0].isEmpty()) grp.size(new Point2D.Double(grp.size().getX(), Double.valueOf(etb.items()[1].inputs()[1])));
        else if(etb.items()[1].inputs()[1].isEmpty())grp.size(new Point2D.Double(Double.valueOf(etb.items()[1].inputs()[0]), grp.size().getY()));
        else grp.size(new Point2D.Double(Double.valueOf(etb.items()[1].inputs()[0]), Double.valueOf(etb.items()[1].inputs()[1])));
        grp.visibleSize(grp.size());
        int r,g,b;
        if(etb.items()[2].inputs()[0].isEmpty()) r = grp.color().getRed();
        else r = (Double.valueOf(etb.items()[2].inputs()[0])).intValue();
        if(etb.items()[2].inputs()[1].isEmpty()) g = grp.color().getGreen();
        else g = (Double.valueOf(etb.items()[2].inputs()[1])).intValue();
        if(etb.items()[2].inputs()[2].isEmpty()) b = grp.color().getBlue();
        else b = (Double.valueOf(etb.items()[2].inputs()[2])).intValue();
        grp.color(new Color(r, g, b));
        if(!etb.items()[3].inputs()[0].isEmpty()) grp.rotation(Double.valueOf(Double.valueOf(etb.items()[3].inputs()[0]) % 360).intValue());
    }

    public void applyRectangle(Rectangle rect, EditToolBar etb){
        if(!etb.items()[0].inputs()[0].isEmpty() && !etb.items()[0].inputs()[1].isEmpty())
            if(etb.items()[0].inputs()[0].isEmpty()) rect.position(new Point2D.Double(rect.position().getX(), Double.valueOf(etb.items()[0].inputs()[1])));
            else if(etb.items()[0].inputs()[0].isEmpty()) rect.position(new Point2D.Double(Double.valueOf(etb.items()[0].inputs()[0]), rect.position().getY()));
            else rect.position(new Point2D.Double(Double.valueOf(etb.items()[0].inputs()[0]), Double.valueOf(etb.items()[0].inputs()[1])));
        rect.visiblePosition(rect.position());
        if(!etb.items()[1].inputs()[0].isEmpty() && !etb.items()[1].inputs()[0].isEmpty())
        if(etb.items()[1].inputs()[0].isEmpty()) rect.size(new Point2D.Double(rect.size().getX(), Double.valueOf(etb.items()[1].inputs()[1])));
        else if(etb.items()[1].inputs()[1].isEmpty())rect.size(new Point2D.Double(Double.valueOf(etb.items()[1].inputs()[0]), rect.size().getY()));
        else rect.size(new Point2D.Double(Double.valueOf(etb.items()[1].inputs()[0]), Double.valueOf(etb.items()[1].inputs()[1])));
        rect.visibleSize(rect.size());
        int r,g,b;
        if(etb.items()[2].inputs()[0].isEmpty()) r = rect.color().getRed();
        else r = (Double.valueOf(etb.items()[2].inputs()[0])).intValue();
        if(etb.items()[2].inputs()[1].isEmpty()) g = rect.color().getGreen();
        else g = (Double.valueOf(etb.items()[2].inputs()[1])).intValue();
        if(etb.items()[2].inputs()[2].isEmpty()) b = rect.color().getBlue();
        else b = (Double.valueOf(etb.items()[2].inputs()[2])).intValue();
        rect.color(new Color(r, g, b));
        if(!etb.items()[3].inputs()[0].isEmpty()) rect.rotation(Double.valueOf(Double.valueOf(etb.items()[3].inputs()[0]) % 360).intValue());
        if(!etb.items()[4].inputs()[0].isEmpty()) rect.rounded(Double.valueOf(Double.valueOf(etb.items()[4].inputs()[0]) % 360).intValue());
    }

    public void applyPolygone(Polygone poly, EditToolBar etb){
        if(!etb.items()[0].inputs()[0].isEmpty() && !etb.items()[0].inputs()[1].isEmpty())
            if(etb.items()[0].inputs()[0].isEmpty()) poly.position(new Point2D.Double(poly.position().getX(), Double.valueOf(etb.items()[0].inputs()[1])));
            else if(etb.items()[0].inputs()[0].isEmpty()) poly.position(new Point2D.Double(Double.valueOf(etb.items()[0].inputs()[0]), poly.position().getY()));
            else poly.position(new Point2D.Double(Double.valueOf(etb.items()[0].inputs()[0]), Double.valueOf(etb.items()[0].inputs()[1])));
        poly.visiblePosition(poly.position());
        if(!etb.items()[1].inputs()[0].isEmpty()) poly.side(Double.valueOf(etb.items()[1].inputs()[0]).intValue());
        if(!etb.items()[1].inputs()[1].isEmpty()) poly.length(Double.valueOf(etb.items()[1].inputs()[1]));
        int r,g,b;
        if(etb.items()[2].inputs()[0].isEmpty()) r = poly.color().getRed();
        else r = (Double.valueOf(etb.items()[2].inputs()[0])).intValue();
        if(etb.items()[2].inputs()[1].isEmpty()) g = poly.color().getGreen();
        else g = (Double.valueOf(etb.items()[2].inputs()[1])).intValue();
        if(etb.items()[2].inputs()[2].isEmpty()) b = poly.color().getBlue();
        else b = (Double.valueOf(etb.items()[2].inputs()[2])).intValue();
        poly.color(new Color(r, g, b));
        if(!etb.items()[3].inputs()[0].isEmpty()) poly.rotation(Double.valueOf(Double.valueOf(etb.items()[3].inputs()[0]) % 360).intValue());
    }
}
