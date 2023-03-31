package xshape.model.shape.tools.toolbar.systemtb;

import java.awt.geom.Point2D;

import xshape.model.shape.tools.menus.Menu;
import xshape.model.shape.tools.toolbar.ToolBar;

public abstract class SystemToolBar extends ToolBar {
  private Menu _files;
  private Menu _edit;
  private Menu _trashbin;


  public SystemToolBar(Point2D pos, Point2D size, boolean selected, Menu files, Menu edit, Menu trash){
    super(pos, size, selected);
    _files = files;
    _edit = edit;
    _trashbin = trash;
  }
  /**
   * @return Menu return the _files
   */
  public Menu files() { return _files; }
  /**
   * @return Menu return the _edit
   */
  public Menu edit() { return _edit; }
  /**
   * @return Button return the _trashbin
   */
  public Menu trashbin() { return _trashbin; }

  @Override
  public boolean isInItem(Point2D pos) {
    if(files().isInside(pos) || edit().isInside(pos) || trashbin().isInside(pos)) return true;
    return false;
  }
}
