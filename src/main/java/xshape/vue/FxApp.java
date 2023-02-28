package xshape.vue;

import xshape.controleur.XShape;
import xshape.model.ShapeFactory;
import xshape.model.ShapeFactoryFx;

public class FxApp extends XShape {

    @Override
    protected ShapeFactory createFactory() {
        return new ShapeFactoryFx(FxApplication._root);
    }
    @Override
    public void run() {
        draw();
        FxApplication.launch(FxApplication.class);
    }
}