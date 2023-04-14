package xshape.model.Interface;

import xshape.model.shape.tools.PopUpMenu;
import xshape.model.shape.tools.toolbar.ShapeToolBar;
import xshape.model.shape.tools.toolbar.SystemToolBar;

public interface IMenuable {
    void systemToolBar(SystemToolBar toolBar);
    SystemToolBar systemToolBar();
    void shapesToolBar(ShapeToolBar toolBar);
    ShapeToolBar shapesToolBar();
    void popUpMenu(PopUpMenu popUpMenu);
    PopUpMenu popUpMenu();
    boolean isPopUping();
}
