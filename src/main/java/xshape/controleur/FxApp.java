package xshape.controleur;

import xshape.model.abstractFactory.*;
import xshape.model.ToolBar;
import xshape.model.ToolBarFx;
import xshape.model.Button;
import xshape.vue.FxApplication;

public class FxApp extends XShape {

    @Override
    protected ShapeFactory createFactory() {
        return new ShapeFactoryFx(FxApplication._root);
    }

    @Override
    public void run() {
        FxApplication.launch(FxApplication.class);
        draw();
    }
}