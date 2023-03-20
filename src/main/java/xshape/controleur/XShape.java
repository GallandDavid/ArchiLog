package xshape.controleur;


import java.util.ArrayList;

import xshape.Command.CommandHistory;
import xshape.Command.ICommand;
import xshape.model.Shape;
import xshape.model.abstractFactory.ShapeFactory;
import xshape.observer.Iobserver;

public abstract class XShape implements CommandHistory, Iobserver{
    Shape selected_item = null;
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
        addShape((Shape) _factory.createRectangle(15, 15, 50, 50, this));
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
        if(selected_item != null)
            selected_item.draw();
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

    public void addSelectedShape(Shape shape){
        selected_item = shape;
    }

    public Shape getShape(String ref){
        for (Shape s : _shapes) {
            if(s.getId().equals(ref)){
                return s;
            }
        }
        return null;
    }

    public Shape extractShape(String ref){
        Shape[] shapes = new Shape[_shapes.length - 1];
        Shape shape = null;
        for (Shape s : _shapes) {
            if(s.getId().equals(ref)){
                shape = s;
                break;
            }
        }
        if(shape != null){
            int j = 0;
            for (int i = 0; i < _shapes.length; i++)
                if(!(_shapes[i].getId().equals(ref))){
                    shapes[j] = _shapes[i];
                    j ++;
                }
            _shapes = shapes;
        }
        return shape;
    }

    public void removeShape(String ref){
        Shape[] shapes = new Shape[_shapes.length - 1];
        int j = 0;
        for (int i = 0; i < _shapes.length; i++)
            if(!(_shapes[i].getId().equals(ref))){
                shapes[j] = _shapes[i];
                j ++;
            }
            else
                _shapes[i].remove();
        _shapes = shapes;
    }

    public void printShapes(){
        if(_shapes != null)
            for(Shape s : _shapes)
                System.out.println(s.toString());
    }

    
}
