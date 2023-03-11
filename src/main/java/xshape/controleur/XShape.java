package xshape.controleur;

import java.awt.geom.Point2D;

import xshape.model.Shape;
import xshape.model.ShapeFactory;
import xshape.model.ToolBar;

public abstract class XShape {
    private ShapeFactory _factory = null;
    Shape[] _shapes = null;
    private ToolBar _toolBar = null;

    //method factory to delegate instanciation of Shapefactory to subclass
    protected abstract ShapeFactory createFactory();
    //method factory to delegate instanciation of Shapefactory to subclass
    protected abstract ToolBar createToolBar();
    //Handler to start the GUI
    public abstract void run();

    private void createScene() {
        Shape[] tmp = {};
        _shapes = tmp;
    }

    public void draw() {
        if (_shapes == null) {
            _factory = createFactory();
            createScene();
        }
        if(_shapes != null)
            for (Shape s : _shapes)
                s.draw();
    }

    protected ShapeFactory factory(){
        return _factory;
    }

    protected ToolBar toolBar(){
        return _toolBar;
    }
    
    protected void toolBar(ToolBar tb){
        this._toolBar = tb;
    }
}
