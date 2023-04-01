package xshape.model.Interface;

import java.awt.geom.Point2D;

public interface IClickable {
    Point2D mousePos();
    void mousePos(Point2D pos);
    Point2D mousVec(Point2D pos);
}
