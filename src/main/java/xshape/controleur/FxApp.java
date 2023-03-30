package xshape.controleur;

import xshape.model.abstractFactory.*;
import xshape.vue.FxApplication;

import javafx.scene.Group;
import javafx.scene.control.MenuBar;

public class FxApp extends XShape  {
    Group _root;
    MenuBar _popup = null;
    FxApplication _fx = null;

    public FxApp(Group root, FxApplication fx){
        _root = root;
        createFactory();
    }

    @Override
    protected ShapeFactory createFactory() {
        return new ShapeFactoryFx(_root);
    }

    @Override
    public void run() {
        draw();
    }

    public @Override void render() { }
}