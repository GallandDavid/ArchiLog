package xshape.controleur;

import xshape.model.abstractFactory.*;
import javafx.scene.Group;
import xshape.model.ToolBar;
import xshape.model.ToolBarFx;
import xshape.model.Builder.ToolBarDirector;

public class FxApp extends XShape implements ToolBarDirector {
    public ToolBar _toolbar = new ToolBarFx(this);
    Group _root;

    public FxApp(Group root){
        _root = root;
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
        _toolbar.makeProduct();
    }

    @Override
    public Object getToolBar() {
        return _toolbar.getProduct();
    }

    @Override
    public ToolBar toolBar() {
        return _toolbar;
    }
}