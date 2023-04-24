package xshape.model.shape;

import java.awt.Color;
import java.awt.geom.Point2D;

import xshape.model.Interface.IBoundsRoundable;
import xshape.model.Interface.IResizeable;
import xshape.model.shape.tools.toolbar.editToolBar.EditToolBar;
import xshape.model.visitor.CreateEditToolBarVisitor;
import xshape.model.visitor.DrawVisitor;

public class Rectangle extends Shape implements IBoundsRoundable, IResizeable{

    protected static double _size_x = 100;
    protected static double _size_y = 100;
    private Point2D _size;
    private Point2D _visible_size;
    private int _rounde;


    public Rectangle(Point2D pos, Point2D size, boolean selected, Color color){
        super(pos, selected, false, color);
        _size = size;
        _rounde = 0;
        visibleSize(_size);
    }

    public Rectangle(Point2D pos, Point2D size, boolean selected){
        super(pos, selected, false);
        _size = size;
        visibleSize(_size);
        _rounde = 0;
    }
    public Rectangle(Rectangle rect){
        super(rect.position(), rect.visiblePosition(), rect.selected(), rect.getId(), rect.isPlaced(), rect.deepth(), rect.grouped(), rect.rotation(), rect.color());
        size(rect.size());
        visibleSize(rect.visibleSize());
        rounded(rect.rounded());
    
    }
    public Rectangle(Point2D pos, boolean selected){
        super(pos, selected, false);
        _size = new Point2D.Double(_size_x,_size_y);
        visibleSize(_size);
        _rounde = 0;
    }

    @Override public String toString(){
        String str = super.toString();
        str += "Size : (" + _size.getX() + ", " + _size.getY() + ")   |   ";
        str += "Visble Size : (" + _visible_size.getX() + ", " + _visible_size.getY() + ")\n";
        return str;
    }

    @Override public int rounded(){ return _rounde; }
    @Override public void rounded(int rounde){ _rounde = rounde; }
	@Override public Point2D size() { return (Point2D) _size.clone(); }
    @Override public Shape size(Point2D vec) { _size = (Point2D) vec.clone(); return this; }
	@Override public Point2D visibleSize() { return (Point2D) _visible_size.clone(); }
	@Override public Shape visibleSize(Point2D vec) { _visible_size = (Point2D) vec.clone(); return this; }
    @Override public boolean equals(Object obj){ return super.equals(obj); }
    @Override public boolean isInside(Point2D pos){ return pos.getX() > position().getX() - size().getX() / 2 && pos.getX() < position().getX() + size().getX() / 2 && pos.getY() > position().getY() - size().getY() / 2 && pos.getY() < position().getY() + size().getY() / 2; }

    @Override
    public void accept(DrawVisitor dv) {
        dv.drawRectangle(this);
    }
    @Override
    public EditToolBar accept(CreateEditToolBarVisitor cetbv, Point2D pos, Point2D size) { return cetbv.rectangleEditToolBar(null, pos, size, position().getX(), position().getY(), size().getX(), size().getY(), color().getRed(), color().getGreen(), color().getBlue(), rotation(), rounded()); }

    @Override
    public Point2D[] extremPoints() {
        Point2D[] p = {new Point2D.Double(position().getX() - size().getX()/2, position().getY() - size().getY()/2),new Point2D.Double(position().getX() + size().getX()/2, position().getY() + size().getY()/2)};
        return p;
    }


    
}
