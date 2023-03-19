package xshape.controleur;

import xshape.model.abstractFactory.*;
import xshape.observer.Iobserver;
import javafx.scene.Group;
import xshape.model.Rectangle;
import xshape.model.RectangleFx;
import xshape.model.ToolBar;
import xshape.model.ToolBarFx;
import xshape.model.Builder.ToolBarDirector;

public class FxApp extends XShape implements Iobserver, ToolBarDirector {
    ToolBar _toolbar = new ToolBarFx(this);
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
    public void update(String code) {
        
    }

    @Override
    public Object getToolBar() {
        return _toolbar.getProduct();
    }

    @Override
    public void pop() {
    }

    @Override
    public void update(String code, double X, double Y) {
        switch(code){
            case "rect selected":
                addShape((xshape.model.Shape) factory().createRectangle(X,Y,true));
                break;
            default:
                break;
        }
        draw();
        _root.requestLayout();
    }

    @Override
    public void update(String code, int X, int Y) {
    }
}