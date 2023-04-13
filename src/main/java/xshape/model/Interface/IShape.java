package xshape.model.Interface;

import java.awt.geom.Point2D;

import xshape.model.shape.Shape;

public interface IShape extends IVisible, IManipulable, ISelectable, IPlaceable{
	String getId();
	Point2D size();
	boolean grouped();
	Point2D position();
	Shape size(Point2D vec);
	Shape position(Point2D position);
	Shape translate(Point2D vec);
	String toString();
}
