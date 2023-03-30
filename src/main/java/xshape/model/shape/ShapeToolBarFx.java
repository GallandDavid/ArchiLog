package xshape.model.shape;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ShapeToolBarFx extends ShapeToolBar{
    private javafx.scene.shape.Rectangle _adapted;
    private ArrayList<Rectangle> _addonsBox = new ArrayList<>();
    Group _grp;

    public ShapeToolBarFx(Point2D pos, Point2D size, boolean selected, ArrayList<Shape> addons, Group grp) {
        super(pos, size, selected, new RectangleFx(pos.getX(), pos.getY(), size.getX(), size.getY(), selected, grp), addons);
        _grp = grp;
        _adapted = new Rectangle();
        for(int i = 0; i < addons().size(); i ++){
            _addonsBox.add(new Rectangle());
        }
        _grp.getChildren().addAll(_adapted);
    }

    @Override
    public void draw() {
        for (int i = 0; i < addons().size(); i ++) {
            addons().get(i).draw();
            if(!_grp.getChildren().contains(_addonsBox.get(i))) _grp.getChildren().add(_addonsBox.get(i));
            Point2D p = addons().get(i).visiblePosition();
            Point2D	s = addons().get(i).visibleSize();
            _addonsBox.get(i).setX(p.getX()- ((s.getX()/2) + 3));
            _addonsBox.get(i).setY(p.getY()- ((s.getY()/2) + 3));
            _addonsBox.get(i).setWidth(s.getX() + 6);
            _addonsBox.get(i).setHeight(s.getY() + 6);
            _addonsBox.get(i).setFill(Color.GRAY);
            _addonsBox.get(i).toBack();
        }
        
        if(!_grp.getChildren().contains(_adapted)) _grp.getChildren().add(_adapted);
		Point2D p = visiblePosition();
		Point2D	s = visibleSize();
		_adapted.setX(p.getX()- s.getX()/2);
		_adapted.setY(p.getY()- s.getY()/2);
		_adapted.setWidth(s.getX());
		_adapted.setHeight(s.getY());
		_adapted.setFill(Color.GRAY);
		_adapted.toBack();
    }

    @Override
    public void remove() {
        _grp.getChildren().remove(_adapted);
		System.gc();
    }

    @Override public boolean isInside(Point2D pos){ return pos.getX() > position().getX() - size().getX() / 2 && pos.getX() < position().getX() + size().getX() / 2 && pos.getY() > position().getY() - size().getY() / 2 && pos.getY() < position().getY() + size().getY() / 2; }

    @Override
    public Object adapted() {
        return _adapted;
    }

    @Override public void addAddons(Shape shape){ 
        addons().add(shape); 
        Rectangle rect = new Rectangle();
        _addonsBox.add(rect);
        _grp.getChildren().add(rect);
    } 

    /**
     * @param String match with _addons's shape and remove that shape from _addons
     */
    public void removeAddons(String id){
        for(int i = 0; i < addons().size(); i ++)
            if(addons().get(i).getId().equals(id)){
                addons().remove(addons().get(i)); 
                _grp.getChildren().remove(_addonsBox.get(i));
                _addonsBox.remove(_addonsBox.get(i));
            }
    }
    
}
