package xshape.model.visitor;

import xshape.model.shape.Group;
import xshape.model.shape.Rectangle;
import xshape.model.shape.tools.Menu;
import xshape.model.shape.tools.PopUpMenu;
import xshape.model.shape.tools.WhiteBoard;
import xshape.model.shape.tools.toolbar.ShapeToolBar;
import xshape.model.shape.tools.toolbar.SystemToolBar;

public interface DrawVisitor {
    
    void drawGroup(Group shp);
    void drawRectangle(Rectangle rect);
    void drawWhiteBoard(WhiteBoard wtb);
    void drawShapeToolBar(ShapeToolBar stb);
    void drawSystemToolBar(SystemToolBar stb);
    void drawMenu(Menu menu);
    void drawPopUpMenu(PopUpMenu pum);
}
