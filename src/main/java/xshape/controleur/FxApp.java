package xshape.controleur;

import xshape.model.abstractFactory.*;

import java.awt.geom.Point2D;

import javafx.scene.Group;
import xshape.model.ToolBar;
import xshape.model.ToolBarFx;
import xshape.model.Builder.ToolBarDirector;

public class FxApp extends XShape implements ToolBarDirector {
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
                addSelectedShape((xshape.model.Shape) factory().createRectangle(X,Y,true,this));
                break;
            case "rect selected drag":
                selected_item.position(new Point2D.Double(X,Y));
                break;
            case "rect selected place":
                selected_item = null;
                addShape(factory().createRectangle(X,Y,this));
                break;
            default:
                break;
        }
        draw();
    }

    @Override
    public void update(String code, int X, int Y) {
    }

    @Override
    public void update(String code, double x, double y, String ref) {
        switch(code){
            case "obj selected":
                //add selection draw
                break;
            case "obj selected drag":
                selectShape(ref).position(new Point2D.Double(x,y));
                break;
            case "obj selected place":
                //add place info
                break;
            default:
                break;
        }
        draw();
    }
}