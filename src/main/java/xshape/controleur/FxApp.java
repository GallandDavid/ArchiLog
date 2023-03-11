package xshape.controleur;

import xshape.model.ShapeFactory;
import xshape.model.ShapeFactoryFx;
import xshape.model.ToolBar;
import xshape.model.ToolBarFx;
import xshape.model.Button;
import xshape.vue.FxApplication;

public class FxApp extends XShape{

    @Override
    protected ShapeFactory createFactory() {
        return new ShapeFactoryFx(FxApplication._root);
    }

    @Override
    protected ToolBar createToolBar() {
        return new ToolBarFx(400.0, 0.0, 100.0, 500.0, new Button(420.0,240.0,60.0,20.0,"Rectangle"));
    }

    @Override
    public void run() {
        super.toolBar(createToolBar());
        FxApplication.launch(FxApplication.class,super.toolBar().rectButton().title());
        draw();
    }
}