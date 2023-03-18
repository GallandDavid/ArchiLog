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
        Shape rect = (Shape) _factory.createRectangle(15, 15, 50, 50);
        Shape[] tmp = {rect};
        _shapes = tmp;
    }

    public void draw() {
        if (_shapes == null) {
            _factory = createFactory();
            createScene();
        }
        if(_shapes != null){
            System.out.println("ok");
            for (Shape s : _shapes){
                System.out.println("ok");
                s.draw();
            }
        }
    }

    protected ShapeFactory factory(){
        return _factory;
    }
}
