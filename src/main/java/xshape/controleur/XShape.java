package xshape.controleur;


import java.util.ArrayList;

import xshape.Command.CommandHistory;
import xshape.Command.ICommand;
import xshape.model.Shape;
import xshape.model.abstractFactory.ShapeFactory;

public abstract class XShape implements CommandHistory{
    private ShapeFactory _factory = null;
    Shape[] _shapes = null;
    ArrayList<ICommand> _history = new ArrayList<>();

    @Override
    public void push(ICommand command){
        _history.add(command);
    }

    //method factory to delegate instanciation of Shapefactory to subclass
    protected abstract ShapeFactory createFactory();
    //Handler to start the GUI
    public abstract void run();

    private void createScene() {
        addShape((Shape) _factory.createRectangle(15, 15, 50, 50));
    }

    public void draw() {
        if (_shapes == null) {
            _factory = createFactory();
            createScene();
        }
        if(_shapes != null){
            for (Shape s : _shapes){
                s.draw();
            }
        }
    }

    protected ShapeFactory factory(){
        return _factory;
    }

    public void addShape(Shape shape){
        Shape[] tmp;
        if(_shapes == null)
            tmp = new Shape[1];
        else
            tmp = new Shape[_shapes.length + 1];
        for (int i = 0; i < tmp.length - 1; i++) {
            tmp[i] = _shapes[i];
        }
        tmp[tmp.length - 1] = shape;
        _shapes = tmp;
    }
}
