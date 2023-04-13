package xshape.model.Interface;

import java.awt.geom.Point2D;

public interface ISelectable {
	boolean isInside(Point2D pos);
    void selected(boolean sel);
    boolean selected();
}
