package xshape.model.Interface;

import java.awt.geom.Point2D;

import xshape.model.visitor.DrawVisitable;

public interface IShape extends ISelectable, DrawVisitable{
	Point2D size();
	Point2D position();
	IShape position(Point2D position);
	String toString();
	boolean isInside(Point2D pos);
}
