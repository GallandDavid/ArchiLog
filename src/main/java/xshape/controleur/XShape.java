package xshape.controleur;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.SortedMap;
import java.util.TreeMap;

import xshape.model.Command.Command;
import xshape.model.Command.CommandHistory;
import xshape.model.Command.ICommand;
import xshape.model.Command.RectPlaceCommand;
import xshape.model.Command.ShapeTranslateCommand;
import xshape.model.Command.TrashBinCommand;
import xshape.model.abstractFactory.ShapeFactory;
import xshape.model.controlInput.InputControl;
import xshape.model.observer.IInputObserver;
import xshape.model.shape.Shape;
import xshape.model.shape.tools.popup.PopUpMenu;
import xshape.model.shape.tools.toolbar.shapestb.ShapeToolBar;
import xshape.model.shape.tools.toolbar.systemtb.SystemToolBar;

public abstract class XShape implements CommandHistory, IInputObserver{
    private SystemToolBar _systemToolBar = null;
    private ShapeToolBar _shapesToolBar = null;
    private PopUpMenu _popUpMenu = null;
    public boolean _selection = false;
    public Shape _selected_item = null;
    private ShapeFactory _factory = null;
    Shape[] _shapes = null;
    LinkedList<ICommand> _history = new LinkedList<>();
    LinkedList<ICommand> _redos = new LinkedList<>();

    //method factory to delegate instanciation of Shapefactory to subclass
    protected abstract ShapeFactory createFactory();
    //Handler to start the GUI
    public abstract void run();
    public abstract void render();

    private void createScene() {
        if(_popUpMenu != null) _popUpMenu.draw();
        Command command1 = new RectPlaceCommand(this, 20,300, true);
        Command command2 = new RectPlaceCommand(this, 100, 200, true);
        Command[] tmp = {command1,command2};
        for(Command cmd : tmp)
            cmd.execute();
    }

    public SortedMap<Integer, Shape> orderShapes() {
        SortedMap<Integer, Shape> map = new TreeMap<Integer, Shape>(
                                            new Comparator<Integer>() {
                                                public int compare(Integer a, Integer b){
                                                    return a.compareTo(b);
                                                    }
                                            });
        for (int i = 0; i < _shapes.length; i ++)
            map.put(_shapes[i].deepth(), _shapes[i]);
        return map;
    }

    public void systemToolBar(SystemToolBar toolBar){ _systemToolBar = toolBar; }
    public SystemToolBar systemToolBar() { return _systemToolBar; }
    public void shapesToolBar(ShapeToolBar toolBar){ _shapesToolBar = toolBar; }
    public ShapeToolBar shapesToolBar() { return _shapesToolBar; }
    public void popUpMenu(PopUpMenu popUpMenu){ _popUpMenu = popUpMenu; }
    public PopUpMenu popUpMenu(){ return _popUpMenu; }


    public void draw(){
        if(_factory == null)    _factory = createFactory();
        if (_shapes == null)    createScene();
        if(_shapes != null)
            for(Shape s : orderShapes().values()){
                s.draw();
            }
        _shapesToolBar.draw();
        if(_selected_item != null)
            _selected_item.draw();
        _systemToolBar.draw();
        render();
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
    public void update(InputControl inputControleur) {
        Command cmd = null;
        if(inputControleur.leftPressed() && inputControleur.mouseMoved()){
            for (Shape s : getShapes()) {
                if(s.selected()){
                    s.visibleTranslate(s.getMouseVec(inputControleur.position().getX(), inputControleur.position().getY()));
                    s.setPrevMouse(inputControleur.position().getX(), inputControleur.position().getY());
                }
            }
        }
    //
        else if(inputControleur.left().releaseEvent()){
            if(_popUpMenu == null || !_popUpMenu.isInside(inputControleur.position())){
                //removePopUpMenu();
                if(inputControleur.position().getY() > systemToolBar().position().getY() + systemToolBar().size().getY() / 2){
                    ArrayList<Object> shapes = new ArrayList<>();
                    for (Shape shape : getShapes()){
                        if(shape.selected()){
                            shapes.add(shape);
                        }
                    }
                    if(!shapes.isEmpty()){
                        cmd = new ShapeTranslateCommand(this, shapes, inputControleur.position().getX(),inputControleur.position().getY());
                    }
                }
                else{
                    if(_systemToolBar.isInItem(inputControleur.position())){
                        if(_systemToolBar.files().isInside(inputControleur.position())){
                            _systemToolBar.selectFiles();
                        }
                        if(_systemToolBar.save().isInside(inputControleur.position())){
                        }
                        if(_systemToolBar.load().isInside(inputControleur.position())){
                        }
                        if(_systemToolBar.edit().isInside(inputControleur.position())){
                            _systemToolBar.selectEdit();
                        }
                        if(_systemToolBar.undo().isInside(inputControleur.position())){
                            System.out.print("undo");
                            undo();
                        }
                        if(_systemToolBar.redo().isInside(inputControleur.position())){
                            redo();
                        }
                        if(_systemToolBar.trashbin().isInside(inputControleur.position())){
                            ArrayList<Object> shapes = new ArrayList<>();
                            for (Shape shape : getShapes()){
                                if(shape.selected()){
                                    shapes.add(shape);
                                }
                            }
                            cmd = new TrashBinCommand(this,shapes);
                        }else{
                            for (Shape s : getShapes())
                                if(s.selected()){
                                    s.visiblePosition(s.position());
                                }
                        }
                    }
                }
            }
        }else if (inputControleur.rightReleased()){
            int selected = 0;
            boolean grouped = true;
            for (Shape shape : orderShapes().values())
                if(shape.selected()){
                    if(!shape.grouped())
                        grouped = false;
                    selected ++;
                }
            //setPopUpMenu(inputControleur.position(), selected, grouped);
        }else if(inputControleur.leftPressed()){
            if(_popUpMenu == null || !_popUpMenu.isInside(inputControleur.position())){
                if(_systemToolBar.isInside(inputControleur.position())){
                    if(_systemToolBar.isInItem(inputControleur.position())){
                        if(_systemToolBar.files().isInside(inputControleur.position())){
                            //
                        }else if(_systemToolBar.edit().isInside(inputControleur.position())){
                            //
                        }else if(_systemToolBar.trashbin().isInside(inputControleur.position())){
                            //
                        }
                    }

                }else if(_shapesToolBar.isInside(inputControleur.position())){
                    if(_shapesToolBar.isInItem(inputControleur.position())){
                        //
                    }
                }else{
                    Shape shape = topShape(inputControleur.position());
                    if(shape == null){
                        if(!inputControleur.ctrl().pressEvent()){
                            for (Shape s : getShapes()) {
                                if(s.selected()){
                                    s.selected(false);
                                }
                            }
                            selection(false);
                        }
                    }else{
                        if(shape.selected()){
                            if(inputControleur.ctrl().pressEvent()){
                                shape.selected(false);
                                if(!asSelected()){
                                    selection(false);
                                }
                            }else{
                                for (Shape s : getShapes()) {
                                    if(s.selected()){
                                        shape.setSelected(inputControleur.position());
                                    }
                                }
                                shape.setSelected(inputControleur.position());
                            }
                        }else{
                            if(inputControleur.ctrl().pressEvent() && selection()){
                                for(Shape s : getShapes()){
                                    if(s.selected()){
                                        s.setSelected(inputControleur.position());
                                    }
                                }
                            }else{
                                for(Shape s : getShapes()){
                                    if(s.selected()){
                                        s.selected(false);
                                    }
                                }
                            }
                            shape.setSelected(inputControleur.position());
                            selection(true);
                        }
                    }
                }
            }
        }

        if(cmd != null){
            if(cmd.execute()){
                push(cmd);
                clearRedo();
            }
        }

        draw();
    }

    private boolean asSelected() {
        for (Shape s : getShapes()) 
            if(s.selected()) return true;
        return false;
    }
    private Shape topShape(Point2D pos){
        System.out.println(pos.toString());
        for (Shape s : orderShapes().values()) {
            if(s.isInside(pos)){
                return s;
            }
        }
        return null;
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

    public Shape[] getShapes() {
        return _shapes;
    }
    public void selection(boolean sel){ _selection = sel; }
    public boolean selection(){ return _selection; }
}

