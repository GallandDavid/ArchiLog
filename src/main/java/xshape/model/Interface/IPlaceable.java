package xshape.model.Interface;

import java.awt.geom.Point2D;

public interface IPlaceable {
    boolean isPlaced();
    void setPlaced(boolean placed);
    int deepth();
    Point2D[] extremPoints();
}
