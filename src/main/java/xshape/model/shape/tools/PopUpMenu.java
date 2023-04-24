package xshape.model.shape.tools;

import java.awt.geom.Point2D;

import xshape.model.Interface.IShape;
import xshape.model.shape.Rectangle;
import xshape.model.visitor.DrawVisitor;

public class PopUpMenu implements IShape{
    public static Point2D _size = new Point2D.Double(70,80);
    Rectangle _rect = null;
    private final Menu _edit;
    private final Menu _group;
    private final Menu _ungroup;
    private boolean _grouped;
    private int _selected;

    public PopUpMenu(Point2D pos, int selected, boolean grouped) {
        pos = new Point2D.Double(pos.getX() + _size.getX() / 2, pos.getY() + _size.getY() / 2);
        _rect = new Rectangle(pos, _size, false);
        _edit = new Menu("Edit", new Point2D.Double(pos.getX(), pos.getY() - _size.getY() / 4), new Point2D.Double(_size.getX(), _size.getY() / 2), grouped);
        _group = new Menu("Group", new Point2D.Double(pos.getX(), pos.getY() + _size.getY() / 4), new Point2D.Double(_size.getX(), _size.getY() / 2), grouped);
        _ungroup = new Menu("Un-Group", new Point2D.Double(pos.getX(), pos.getY() + _size.getY() / 4), new Point2D.Double(_size.getX(), _size.getY() / 2), grouped);
        _selected = selected;
        _grouped = grouped;
    }

    public Menu edit(){ return _edit; }
    public Menu group(){ return _group; }
    public Menu ungroup(){ return _ungroup; }
    

    /**
     * @return boolean return the _grouped
     */
    public boolean grouped() { return _grouped; }

    /**
     * @return int return the _selected
     */
    public int nbSelected() { return _selected; }

    @Override
    public void selected(boolean sel) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selected'");
    }

    @Override
    public boolean selected() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selected'");
    }

    @Override
    public void accept(DrawVisitor dv) {
        dv.drawPopUpMenu(this);
    }

    @Override
    public Point2D size() { return _rect.size();
    }

    @Override
    public Point2D position() {
        return _rect.position();
    }

    @Override
    public IShape position(Point2D pos) {
        _rect.position(pos);
        return this;
    }

    @Override
    public boolean isInside(Point2D pos) {return _rect.isInside(pos); }

}
