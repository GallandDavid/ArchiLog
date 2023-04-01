package xshape.model.Interface;

import java.awt.geom.Point2D;

import xshape.model.shape.Shape;

public interface IVisible {
	Point2D visiblePosition();
	Shape visiblePosition(Point2D position);
	Point2D visibleSize();
	Shape visibleSize(Point2D vec);
	Shape visibleTranslate(Point2D vec);
}
