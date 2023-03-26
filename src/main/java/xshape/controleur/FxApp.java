package xshape.controleur;

import xshape.model.abstractFactory.*;
import xshape.model.toolbar.ToolBarFx;
import javafx.scene.Group;

public class FxApp extends XShape{
    Group _root;

    public FxApp(Group root){
        _root = root;
        createFactory();
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

    @Override
    void render() {
    }
}