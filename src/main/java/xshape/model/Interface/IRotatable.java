package xshape.model.Interface;

import java.awt.geom.Point2D;

public interface IRotatable {
    Point2D centreRotation();
    int rotation();
    void rotation(int rotation);
}
