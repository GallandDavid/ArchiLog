package xshape.model.Interface;

import java.awt.geom.Point2D;

import xshape.model.shape.Shape;


public interface IMovable {
    
	IShape translate(Point2D vec);

	Point2D visiblePosition();
	Shape visiblePosition(Point2D position);
	Point2D visibleSize();
	Shape visibleTranslate(Point2D vec);
}
