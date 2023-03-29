package xshape.model.shape;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class GroupFx extends Group{
    javafx.scene.Group _grp;
    javafx.scene.Group _adapted;

    public GroupFx(GroupFx shape, ArrayList<Shape> shapes){
		super((Point2D) shape.position().clone(), (Point2D) shape.size().clone(), (Point2D) shape.visiblePosition().clone(), (Point2D) shape.visibleSize().clone(), shape.isMovable(), shape.getPrevMousePosX(), shape.getPrevMousePosY(), shape.getId(), shape.isPlaced(), shape.deepth());
		group(shapes);
        _adapted = new javafx.scene.Group();
        for (Shape s : group())
            _adapted.getChildren().add((javafx.scene.shape.Shape) s.adapted());
		_grp = shape._grp;
		_grp.getChildren().add(_adapted);
	}

    public GroupFx(Point2D pos, Point2D size, boolean selected, ArrayList<Shape> group, javafx.scene.Group grp) {
        super(pos, size, selected, group);
        _grp = grp;
        _adapted = new javafx.scene.Group();
        for (Shape shape : group())
            _adapted.getChildren().add((javafx.scene.shape.Shape) shape.adapted());
    }

    @Override
    public void draw() {
        for (Shape shape : group())
            shape.draw();
    }

    @Override
    public void remove() {
        _grp.getChildren().remove(_adapted);
    }

    @Override
    public Object adapted() {
        return adapted();
    }
    
}
