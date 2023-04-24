package xshape.model.visitor;
import xshape.model.shape.Group;
import xshape.model.shape.Polygone;
import xshape.model.shape.Rectangle;
import xshape.model.shape.tools.EditItem;
import xshape.model.shape.tools.Menu;
import xshape.model.shape.tools.PopUpMenu;
import xshape.model.shape.tools.WhiteBoard;
import xshape.model.shape.tools.toolbar.ShapeToolBar;
import xshape.model.shape.tools.toolbar.SystemToolBar;
import xshape.model.shape.tools.toolbar.editToolBar.EditToolBar;

public interface DrawVisitor {
    
    void drawGroup(Group shp);
    void drawRectangle(Rectangle rect);
    void drawWhiteBoard(WhiteBoard wtb);
    void drawShapeToolBar(ShapeToolBar stb);
    void drawSystemToolBar(SystemToolBar stb);
    void drawMenu(Menu menu);
    void drawPopUpMenu(PopUpMenu pum);
    void drawEditItem(EditItem editItem);
    void drawEditToolBar(EditToolBar editToolBar);
    void drawPolygone(Polygone polygone);
}
