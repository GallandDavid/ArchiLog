package xshape.model.shape.tools.toolbar.systemtb;

import java.awt.geom.Point2D;

import xshape.model.shape.tools.menus.Menu;
import xshape.model.shape.tools.toolbar.ToolBar;

public abstract class SystemToolBar extends ToolBar {
  private Menu _files;
  private Menu _save;
  private Menu _load;
  private Menu _edit;
  private Menu _undo;
  private Menu _redo;
  private Menu _trashbin;

  private boolean filesSelected = false;
  private boolean editSelected = false;


  public SystemToolBar(Point2D pos, Point2D size, boolean selected, Menu files, Menu save, Menu load, Menu edit, Menu undo, Menu redo, Menu trash){
    super(pos, size, selected);
    _files = files;
    _save = save;
    _load = load;
    _edit = edit;
    _undo = undo;
    _redo = redo;
    _trashbin = trash;
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
      save().remove();
      load().remove();
    }
    if(editSelected){
      editSelected = false;
      undo().remove();
      redo().remove();
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
}
