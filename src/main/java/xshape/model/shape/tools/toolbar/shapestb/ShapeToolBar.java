package xshape.model.shape.tools.toolbar.shapestb;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import xshape.model.shape.Rectangle;
import xshape.model.shape.Shape;
import xshape.model.shape.tools.toolbar.ToolBar;
import xshape.model.visitor.DrawVisitor;

public class ShapeToolBar extends ToolBar {
    private Rectangle _rect;
    private ArrayList<Shape> _addons = new ArrayList<>();
    private static double _marge_in_vertical = 7.0;
    private static double _marge_in_horizontal = 7.0;
    private static double _height = 30;
  
  
    public ShapeToolBar(Point2D pos, Point2D size, boolean selected, ArrayList<Shape> addons){
      super(pos, size, selected);
      _rect = new Rectangle(new Point2D.Double(pos.getX(), (pos.getY() - (size.getY()/2)) + _marge_in_vertical + (_height/2)), new Point2D.Double(size.getX() - _marge_in_horizontal * 2, _height), selected);
      if(addons != null)
        _addons.addAll(addons);
    }
    /**
     * @return Rectangle return the _rect
     */
    public Rectangle rect() { return _rect; }

    /**
     * @return Menu return the _files
     */
    public ArrayList<Shape> addons() { return _addons; }

    /**
     * @param Shape add the shape to _addons
     */
    public void addAddons(Shape shape){ _addons.add(shape); }

    /**
     * @param String match with _addons's shape and remove that shape from _addons
     */
    public void removeAddons(String id){
        for(Shape shape : addons())
            if(shape.getId().equals(id))
                _addons.remove(shape); 
    }

    /**
     * @return Shape return the _addons shape that match with id
     * @param String match with _addons's shape
     */
    public Shape addon(String id){
        for(Shape shape : addons())
            if(shape.getId().equals(id))
                return shape;
        return null;
    }

    @Override
    public boolean isInItem(Point2D pos) {
        if(rect().isInside(pos)) return true;
        for(Shape s : addons())
            if(s.isInside(pos))
                return true;
        return false;
    }

    @Override public void accept(DrawVisitor dv) { dv.drawShapeToolBar(this); }
  
  }
