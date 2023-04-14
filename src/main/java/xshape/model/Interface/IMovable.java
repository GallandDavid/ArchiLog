package xshape.model.Interface;

import java.awt.geom.Point2D;


public interface IMovable {
    
	IShape translate(Point2D vec);
	IShape position(Point2D position);
}
