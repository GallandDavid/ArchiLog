package xshape.controleur;


import java.util.ArrayList;

import xshape.Command.CommandHistory;
import xshape.Command.ICommand;
import xshape.model.Rectangle;
import xshape.model.Shape;
import xshape.model.abstractFactory.ShapeFactory;

public abstract class XShape implements CommandHistory{
    private ShapeFactory _factory = null;
    ArrayList<Shape> _shapes = new ArrayList<>();
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
