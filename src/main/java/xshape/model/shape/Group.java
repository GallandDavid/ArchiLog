package xshape.model.shape;

import java.awt.geom.Point2D;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import xshape.model.visitor.DrawVisitor;


public class Group extends Shape{

    private ArrayList<Shape> _group = new ArrayList<>();

    public Group(){
        super(null,null,false,true);
    }

    public Group(Point2D pos, Point2D size, boolean selected, ArrayList<Shape> group) {
        super(pos, size, selected, true);
        for (Shape shape : group)
            _group.add(shape);
        init();
    }
    public Group(ArrayList<Shape> group) {
        super(null, null, false, true);
        for (Shape shape : group)
            _group.add(shape);
        init();
    }

    public Group(Point2D pos, Point2D size, boolean selected, Shape shape) {
        super(pos, size, selected, true);
        _group.add(shape);
    }

    public Group(Group grp) {
        super(grp.position(),grp.size(),grp.visiblePosition(),grp.visibleSize(),grp.selected(),grp.getId(),grp.isPlaced(),grp.deepth(),true, grp.rotation());
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
            if(shape.position().getY() - shape.size().getY() / 2 < min_y) min_y = shape.position().getY() - shape.size().getY() / 2;
        }
        size(new Point2D.Double(max_x - min_x + 4, max_y - min_y + 4));

        position(new Point2D.Double(min_x + size().getX()/2 - 2, min_y + size().getY()/2 - 2));
        visiblePosition(position());
        visibleSize(size());
    }

    @Override
    public void selected(boolean sel){
        for(Shape s : group()){
            s.selected(sel);
        }
        _selected = true;
    }

    public void add(Shape shape){
        _group.add(shape);
        init();
    }

    public void add(ArrayList<Shape> shapes){
        for (Shape shape : shapes) _group.add(shape);
        init();
    }

    @Override public boolean equals(Object obj){ return false; }
    @Override public void duplicate(Shape shape){
        super.duplicate(shape);
        _group = new ArrayList<>();
        for (Shape s : ((Group) shape).group()) {
            Class<?> classe = null;
            Shape shp = null;
            try {
                Constructor<?> constructeur;
                classe = Class.forName (s.getClass().getName());
                if(s instanceof Group){
                    constructeur = classe.getConstructor ();
                    shp = (Shape) constructeur.newInstance();
                    ((Group) shape).add(((Group) s).group());
                }
                else{
                    constructeur = classe.getConstructor (s.getClass());
                    shp = (Shape) constructeur.newInstance (new Object [] {s});
                }
            }
            catch (ClassNotFoundException e) {  e.printStackTrace();    }
            catch (InstantiationException e) {  e.printStackTrace();    } 
            catch (IllegalAccessException e) {  e.printStackTrace();    } 
            catch (InvocationTargetException e) { e.printStackTrace();    } 
            catch (NoSuchMethodException e) { e.printStackTrace();    }
            add(shp);
        }
    }
    @Override public Shape translate(Point2D vec) { 
        for (Shape s : group()) s.translate(vec);
        super.translate(vec);
        return this;
    }
    @Override public Shape visibleTranslate(Point2D vec) { 
        for (Shape s : group()) s.visibleTranslate(vec);
        super.visibleTranslate(vec);
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

    @Override
    public void accept(DrawVisitor dv) {
        dv.drawGroup(this);
    }

}

