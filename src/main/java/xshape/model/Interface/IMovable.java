package xshape.model.Interface;

import java.awt.geom.Point2D;

import xshape.model.shape.Shape;

public interface IMovable {
    
	Shape translate(Point2D vec);
	Shape position(Point2D position);
}
