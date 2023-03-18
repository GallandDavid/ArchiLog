package xshape.model;

import java.awt.geom.Point2D;

import xshape.vue.AwtContext;

import java.awt.*;

public class RectangleAwt extends Rectangle {

	public RectangleAwt() {
		super();
	}

    public RectangleAwt(boolean selected) {
		super(selected);
	}

    public RectangleAwt(double posX, double posY) {
		super(new Point2D.Double(posX, posY));
	}

    public RectangleAwt(double posX, double posY, boolean selected) {
		super(new Point2D.Double(posX, posY),selected);
	}

    public RectangleAwt(double posX, double posY, double height, double width) {
		super(new Point2D.Double(posX, posY),new Point2D.Double(width, height));
	}

    public RectangleAwt(double posX, double posY, double height, double width, boolean selected) {
		super(new Point2D.Double(posX, posY),new Point2D.Double(width, height), selected);
	}

	@Override
	public void draw() {
        Graphics g = AwtContext.instance().graphics();
        Color c = g.getColor();
		Point2D pos = position();
		Point2D size = size();
        g.setColor(Color.BLUE);
        g.fillRect((int)(pos.getX() - size.getX()/2),
        (int)(pos.getY() - size.getY()/2),        
        (int)(size.getX()),
        (int)(size.getY()));
        g.setColor(c);
	}

}
