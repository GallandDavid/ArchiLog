package xshape.model.shape.tools.toolbar;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import xshape.model.Interface.IShape;
import xshape.model.shape.Polygone;
import xshape.model.shape.Rectangle;
import xshape.model.shape.Shape;
import xshape.model.visitor.DrawVisitor;

public class ShapeToolBar extends ToolBar {
    private Rectangle _area;
    private Rectangle _rect;
    private Polygone _poly;
    private ArrayList<Shape> _addons = new ArrayList<>();
    private static double _marge_in_vertical = 7.0;
    private static double _marge_in_horizontal = 7.0;
    private static double _height = 30;
  
  
    public ShapeToolBar(Point2D pos, Point2D size, boolean selected, ArrayList<Shape> addons){
      super(pos, size, selected);
      _area = new Rectangle(pos, size, selected);
      _rect = new Rectangle(new Point2D.Double(pos.getX(), (pos.getY() - (size.getY()/2)) + _marge_in_vertical + (_height/2)), new Point2D.Double(size.getX() - _marge_in_horizontal * 2, _height), false);
      _poly = new Polygone(new Point2D.Double(pos.getX(), (pos.getY() - (size.getY()/2)) + _marge_in_vertical*3 + (_height*3/2)), 6, 2 * Math.sqrt((Math.pow(_height, 2) * Math.pow(Math.sin(Math.PI / 6), 2)) / (4 - 2 * Math.cos(Math.PI * 2 / 6))), false);
      if(addons != null)
        _addons.addAll(addons);
    }
    /**
     * @return Rectangle return the _rect
     */
    public Rectangle rect() { return _rect; }

    public Polygone poly(){ return _poly; }

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
        if(poly().isInside(pos)) return true;
        for(Shape s : addons())
            if(s.isInside(pos))
                return true;
        return false;
    }

    @Override public void accept(DrawVisitor dv) { dv.drawShapeToolBar(this); }
    @Override
    public void resize(double w, double h) {
        Point2D size = new Point2D.Double(size().getX(), size().getY() + h);

        position(new Point2D.Double(position().getX(),position().getY() + (h / 2)));
        size(size);
    }
    @Override public boolean isInside(Point2D pos) { return _area.isInside(pos); }
    @Override public void selected(boolean sel) { _area.selected(sel); }
    @Override public boolean selected() { return _area.selected(); }
    @Override public Point2D size() { return _area.size(); }
    @Override public IShape size(Point2D size) { _area.size(size); return this;}
    @Override public Point2D position() { return _area.position(); }
    @Override public IShape position(Point2D position) { _area.position(position); return this; }
}