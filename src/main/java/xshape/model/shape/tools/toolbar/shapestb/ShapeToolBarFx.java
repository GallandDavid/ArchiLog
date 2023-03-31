package xshape.model.shape.tools.toolbar.shapestb;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import xshape.model.shape.RectangleFx;
import xshape.model.shape.Shape;

public class ShapeToolBarFx extends ShapeToolBar{
    private static double _marge_in_vertical = 7.0;
    private static double _marge_in_horizontal = 7.0;
    private static double _height = 30;
    private javafx.scene.shape.Rectangle _adapted;
    private ArrayList<Rectangle> _addonsBox = new ArrayList<>();
    Group _grp;

    public ShapeToolBarFx(Point2D pos, Point2D size, boolean selected, ArrayList<Shape> addons, Group grp) {
        super(pos, size, selected, 
                new RectangleFx(pos.getX(), (pos.getY() - (size.getY()/2)) + _marge_in_vertical + (_height/2), _height, size.getX() - _marge_in_horizontal * 2, selected, grp),
                addons);
        _grp = grp;
        _adapted = new Rectangle();
        for(int i = 0; i < addons().size(); i ++){
            _addonsBox.add(new Rectangle());
        }
        _grp.getChildren().addAll(_adapted);
    }

    @Override
    public void draw() {
        
        if(!_grp.getChildren().contains(_adapted)) _grp.getChildren().add(_adapted);
		Point2D pos = visiblePosition();
		Point2D	size = visibleSize();
		_adapted.setX(pos.getX()- size.getX()/2);
		_adapted.setY(pos.getY()- size.getY()/2);
		_adapted.setWidth(size.getX());
		_adapted.setHeight(size.getY());
		_adapted.setFill(Color.LIGHTGRAY);
        _adapted.toFront();
        
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
            _addonsBox.get(i).toFront();
        }
        rect().draw();
    }

    @Override
    public void remove() {
        _grp.getChildren().remove(_adapted);
        rect().remove();
		System.gc();
    }

    @Override public Object adapted() { return _adapted; }

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
