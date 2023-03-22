package xshape.controleur;

import xshape.model.abstractFactory.*;

import java.awt.geom.Point2D;

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
    public void update(String code) {
        
    }

    @Override
    public Object getToolBar() {
        return _toolbar.getProduct();
    }


    @Override
    public void update(String code, double X, double Y) {
        switch(code){
            case "rect selected":
                selectedShape((xshape.model.Shape) factory().createRectangle(X,Y,true,this));
                break;
            case "rect selected drag":
            
                _selected_item.position(new Point2D.Double(X,Y));
                break;
            case "rect select place":
                if((Y - (_selected_item.size().getY() / 2)) > _toolbar.getHeight())
                    addShape(factory().createRectangle(X,Y,this));
                _selected_item.remove();
                _selected_item = null;
                System.gc();
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
                break;
            case "obj selected drag":
                addShape(extractShape(ref).position(new Point2D.Double(x,y)));
                break;
            case "obj select place":
                if(!((y - (getShape(ref).size().getY() / 2)) > _toolbar.getHeight()))
                    removeShape(ref);
                    System.gc();
                break;
            default:
                break;
        }
        draw();
    }

    @Override
    public ToolBar toolBar() {
        return _toolbar;
    }
}