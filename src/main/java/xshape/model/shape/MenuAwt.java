package xshape.model.shape;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import xshape.vue.AwtContext;

public class MenuAwt extends Menu{

    public MenuAwt(String title, Point2D pos, Point2D size, boolean selected) {
        super(title, pos, size, selected);
    }

    @Override
    public void draw() {
        Graphics2D g = (Graphics2D) AwtContext.instance().graphics();
		Point2D pos = visiblePosition();
		Point2D size = visibleSize();
        g.setColor(Color.GRAY);
        g.fillRect((int)(pos.getX() - size.getX()/2),
        (int)(pos.getY() - size.getY()/2),        
        (int)(size.getX()),
        (int)(size.getY()));

        Graphics2D g2 = (Graphics2D) AwtContext.instance().graphics();
        g2.setColor(Color.BLACK);
		g2.drawString(title(), (int)(pos.getX() - size.getX()/2), (int)(pos.getY() - size.getY()/2));
	}

    @Override
    public void remove() {}
	@Override public boolean isInside(Point2D pos){ return pos.getX() > position().getX() - size().getX() / 2 && pos.getX() < position().getX() + size().getX() / 2 && pos.getY() - 8 > position().getY() && pos.getY() - 8 <= position().getY() + size().getY();}
	@Override public Object adapted() { return null;}
    
}
