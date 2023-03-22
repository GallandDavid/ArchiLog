package xshape.model.shape;

import java.awt.geom.Point2D;

import xshape.observer.Iobserver;
import xshape.vue.AwtContext;

import java.awt.*;

public class RectangleAwt extends Rectangle {

	public RectangleAwt(Shape shape){
		this(shape.position().getX(), shape.position().getY(), shape.size().getX(), shape.size().getY(), shape.isSelected(), shape._app);
		visiblePosition(shape.visiblePosition());
		visibleSize(shape.visibleSize());
		_prev_mouse_pos_X = shape._prev_mouse_pos_X;
		_prev_mouse_pos_Y = shape._prev_mouse_pos_Y;
	}

	public RectangleAwt(Iobserver obs) {
		this(_pos_x, _pos_y, _size_x, _size_y, false, obs);
	}

    public RectangleAwt(boolean selected, Iobserver obs) {
		this(_pos_x, _pos_y, _size_x, _size_y, selected, obs);
	}

    public RectangleAwt(double posX, double posY, Iobserver obs) {
		this(posX, posY, _size_x, _size_y, false, obs);
	}

    public RectangleAwt(double posX, double posY, boolean selected, Iobserver obs) {
		this(posX, posY, _size_x, _size_y, selected, obs);
	}

    public RectangleAwt(double posX, double posY, double height, double width, Iobserver obs) {
		this(posX, posY, height, width, false, obs);
	}

    public RectangleAwt(double posX, double posY, double height, double width, boolean selected, Iobserver obs) {
		super(new Point2D.Double(posX, posY),new Point2D.Double(width, height), selected, obs);
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

	@Override
	public void remove() {
	}

	@Override
	public Object clone(){


		return null;
	}

}