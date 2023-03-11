package xshape.controleur;

import java.awt.geom.Point2D;

import xshape.model.Shape;
import xshape.model.ShapeFactory;
import xshape.model.ToolBar;

public abstract class XShape {
    private ShapeFactory _factory = null;
    Shape[] _shapes = null;
    private ToolBar _toolbar = null;

    //method factory to delegate instanciation of Shapefactory to subclass
    protected abstract ShapeFactory createFactory();
    //Handler to start the GUI
    public abstract void run();

    private void createScene() {
        _shapes = null;
        _shapes[0] = _factory.createToolBar();
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

}
