package xshape.model.shape;

import java.awt.geom.Point2D;
import xshape.vue.AwtContext;

import java.awt.*;

public class RectangleAwt extends Rectangle {

	public RectangleAwt(RectangleAwt shape){
		super((Point2D) shape.position(), (Point2D) shape.size(), (Point2D) shape.visiblePosition(), (Point2D) shape.visibleSize(), shape.selected(), shape.getId(), shape.isPlaced(), shape.deepth());
	}

	public RectangleAwt() {
		this(_pos_x, _pos_y, _size_x, _size_y, false);
	}

    public RectangleAwt(boolean selected) {
		this(_pos_x, _pos_y, _size_x, _size_y, selected);
	}

    public RectangleAwt(double posX, double posY) {
		this(posX, posY, _size_x, _size_y, false);
	}

    public RectangleAwt(double posX, double posY, boolean selected) {
		this(posX, posY, _size_x, _size_y, selected);
	}

    public RectangleAwt(double posX, double posY, double height, double width) {
		this(posX, posY, height, width, false);
	}

    public RectangleAwt(double posX, double posY, double height, double width, boolean selected) {
		super(new Point2D.Double(posX, posY),new Point2D.Double(width, height), selected);
	}

	@Override
	public void draw() {
        Graphics2D g = (Graphics2D) AwtContext.instance().graphics();
		Point2D pos = visiblePosition();
		Point2D size = visibleSize();
        
		if(selected()) g.setColor(new Color(100, 100, 255));
		else g.setColor(Color.BLUE);
        g.fillRect((int)(pos.getX() - size.getX()/2),
        (int)(pos.getY() - size.getY()/2),        
        (int)(size.getX()),
        (int)(size.getY()));
	}
    @Override public Object adapted() { return null; }
}
