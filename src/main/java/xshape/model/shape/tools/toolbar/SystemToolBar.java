package xshape.model.shape.tools.toolbar;

import java.awt.geom.Point2D;

import xshape.model.Interface.IShape;
import xshape.model.shape.Rectangle;
import xshape.model.shape.tools.Menu;
import xshape.model.visitor.DrawVisitor;

public class SystemToolBar extends ToolBar {
  private Rectangle _rect;
  private Menu _files;
  private Menu _save;
  private Menu _load;
  private Menu _edit;
  private Menu _undo;
  private Menu _redo;
  private Menu _trashbin;

  private static double _width = 60;

  private boolean filesSelected = false;
  private boolean editSelected = false;


  public SystemToolBar(Point2D pos, Point2D size, boolean selected){
    super(pos, size, selected);
    _rect = new Rectangle(pos, size, selected);
    _files = new Menu("Files", new Point2D.Double(_width / 2, size.getY() / 2), new Point2D.Double(_width, size.getY()), false);
    _save = new Menu("Save", new Point2D.Double(_width / 2, (size.getY() / 2) + (size.getY())), new Point2D.Double(_width, size.getY()), false);
    _load = new Menu("Load", new Point2D.Double(_width / 2, (size.getY() / 2) + (size.getY() * 2)), new Point2D.Double(_width, size.getY()), false);
    _edit = new Menu("Edit", new Point2D.Double(_width + (_width / 2), size.getY() / 2), new Point2D.Double(_width, size.getY()), false);
    _undo = new Menu("Undo", new Point2D.Double(_width + (_width / 2), (size.getY() / 2) + size.getY()), new Point2D.Double(_width, size.getY()), false);
    _redo = new Menu("Redo", new Point2D.Double(_width + (_width / 2), (size.getY() / 2) + (size.getY() * 2)), new Point2D.Double(_width, size.getY()), false);
    _trashbin = new Menu("Bin", new Point2D.Double(2 * _width + (_width / 2), size.getY() / 2), new Point2D.Double(_width, size.getY()), false);

  }
  /**
   * @return Menu return the _files
   */
  public Menu files() { return _files; }
  /**
   * @return Menu return the _save
   */
  public Menu save() { return _save; }
  /**
   * @return Menu return the _load
   */
  public Menu load() { return _load; }
  /**
   * @return Menu return the _edit
   */
  public Menu edit() { return _edit; }
  /**
   * @return Menu return the _undo
   */
  public Menu undo() { return _undo; }
  /**
   * @return Menu return the _redo
   */
  public Menu redo() { return _redo; }
  /**
   * @return Button return the _trashbin
   */
  public Menu trashbin() { return _trashbin; }

  @Override
  public boolean isInside(Point2D pos){
    if(filesSelected)
      return super.isInside(pos) || save().isInside(pos) || load().isInside(pos);
    if(editSelected)
      return super.isInside(pos) || undo().isInside(pos) || redo().isInside(pos);
    return super.isInside(pos);
  }

  @Override
  public boolean isInItem(Point2D pos) {
    if(files().isInside(pos) || edit().isInside(pos) || trashbin().isInside(pos) ) return true;
    if(filesSelected){
      System.out.println("selected files");
      if(save().isInside(pos) || load().isInside(pos)) return true;
    }
    if(editSelected)
      if(undo().isInside(pos) || redo().isInside(pos)) return true;
    return false;
  }

  public void unSelect(){
    if(filesSelected){
      filesSelected = false;
    }
    if(editSelected){
      editSelected = false;
    }
  }

  public void selectFiles(){
    unSelect();
    filesSelected = true;
  }

  public void selectEdit(){
    unSelect();
    editSelected = true;
  }

  public boolean filesSelected(){ return filesSelected; }
  public boolean editSelected(){ return editSelected; }

  @Override public void accept(DrawVisitor dv) { dv.drawSystemToolBar(this); }
  @Override
  public void resize(double w, double h) {
    Point2D size = new Point2D.Double(size().getX() + w, size().getY());
    position(new Point2D.Double(size.getX()/2, position().getY()));
    size(size);
  }
  @Override public void selected(boolean sel) { _rect.selected(sel); }
  @Override public boolean selected() { return _rect.selected(); }
  @Override public Point2D size() { return _rect.size(); }
  @Override public IShape size(Point2D position) { _rect.size(position); return this; }
  @Override public Point2D position() { return _rect.position(); }
  @Override public IShape position(Point2D position) { _rect.position(position); return this; }
}