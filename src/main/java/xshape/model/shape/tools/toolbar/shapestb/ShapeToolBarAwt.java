package xshape.model.shape.tools.toolbar.shapestb;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import xshape.model.shape.RectangleAwt;
import xshape.model.shape.Shape;
import xshape.vue.AwtContext;

public class ShapeToolBarAwt extends ShapeToolBar{
    private static double _marge_in_vertical = 7.0;
    private static double _marge_in_horizontal = 7.0;
    private static double _height = 30;

    public ShapeToolBarAwt(Point2D pos, Point2D size, boolean selected, ArrayList<Shape> addons) {
        super(pos, size, selected, 
        new RectangleAwt(pos.getX(), (pos.getY() - (size.getY()/2)) + _marge_in_vertical + (_height/2), _height, size.getX() - _marge_in_horizontal * 2, selected),
                addons);
    }

    @Override
    public void draw() {
        Graphics2D g = (Graphics2D) AwtContext.instance().graphics();
		Point2D pos = visiblePosition();
		Point2D size = visibleSize();
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect((int)(pos.getX() - size.getX()/2),
        (int)(pos.getY() - size.getY()/2),        
        (int)(size.getX()),
        (int)(size.getY()));
        for (int i = 0; i < addons().size(); i ++) {
            addons().get(i).draw();
            Graphics2D g2 = (Graphics2D) AwtContext.instance().graphics();
            Point2D p = addons().get(i).visiblePosition();
            Point2D	s = addons().get(i).visibleSize();
            g2.setColor(Color.GRAY);
            g2.fillRect((int)(p.getX()- ((s.getX()/2) + 3)),
            (int)(p.getY()- ((s.getY()/2) + 3)),        
            (int)(s.getX() + 6),
            (int)(s.getY() + 6));
        }

        rect().draw();
        
    }

    @Override public void remove() {}
    @Override public Object adapted() { return null; }

}
