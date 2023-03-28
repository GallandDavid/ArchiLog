package xshape.model.shape;

import java.awt.geom.Point2D;

import xshape.model.observer.Iobserver;
import xshape.vue.AwtContext;

import java.awt.*;

public class RectangleAwt extends Rectangle {

	public RectangleAwt(RectangleAwt shape){
		super((Point2D) shape.position(), (Point2D) shape.size(), (Point2D) shape.visiblePosition(), (Point2D) shape.visibleSize(), shape.isMovable(), shape.getPrevMousePosX(), shape.getPrevMousePosY(), shape.getId(), shape.isPlaced(), shape.deepth(), shape._app);
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
        Graphics2D g = (Graphics2D) AwtContext.instance().graphics();
		Point2D pos = visiblePosition();
		Point2D size = visibleSize();
        g.setColor(Color.BLUE);
		if(selected()){
			AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
			g.setComposite(alphaComposite);
		}
		else{
			AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
			g.setComposite(alphaComposite);
		}
        g.fillRect((int)(pos.getX() - size.getX()/2),
        (int)(pos.getY() - size.getY()/2),        
        (int)(size.getX()),
        (int)(size.getY()));
	}

	@Override public void remove() { }
	@Override public boolean isInside(Point2D pos){ return pos.getX() > position().getX() - size().getX() / 2 && pos.getX() < position().getX() + size().getX() / 2 && pos.getY() - 8 > position().getY() && pos.getY() - 8 <= position().getY() + size().getY();}
	@Override public Object adapted() { return null;}

}
