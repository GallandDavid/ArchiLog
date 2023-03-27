package xshape.model.shape;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import xshape.model.observer.Iobserver;

public class Group extends Shape{

    private ArrayList<Shape> _group = new ArrayList<>();

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
            if(shape.position().getX() > max_x) max_x = shape.position().getX();
            if(shape.position().getY() > max_y) max_y = shape.position().getY();
            if(shape.position().getX() < min_x) min_x = shape.position().getX();
            if(shape.position().getX() < min_y) min_y = shape.position().getX();
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

    private void resize(Shape shape) {
        /////
    }

    @Override
    public void draw() {
        for (Shape shape : _group)
            shape.draw();
    }

    @Override
    public void remove() {
        for (Shape shape : _group)
            _group.remove(shape);
    }

    @Override
    public boolean equals(Object obj){
        return false;
    }

    @Override
    public void duplicate(Shape shape){
    }

    @Override
    public Shape translate(Point2D vec) {
        return null;
    }

    @Override
    public Shape visibleTranslate(Point2D vec) {
        return null;
    }

    @Override
    public boolean isInside(Point2D pos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isInside'");
    }
    
    @Override
    public String toString(){
        String str = "Group : " + getId() + "\n";
        for (Shape s : _group) {
            str += s.toString() + "\n";
        }
        return str;
    }
    
}

