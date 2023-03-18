package xshape.controleur;

import xshape.model.abstractFactory.*;
import xshape.observer.Iobserver;
import xshape.model.ToolBar;
import xshape.model.ToolBarFx;
import xshape.model.Builder.ToolBarDirector;
import xshape.vue.FxApplication;

public class FxApp extends XShape implements Iobserver, ToolBarDirector {
    ToolBar _toolbar = new ToolBarFx(this);

    @Override
    protected ShapeFactory createFactory() {
        return new ShapeFactoryFx(FxApplication._root);
    }

    @Override
    public void run() {
        FxApplication.launch(FxApplication.class, FxApp.class.getName());
        draw();
    }


    @Override
    public void createToolBar() {
        _toolbar.makeProduct();
    }

    @Override
    public void update(String code) {
        switch(code){
            case "new rect/follow mouse/place at right click":

        }
    }

    @Override
    public Object getToolBar() {
        return _toolbar.getProduct();
    }
}