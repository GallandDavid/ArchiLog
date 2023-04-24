package xshape.model.shape.tools.toolbar.editToolBar;

import java.awt.geom.Point2D;

import xshape.model.Interface.IShape;
import xshape.model.shape.Rectangle;
import xshape.model.shape.tools.EditItem;
import xshape.model.shape.tools.Menu;
import xshape.model.shape.tools.toolbar.ToolBar;
import xshape.model.visitor.DrawVisitor;

public class EditToolBar extends ToolBar{
    Rectangle _rect;
    EditItem[] _items;
    Menu _apply;
    Menu _ok;
    Menu _cancel;
    double _menu_height = 25;

    public EditToolBar(Point2D pos, Point2D size, boolean selected, EditItem[] items) {
        super(pos, size, selected);
        _rect = new Rectangle(pos, size, selected);
        _apply = new Menu("Apply", new Point2D.Double(pos.getX() - size.getX() / 3, (pos.getY() + size.getY() / 2) - _menu_height / 2), new Point2D.Double( size.getX() / 3, _menu_height), selected);
        _ok = new Menu("Ok", new Point2D.Double(pos.getX(), (pos.getY() + size.getY() / 2) - _menu_height / 2), new Point2D.Double( size.getX() / 3, _menu_height), selected);
        _cancel = new Menu("Cancel", new Point2D.Double(pos.getX() + size.getX() / 3, (pos.getY() + size.getY() / 2) - _menu_height / 2), new Point2D.Double( size.getX() / 3, _menu_height), selected);
        _items = items;
    }


    public Menu apply(){ return _apply; }
    public Menu ok(){ return _ok; }
    public Menu cancel(){ return _cancel; }

    public EditItem[] items(){ return _items; }

    @Override
    public void accept(DrawVisitor dv) {
        dv.drawEditToolBar(this);
    }

    @Override
    public boolean isInItem(Point2D pos) {
        for (EditItem editItem : _items) 
            if(editItem.isInside(pos)) return true;
        return false;
    }
    
    @Override public String toString(){ return super.toString(); }


    @Override
    public void resize(double w, double h) {
        size(new Point2D.Double(size().getX(), size().getY() + h ));
        position(new Point2D.Double(position().getX() + w,position().getY() + h/2));
        _apply = new Menu("Apply", new Point2D.Double(position().getX() - size().getX() / 3, (position().getY() + size().getY() / 2) - _menu_height / 2), new Point2D.Double( size().getX() / 3, _menu_height), false);
        _ok = new Menu("Ok", new Point2D.Double(position().getX(), (position().getY() + size().getY() / 2) - _menu_height / 2), new Point2D.Double( size().getX() / 3, _menu_height), false);
        _cancel = new Menu("Cancel", new Point2D.Double(position().getX() + size().getX() / 3, (position().getY() + size().getY() / 2) - _menu_height / 2), new Point2D.Double( size().getX() / 3, _menu_height), false);
        for (EditItem editItem : _items) {
            Point2D pos = editItem.position();
            editItem.position(new Point2D.Double(pos.getX() + w,pos.getY()));
            for (int i = 0; i < editItem.nbItems(); i++) {
                editItem.inputsArea()[i].position(new Point2D.Double(editItem.inputsArea()[i].position().getX() + w, editItem.inputsArea()[i].position().getY() ));
                editItem.itemsArea()[i].position(new Point2D.Double(editItem.itemsArea()[i].position().getX() + w, editItem.itemsArea()[i].position().getY() ));

            }
        }
    }

    @Override public boolean isInside(Point2D pos) { return _rect.isInside(pos); }
    @Override public void selected(boolean sel) { _rect.selected(sel); }
    @Override public boolean selected() { return _rect.selected(); }
    @Override public Point2D size() { return _rect.size(); }
    @Override public IShape size(Point2D size) { return _rect.size(size); }
    @Override public Point2D position() { return _rect.position(); }
    @Override public IShape position(Point2D position) { _rect.position(position); return this; }
}
