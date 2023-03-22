package xshape.model;

import java.awt.geom.Point2D;

public interface Shape{
	void draw();
	Point2D size();
	Shape size(Point2D vec);
	Point2D position();
	Shape position(Point2D position);
	Shape translate(Point2D vec);
	String getId();
	boolean isSelected();
	String toString();
	void remove();
	void setPrevMousePosX(double X);
    void setPrevMousePosY(double Y);
    double getPrevMousePosX();
    double getPrevMousePosY();
    void setPrevMouse(double x, double y);
    Point2D getMouseVec(double x, double y);
	Point2D visiblePosition();
	Shape visiblePosition(Point2D position);
	Point2D visibleSize();
	Shape visibleSize(Point2D vec);
	Shape visibleTranslate(Point2D vec);
}
