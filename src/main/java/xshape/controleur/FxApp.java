package xshape.controleur;

import xshape.model.Builder.menu.popupmenu.PopUpMenuFx;
import xshape.model.Builder.menu.toolbar.ToolBarFx;
import xshape.model.abstractFactory.*;
import xshape.vue.FxApplication;

import java.awt.geom.Point2D;

import javafx.scene.Group;
import javafx.scene.control.MenuBar;

public class FxApp extends XShape  {
    Group _root;
    MenuBar _popup = null;
    FxApplication _fx = null;

    public FxApp(Group root, FxApplication fx){
        _root = root;
        createFactory();
        createPopUpMenu();
    }

    @Override
    protected ShapeFactory createFactory() {
        return new ShapeFactoryFx(_root);
    }

    @Override
    public void run() {
        createToolBar();
        draw();
    }


    @Override
    public void createToolBar() {
        toolBar(new ToolBarFx(this));
        toolBar().makeProduct();
    }

    public @Override void render() { }

    @Override
    public void setPopUpMenu(Point2D pos, int selected, boolean grouped) {
        popUpMenu().init(pos, selected, grouped);
        popUpMenu().makeProduct();
        _popup = (MenuBar) popUpMenu().getProduct();
        _root.getChildren().add(_popup);
    }

    @Override
    public void removePopUpMenu() {
        if(_popup != null)
            _root.getChildren().remove(_popup);
        _popup = null;
    }

	@Override
	public void createPopUpMenu() {
        popUpMenu(new PopUpMenuFx(this, new Point2D.Double(0,0), 0, false));
	}

    @Override
    public boolean isInPopUpMenu(Point2D pos) {
        if(_popup == null) return false;
        System.out.println("Layout : " + _popup.getLayoutX() + ", " + _popup.getLayoutY());
        System.out.println("Size : " + _popup.getWidth() + ", " + _popup.getHeight());
        if(pos.getX() > _popup.getLayoutX() && pos.getX() < _popup.getLayoutX() + _popup.getWidth() && pos.getY() > _popup.getLayoutY() && pos.getY() < _popup.getLayoutY() + _popup.getHeight()) return true;
        return false;
    }

}