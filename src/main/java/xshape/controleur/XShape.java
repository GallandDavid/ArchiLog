package xshape.controleur;


import xshape.model.Shape;
import xshape.model.abstractFactory.ShapeFactory;

public abstract class XShape {
    private ShapeFactory _factory = null;
    Shape[] _shapes = null;

    //method factory to delegate instanciation of Shapefactory to subclass
    protected abstract ShapeFactory createFactory();
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
}
