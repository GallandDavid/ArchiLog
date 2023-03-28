package xshape.model.shape;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import xshape.model.observer.Iobserver;

public abstract class Group extends Shape{

    private ArrayList<Shape> _group = new ArrayList<>();

    public Group(Point2D pos, Point2D size, Point2D visible_pos, Point2D visible_size, boolean selected, double prev_mouse_pos_X, double prev_mouse_pos_Y, String ID, boolean placed, int deepth, Iobserver obs){
        super(pos, size, visible_pos, visible_size, selected, prev_mouse_pos_X, prev_mouse_pos_Y, ID, placed, deepth, obs, false);
    }

    public Group(Point2D pos, Point2D size, boolean selected, Iobserver obs, ArrayList<Shape> group) {
        super(pos, size, selected, obs, true);
        for (Shape shape : group)
            _group.add(shape);
        init();
    }

    private void init() {
        double max_x = -100000;
        double max_y = -100000;
        double min_x = 100000;
        double min_y = 100000;
        for (Shape shape : _group) {
            if(shape.position().getX() + shape.size().getX() / 2 > max_x) max_x = shape.position().getX() + shape.size().getX() / 2;
            if(shape.position().getY() + shape.size().getY() / 2 > max_y) max_y = shape.position().getY() + shape.size().getY() / 2;
            if(shape.position().getX() - shape.size().getX() / 2  < min_x) min_x = shape.position().getX() - shape.size().getX() / 2;
            if(shape.position().getX() - shape.size().getY() / 2 < min_y) min_y = shape.position().getX() - shape.size().getY() / 2;
        }
        position(new Point2D.Double(min_x, min_y));
        size(new Point2D.Double(max_x - min_x, max_y - min_y));
        visiblePosition(position());
        visibleSize(size());
    }

    public Group(Point2D pos, Point2D size, boolean selected, Iobserver obs, Shape shape) {
        super(pos, size, selected, obs, true);
        _group.add(shape);
    }

    public void add(Shape shape){
        _group.add(shape);
        resize(shape);
    }

    private void resize(Shape shape) {  }
    @Override public boolean equals(Object obj){ return false; }
    @Override public void duplicate(Shape shape){ }
    @Override public Shape translate(Point2D vec) { return null; }
    @Override public Shape visibleTranslate(Point2D vec) { 
        for (Shape s : group()) s.visibleTranslate(vec);
        visiblePosition(new Point2D.Double(visiblePosition().getX() + vec.getX(),visiblePosition().getY() + vec.getY()));
        return this;
    }

    @Override
	public boolean isInside(Point2D pos){
        return pos.getX() > position().getX() - size().getX() / 2 && pos.getX() < position().getX() + size().getX() / 2 && pos.getY() > position().getY() - size().getY() / 2 && pos.getY() < position().getY() + size().getY() / 2;
    }
    
    @Override
    public String toString(){
        String str = "Group : " + getId() + "\n";
        for (Shape s : _group) {
            str += s.toString() + "\n";
        }
        return str;
    }
    

    /**
     * @return ArrayList<Shape> return the _group
     */
    public ArrayList<Shape> group() { return _group; }

    /**
     * @param _group the _group to set
     */
    public void group(ArrayList<Shape> _group) { this._group = _group;}

}

