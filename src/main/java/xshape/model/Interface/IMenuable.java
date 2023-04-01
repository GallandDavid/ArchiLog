package xshape.model.Interface;

import xshape.model.shape.tools.popup.PopUpMenu;
import xshape.model.shape.tools.toolbar.shapestb.ShapeToolBar;
import xshape.model.shape.tools.toolbar.systemtb.SystemToolBar;

public interface IMenuable {
    void systemToolBar(SystemToolBar toolBar);
    SystemToolBar systemToolBar();
    void shapesToolBar(ShapeToolBar toolBar);
    ShapeToolBar shapesToolBar();
    void popUpMenu(PopUpMenu popUpMenu);
    PopUpMenu popUpMenu();
    boolean isPopUping();
}
