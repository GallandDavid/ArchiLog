package xshape.controleur;


import java.util.LinkedList;

import xshape.model.Command.Command;
import xshape.model.Command.CommandHistory;
import xshape.model.Command.ICommand;
import xshape.model.Command.RectPlaceCommand;
import xshape.model.abstractFactory.ShapeFactory;
import xshape.model.shape.Shape;
import xshape.observer.Iobserver;

public abstract class XShape implements CommandHistory, Iobserver{
    public Shape _selected_item = null;
    private ShapeFactory _factory = null;
    Shape[] _shapes = null;
    LinkedList<ICommand> _history = new LinkedList<>();
    LinkedList<ICommand> _redos = new LinkedList<>();

    //method factory to delegate instanciation of Shapefactory to subclass
    protected abstract ShapeFactory createFactory();
    //Handler to start the GUI
    public abstract void run();

    private void createScene() {
        Command command1 = new RectPlaceCommand(this, 20,300, true);
        Command command2 = new RectPlaceCommand(this, 100, 200, true);
        Command[] tmp = {command1,command2};
        for(Command cmd : tmp)
            cmd.execute();
    }

    public void draw() {
        if(_factory == null)    _factory = createFactory();
        if (_shapes == null)    createScene();

        if(_shapes != null)
            for (Shape s : _shapes)
                s.draw();

        if(_selected_item != null)
            _selected_item.draw();

    }

    public ShapeFactory factory(){ return _factory; }

    public void addShape(Shape shape){
        Shape[] tmp;
        if(_shapes == null)
            tmp = new Shape[1];
        else
            tmp = new Shape[_shapes.length + 1];
        for (int i = 0; i < tmp.length - 1; i++) 
            tmp[i] = _shapes[i];
        tmp[tmp.length - 1] = shape;
        _shapes = tmp;
    }

    public void addSelectedShape(Shape shape){ this._selected_item = shape; }

    public Shape getShape(String ref){ for (Shape s : _shapes) if(s.getId().equals(ref)) return s; return null; }

    public void removeShape(String ref){
        if(_shapes.length != 0){
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
            System.gc();
        }
    }

    public void printShapes(){ if(_shapes != null) for(Shape s : _shapes) System.out.println(s.toString()); }

    @Override
    public void undo(){ if(!asUndo()) pushRedo(pop()); }

    public boolean asUndo(){ return _history.isEmpty(); }

    @Override
    public void redo(){ if(!asRedo()) push(popRedo()); }

    public boolean asRedo(){ return _redos.isEmpty(); }

    @Override
    public void push(ICommand command){ _history.addLast(command); }

    @Override
    public void pushRedo(ICommand command){ _redos.addLast(command); }


    @Override
    public ICommand pop(){
        ICommand cmd = _history.removeLast();
        cmd.undo();
        return cmd;
    }

    @Override
    public ICommand popRedo(){
        ICommand cmd = _redos.removeLast();
        cmd.backup();
        return cmd;
    }

    @Override
    public void clearRedo(){ _redos.clear(); }

    @Override 
    public void update(Command command){
        
        if(command.execute()){
            printCommandHistory();
            printRedosHistory();
            push(command);
            clearRedo();
            printCommandHistory();
            printRedosHistory();
        }
        draw();
        
    }

    private void printRedosHistory() {
        if(!_redos.isEmpty()){
            System.out.println("--------------------\nprintRedosHistory :");
            for (ICommand cmd : _redos) System.out.println(cmd.print());
            System.out.println("printRedosHistory\n --------------------");
        }
    }

    private void printCommandHistory() {
        if(!_history.isEmpty()){
            System.out.println("--------------------\nprintCommandHistory :");
            for (ICommand cmd : _history) System.out.println(cmd.print());
            System.out.println("printCommandHistory\n --------------------");
        }
    }

    public void printSelectShape() {
        System.out.println("Start select shape :\n----------");
        if(_selected_item == null)
            System.out.println("null");
        else
            System.out.println(_selected_item.toString());
        System.out.println("End select shape :\n----------");
    }

    public void printArray(Shape[] array){ for(Shape s : array) System.out.println(s); }

}

