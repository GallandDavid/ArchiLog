package xshape.model.shape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import xshape.vue.AwtContext;

public class ShapeToolBarAwt extends ShapeToolBar{

    public ShapeToolBarAwt(Point2D pos, Point2D size, boolean selected, ArrayList<Shape> addons) {
        super(pos, size, selected, 
                new RectangleAwt(pos.getX(), pos.getY(), size.getX(), size.getY(), selected),
                addons);
    }

    @Override
    public void draw() {
        for (int i = 0; i < addons().size(); i ++) {
            addons().get(i).draw();
            Graphics2D g = (Graphics2D) AwtContext.instance().graphics();
            Point2D p = addons().get(i).visiblePosition();
            Point2D	s = addons().get(i).visibleSize();
            g.setColor(Color.GRAY);
            g.fillRect((int)(p.getX()- ((s.getX()/2) + 3)),
            (int)(p.getY()- ((s.getY()/2) + 3)),        
            (int)(s.getX() + 6),
            (int)(s.getY() + 6));
        }
        Graphics2D g = (Graphics2D) AwtContext.instance().graphics();
		Point2D pos = visiblePosition();
		Point2D size = visibleSize();
        g.setColor(Color.GRAY);
        g.fillRect((int)(pos.getX() - size.getX()/2),
        (int)(pos.getY() - size.getY()/2),        
        (int)(size.getX()),
        (int)(size.getY()));
    }

    @Override public void remove() {}
    @Override public boolean isInside(Point2D pos){ return pos.getX() > position().getX() - size().getX() / 2 && pos.getX() < position().getX() + size().getX() / 2 && pos.getY() - 8 > position().getY() && pos.getY() - 8 <= position().getY() + size().getY();}
	@Override public Object adapted() { return null;}

}
