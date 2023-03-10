package xshape.controleur;

import xshape.model.ShapeFactory;
import xshape.model.ShapeFactoryFx;
import xshape.model.ToolBar;
import xshape.model.ToolBarFx;
import xshape.vue.FxApplication;

public class FxApp extends XShape{

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