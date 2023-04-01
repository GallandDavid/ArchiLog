package xshape.model.shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;

import xshape.vue.AwtContext;

public class WhiteBoardAwt extends Rectangle{

    public WhiteBoardAwt(double posX, double posY, double height, double width, boolean selected) {
		super(new Point2D.Double(posX, posY),new Point2D.Double(width, height), selected);
	}

    @Override
	public void draw() {
        Graphics g = AwtContext.instance().graphics();
		Point2D pos = visiblePosition();
		Point2D size = visibleSize();
        g.setColor(Color.WHITE);
        g.fillRect((int)(pos.getX() - size.getX()/2),
        (int)(pos.getY() - size.getY()/2),        
        (int)(size.getX()),
        (int)(size.getY()));
	}
    @Override public Object adapted() { return null; }
    
}
