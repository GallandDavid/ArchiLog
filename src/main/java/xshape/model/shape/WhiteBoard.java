package xshape.model.shape;

import java.awt.geom.Point2D;

public class WhiteBoard extends Shape{
    Rectangle rect = null;
    public WhiteBoard(double posX, double posY, double height, double width, boolean selected) {
		super(new Point2D.Double(posX, posY),new Point2D.Double(width, height), selected, false);
        rect = new Rectangle(new Point2D.Double(posX, posY),new Point2D.Double(width, height), selected);
    }

    @Override
    public boolean isInside(Point2D pos) {
        return rect.isInside(pos);
    }
}
