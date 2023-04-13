package xshape.model.shape;

import java.awt.geom.Point2D;
import javafx.scene.Group;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;

public class RectangleFx extends Rectangle{

	javafx.scene.shape.Rectangle _adapted;
    Group _grp;
    
	public RectangleFx(RectangleFx shape){
		super((Point2D) shape.position().clone(), (Point2D) shape.size().clone(), (Point2D) shape.visiblePosition().clone(), (Point2D) shape.visibleSize().clone(), shape.selected(), shape.getId(), shape.isPlaced(), shape.deepth());
		_adapted = new javafx.scene.shape.Rectangle();
		_grp = shape._grp;
		_grp.getChildren().add(_adapted);
	}
	public RectangleFx( Group grp) { this(_pos_x, _pos_y, _size_y, _size_x, false, grp); }
    public RectangleFx(boolean selected, Group grp) { this(_pos_x, _pos_y, _size_y, _size_x, selected, grp); }
    public RectangleFx(double posX, double posY, Group grp) { this(posX, posY, _size_y, _size_x, false, grp); }
    public RectangleFx(double posX, double posY, boolean selected, Group grp) { this(posX, posY, _size_y, _size_x, selected, grp); }
    public RectangleFx(double posX, double posY, double height, double width, Group grp) { this(posX, posY, height, width, false, grp); }
    public RectangleFx(double posX, double posY, double height, double width, boolean selected, Group grp) {
		super(new Point2D.Double(posX, posY),new Point2D.Double(width, height), selected);
		_adapted = new javafx.scene.shape.Rectangle();
		_grp = grp;
		_grp.getChildren().add(_adapted);
	}

	@Override public void remove() {
		_grp.getChildren().remove(_adapted);
		System.gc();
	}
    @Override public Object adapted() { return _adapted; }
}
