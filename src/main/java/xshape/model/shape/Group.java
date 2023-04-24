package xshape.model.shape;

import java.awt.geom.Point2D;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import xshape.model.Interface.IShape;
import xshape.model.shape.tools.toolbar.editToolBar.EditToolBar;
import xshape.model.visitor.CreateEditToolBarVisitor;
import xshape.model.visitor.DrawVisitor;


public class Group extends Shape{
    Rectangle _rect;

    private ArrayList<Shape> _group = new ArrayList<>();

    public Group(){
        super(null,false,true);
    }

    public Group(Point2D pos, Point2D size, boolean selected, ArrayList<Shape> group) {
        super(pos, selected, true);
        _rect = new Rectangle(pos,size, selected);
        for (Shape shape : group)
            _group.add(shape);
        init();
    }
    public Group(ArrayList<Shape> group) {
        super(null, false, true);
        for (Shape shape : group)
            _group.add(shape);
        init();
    }

    public Group(Point2D pos, Point2D size, boolean selected, Shape shape) {
        super(pos, selected, true);
        _rect = new Rectangle(pos, size, selected);
        _group.add(shape);
    }

    public Group(Group grp) {
        super(grp.position(),grp.visiblePosition(),grp.selected(),grp.getId(),grp.isPlaced(),grp.deepth(),true, grp.rotation(), grp.color());
        _rect.position(grp.position());
        _rect.visiblePosition(grp.position());
        _rect.size(grp.size());
        _rect.visibleSize(grp.visibleSize());
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

        Point2D size = new Point2D.Double(max_x - min_x + 4, max_y - min_y + 4);
        _rect = new Rectangle(new Point2D.Double(min_x + size.getX()/2 - 2, min_y + size.getY()/2 - 2), size, false);
        visiblePosition(position());
        visibleSize(size());
    }

    @Override
    public void selected(boolean sel){
        for(Shape s : group()){
            s.selected(sel);
        }
        _rect.selected(true);
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
        _rect.translate(vec);
        return this;
    }
    @Override public Shape visibleTranslate(Point2D vec) { 
        for (Shape s : group()) s.visibleTranslate(vec);
        _rect.visibleTranslate(vec);
        return this;
    }

    @Override public boolean isInside(Point2D pos) { return _rect.isInside(pos); }
    @Override public boolean selected() { return _rect.selected(); }
    @Override public Point2D size() { return _rect.size(); }
    @Override public IShape size(Point2D vec) { _rect.size(vec); return _rect;}
    @Override public Point2D position() { return _rect.position(); }

    @Override
    public String toString(){
        String str = "Group : " + getId() + "\n";
        for (Shape s : _group) {
            str += s.toString() + "\n";
        }
        return str;
    }
    
    public EditToolBar accept(CreateEditToolBarVisitor cetbv, Point2D pos, Point2D size) { return cetbv.groupEditToolBar(null, pos, size, position().getX(), position().getY(), size().getX(), size().getY(), color().getRed(), color().getGreen(), color().getBlue(), rotation()); }

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

    @Override public Point2D visibleSize() { return _rect.visibleSize(); }

    @Override
    Shape visibleSize(Point2D vec) { _rect.visibleSize(vec); return this;}

    @Override
    public Point2D[] extremPoints() { return _rect.extremPoints(); }

}

