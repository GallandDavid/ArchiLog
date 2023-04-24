package xshape.model.shape.tools;

import java.awt.geom.Point2D;

import xshape.model.Interface.IResizeable;
import xshape.model.Interface.IShape;
import xshape.model.shape.Rectangle;
import xshape.model.visitor.DrawVisitor;

public class EditItem implements IShape, IResizeable{
    private Rectangle _area;
    private String[] _title;
    private Rectangle[] _items_area;
    private String[] _input = null;
    private Rectangle[] _input_area;
    private int _nb_items;

    public EditItem(Point2D pos, Point2D size, String[] title, double[] input){
        _nb_items = title.length;
        _title = title;
        _area = new Rectangle(pos, size, false);
        Rectangle[] items_area = new Rectangle[_title.length];
        Rectangle[] input_area = new Rectangle[_title.length];
        String[] input_tmp = new String[_title.length];
        for(int i = 0; i < _title.length; i ++){
            Point2D size_tmp = new Point2D.Double(size.getX(), size.getY() / _title.length);
            Point2D pos_tmp = new Point2D.Double(pos.getX(), (pos.getY() - size.getY() / 2) + size_tmp.getY()/2 + size_tmp.getY() * i);
            items_area[i] = new Rectangle(pos_tmp, size_tmp, false);
            
            Point2D size_tmp_input = new Point2D.Double(size_tmp.getX() / 4, size_tmp.getY() * 5 / 6);
            input_area[i] = new Rectangle(new Point2D.Double(((pos_tmp.getX() + size_tmp.getX()/2) - size_tmp.getY() / 12) - (size_tmp_input.getX() / 2), pos_tmp.getY()), size_tmp_input, false);
            input_tmp[i] = String.valueOf(input[i]);
        }
        _items_area = items_area;
        _input_area = input_area;
        _input = input_tmp;
    }

    public int nbItems(){ return _nb_items; }

    public Rectangle[] itemsArea(){ return _items_area; }
    public Rectangle[] inputsArea(){ return _input_area; }
    public String[] titles(){ return _title; }
    public String[] inputs(){ return _input; }

    public int insideIndex(Point2D pos){
        for (int i = 0; i < _items_area.length; i++) if(_input_area[i].isInside(pos)) return i;
        return -1;
    }

    public String title(int index){ return _title[index]; }
    public boolean isInside(Point2D pos, int index){ return _input_area[index].isInside(pos);}
    public void selected(boolean sel, int index) { _input_area[index].selected(sel); }
    public boolean selected(int index) { return _input_area[index].selected(); }
    public Point2D size(int index) { return _area.size(); }
    public IShape size(Point2D vec, int index) { _area.size(vec); return _area;}
    public Point2D position(int index) { return _area.position(); }

    @Override public boolean isInside(Point2D pos) { return _area.isInside(pos); }
    @Override public void selected(boolean sel) { }
    @Override public boolean selected() { return _area.selected(); }
    @Override public Point2D size() { return _area.size(); }
    @Override public IShape size(Point2D vec) { _area.size(vec); return _area;}
    @Override public Point2D position() { return _area.position(); }

    @Override
    public void accept(DrawVisitor dv) {
        dv.drawEditItem(this);
    }

    @Override
    public IShape position(Point2D position) { _area.position(position); return this;}
}
