package xshape.controleur;

import java.util.LinkedList;
import xshape.model.Shape;
import xshape.model.Command.Command;
import xshape.model.Command.CommandHistory;
import xshape.model.Command.ICommand;
import xshape.model.abstractFactory.ShapeFactory;
import xshape.observer.Iobserver;

public abstract class XShape implements CommandHistory, Iobserver{
    Shape _selected_item = null;
    private ShapeFactory _factory = null;
    Shape[] _shapes = null;
    LinkedList<ICommand> _history = new LinkedList<>();
    LinkedList<ICommand> _redos = new LinkedList<>();

    //method factory to delegate instanciation of Shapefactory to subclass
    protected abstract ShapeFactory createFactory();
    //Handler to start the GUI
    public abstract void run();

    private void createScene() {
        addShape((Shape) _factory.createRectangle(55, 55, 50, 50, this));
        addShape((Shape) _factory.createRectangle(115, 115, 30, 60, this));
    }

    public void draw() {
        if(_factory == null)    _factory = createFactory();
        if (_shapes == null)    createScene();
        if(_shapes != null){
            for (Shape s : _shapes){
                s.draw();
            }
        }
        if(_selected_item != null)
            _selected_item.draw();
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

    public void selectedShape(Shape shape){
        _selected_item = shape;
    }

    public Shape selectedShape(){
        return _selected_item;
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

    @Override
    public void undo(){
        if(!asUndo())
            pushRedo(pop());
    }

    public boolean asUndo(){
        return _history.isEmpty();
    }

    @Override
    public void redo(){
        if(!asRedo()){
            ICommand redo = popRedo();
            redo.execute();
            push(redo);
        }
    }

    public boolean asRedo(){
        return _redos.isEmpty();
    }

    @Override
    public void push(ICommand command){
        clearRedo();
        _history.addLast(command);
    }

    @Override
    public void pushRedo(ICommand command){
        _redos.addLast(command);
        command.undo();
    }


    @Override
    public ICommand pop(){
        return _history.removeLast();
    }

    @Override
    public ICommand popRedo(){
        return _redos.removeLast();
    }

    @Override
    public void clearRedo(){
        _redos.clear();
    }

    public void printSelectShape() {
        System.out.println("Start select shape :\n----------");
        if(_selected_item == null)
            System.out.println("null");
        else
            System.out.println(_selected_item.toString());
        System.out.println("End select shape :\n----------");
    }

    public void printArray(Shape[] array){
        for(Shape s : array){
            System.out.println(s);
        }
    }
    
    @Override 
    public void update(Command command){
        if(command.execute())
            push(command);
        draw();
    }
}
