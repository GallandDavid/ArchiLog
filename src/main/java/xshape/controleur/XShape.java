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
import xshape.model.Interface.IClickable;
import xshape.model.Interface.IMenuable;
import xshape.model.Interface.IPrintable;
import xshape.model.Interface.IRunnable;
import xshape.model.Interface.IShapeContenable;
import xshape.model.abstractFactory.ShapeFactory;
import xshape.model.controlInput.InputControl;
import xshape.model.observer.IInputObserver;
import xshape.model.shape.Rectangle;
import xshape.model.shape.Shape;
import xshape.model.shape.tools.popup.PopUpMenu;
import xshape.model.shape.tools.toolbar.shapestb.ShapeToolBar;
import xshape.model.shape.tools.toolbar.systemtb.SystemToolBar;

public abstract class XShape implements CommandHistory, IInputObserver, IMenuable, IShapeContenable, IPrintable, IRunnable, IClickable{
    private Rectangle _whiteBoard = null;
    private SystemToolBar _systemToolBar = null;
    private ShapeToolBar _shapesToolBar = null;
    private PopUpMenu _popUpMenu = null;
    private boolean _selection = false;
    private Shape _placed_shape = null;
    protected ShapeFactory _factory = null;
    private Point2D _mouse_pos = null;
    Shape[] _shapes = null;
    LinkedList<ICommand> _history = new LinkedList<>();
    LinkedList<ICommand> _redos = new LinkedList<>();

    private void createScene() {
        if(whiteBoard() != null) whiteBoard().draw();
        Command command1 = new RectPlaceCommand(this, 20,300, true);
        Command command2 = new RectPlaceCommand(this, 100, 200, true);
        Command[] tmp = {command1,command2};
        for(Command cmd : tmp)
            cmd.execute();
    }

    @Override public SortedMap<Integer, Shape> orderShapes() {
        SortedMap<Integer, Shape> map = new TreeMap<Integer, Shape>( new Comparator<Integer>() {
            public int compare(Integer a, Integer b){ return a.compareTo(b); } });
        for (int i = 0; i < _shapes.length; i ++)
            map.put(_shapes[i].deepth(), _shapes[i]);
        return map;
    }

    @Override public void whiteBoard(Rectangle rect){ _whiteBoard = rect; }
    @Override public Rectangle whiteBoard(){ return _whiteBoard; }
    @Override public Point2D mousePos(){ return _mouse_pos;}
    @Override public void mousePos(Point2D pos){ _mouse_pos = (Point2D) pos.clone(); }
    @Override public Point2D mousVec(Point2D pos){ return new Point2D.Double(pos.getX() - mousePos().getX(), pos.getY() - mousePos().getY()); }
    @Override public void systemToolBar(SystemToolBar toolBar){ _systemToolBar = toolBar; }
    @Override public SystemToolBar systemToolBar() { return _systemToolBar; }
    @Override public void shapesToolBar(ShapeToolBar toolBar){ _shapesToolBar = toolBar; }
    @Override public ShapeToolBar shapesToolBar() { return _shapesToolBar; }
    @Override public void popUpMenu(PopUpMenu popUpMenu){ _popUpMenu = popUpMenu; }
    @Override public PopUpMenu popUpMenu(){ return _popUpMenu; }
    @Override public ShapeFactory factory(){ return _factory; }
    @Override public void addShapeToPlaced(Shape shape){ _placed_shape = shape; }
    @Override public Shape placedShape(){ return _placed_shape;}
    @Override public Shape getShape(String ref){ for (Shape s : _shapes) if(s.getId().equals(ref)) return s; return null; }
    @Override public Shape[] getShapes() { return _shapes; }
    @Override public void selection(boolean sel){ _selection = sel; }
    @Override public boolean selection(){ return _selection; }
    @Override public void clearRedo(){ _redos.clear(); }
    @Override public void undo(){ if(!haveUndo()) pushRedo(pop()); }
    @Override public boolean haveUndo(){ return _history.isEmpty(); }
    @Override public void redo(){ if(!haveRedo()) push(popRedo()); }
    @Override public boolean haveRedo(){ return _redos.isEmpty(); }
    @Override public void push(ICommand command){ _history.addLast(command); }
    @Override public void pushRedo(ICommand command){ _redos.addLast(command); }
    @Override public ICommand pop(){
        ICommand cmd = _history.removeLast();
        cmd.undo();
        return cmd;
    }

    @Override public ICommand popRedo(){
        ICommand cmd = _redos.removeLast();
        cmd.backup();
        return cmd;
    }

    @Override public void draw(){
        if(_factory == null)    createFactory();
        if (_shapes == null)    createScene();
        if(_shapes != null)
            for(Shape s : orderShapes().values()){
                s.draw();
            }
        _shapesToolBar.draw();
        if(placedShape() != null)
            placedShape().draw();
        _systemToolBar.draw();
        if(_popUpMenu != null) _popUpMenu.draw();
        render();
    }


    @Override public void addShape(Shape shape){
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

    @Override public void removeShape(String ref){
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

    @Override public boolean asSelected() {
        for (Shape s : getShapes()) 
            if(s.selected()) return true;
        return false;
    }


    @Override public ArrayList<Shape> getSelected(){
        ArrayList<Shape> selected = new ArrayList<>();
        for (Shape shape : getShapes())
            if(shape.selected())
                selected.add(shape);
        return selected;
    }
    @Override public int nbSelected(){
        int nb = 0;
        for (Shape shape : getShapes())
            if(shape.selected())
                nb ++;
        return nb;
    }

    @Override public Shape topShape(Point2D pos){
        System.out.println(pos.toString());
        for (Shape s : orderShapes().values()) {
            if(s.isInside(pos)){
                return s;
            }
        }
        return null;
    }
    
    @Override public boolean isPopUping(){
        return popUpMenu() != null;
    }

    private void translateWhiteBoard(Point2D pos){
        whiteBoard().translate(mousVec(pos));
                for (Shape shape : getShapes()) {
                    shape.translate(mousVec(pos));
                }
    }

    @Override
    public void update(InputControl inputControleur) {
        Command cmd = null;
        inputControleur.print();
        //left pressed alone
        if(inputControleur.left().now() && inputControleur.leftPressed() && !inputControleur.rightPressed() && !inputControleur.mouseMoved() && !inputControleur.ctrlPressed()){
            mousePos(inputControleur.position());
            if(systemToolBar().isInside(inputControleur.position())){
                if(systemToolBar().isInItem(inputControleur.position())){
                    if(systemToolBar().files().isInside(inputControleur.position())){
                        systemToolBar().filesSelected();
                    }else if(systemToolBar().edit().isInside(inputControleur.position())){
                        systemToolBar().editSelected();
                    }else if(systemToolBar().trashbin().isInside(inputControleur.position())){
                        // trashbin (released ?)
                    }
                }

            }else if(shapesToolBar().isInside(inputControleur.position())){
                if(shapesToolBar().isInItem(inputControleur.position())){
                    // instance shape
                    mousePos(inputControleur.position());
                }
            }else if(isPopUping() && popUpMenu().isInside(inputControleur.position())){
            }else{
                System.out.println("on white board");
                Shape shape = topShape(inputControleur.position());
                if(shape != null){
                    System.out.println("on shape");
                    for (Shape s : getSelected())
                            s.selected(false);
                    shape.selected(true);
                }else{
                    System.out.println("on void");
                    whiteBoard().selected(true);
                }
                mousePos(inputControleur.position());
            }
        }
        //left pressed ctrl
        if(inputControleur.left().now() && inputControleur.leftPressed() && !inputControleur.rightPressed() && !inputControleur.mouseMoved() && inputControleur.ctrlPressed()){
            if(whiteBoard().isInside(inputControleur.position())){
                whiteBoard().selected(true);
                Shape shape = topShape(inputControleur.position());
                if(shape != null)
                    shape.selected(!shape.selected());
                mousePos(inputControleur.position());
            }
        }
        //right pressed alone
        if(inputControleur.right().now() && inputControleur.rightPressed() && !inputControleur.leftPressed() && !inputControleur.mouseMoved()){
            // nothing at this time 
        }
        //left mouse dragged
        if(inputControleur.mouseMoved() && inputControleur.leftPressed() && !inputControleur.rightPressed()){
            if(whiteBoard().selected()){
                translateWhiteBoard(inputControleur.position());
            } 
            else if(selection()){
                for (Shape shape : getSelected()) {
                    shape.visibleTranslate(mousVec(inputControleur.position()));                
                }
            }
            mousePos(inputControleur.position());
        }
        //left clicked (released)
        if(inputControleur.left().now() && inputControleur.leftReleased() && !inputControleur.rightPressed() && !inputControleur.mouseMoved() && !inputControleur.ctrlPressed()){
            if(whiteBoard().selected())
                if(topShape(inputControleur.position()) == null)
                    for (Shape s : getSelected())
                        s.selected(false);
            whiteBoard().selected(false);
        }
        //left dragg released (released)
        if(inputControleur.left().now() && inputControleur.leftReleased() && !inputControleur.rightPressed() && inputControleur.mouseMoved()){
            if(!whiteBoard().selected() && selection()){
                ArrayList<Object> shapes = new ArrayList<>();
                shapes.addAll(getSelected());
                cmd = new ShapeTranslateCommand(this, shapes, inputControleur.position().getX(),inputControleur.position().getY());
            }
            whiteBoard().selected(false);
        }
        //right click
        if(inputControleur.right().now() && inputControleur.rightReleased() && !inputControleur.leftPressed()){
            if(whiteBoard().isInside(inputControleur.position())){
                if(topShape(inputControleur.position()) != null){
                    if(getSelected().size() == 1 && getSelected().get(0).grouped()){
                        popUpMenu(factory().createPopUpMenu(inputControleur.position(), nbSelected(), true));
                    }else{
                        popUpMenu(factory().createPopUpMenu(inputControleur.position(), nbSelected(), false));
                    }
                }else{
                    //popup whiteboard
                }
            }
            whiteBoard().selected(false);
        }
        if(cmd != null){
            if(cmd.execute()){
                push(cmd);
                clearRedo();
            }
        }
        draw();
    }  
/*
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
*/
    public void printSelectShape() {
        System.out.println("Start select shape :\n----------");
        if(placedShape() == null)
            System.out.println("null");
        else
            System.out.println(placedShape().toString());
        System.out.println("End select shape :\n----------");
    }
    public void printShapes(){ if(_shapes != null) for(Shape s : _shapes) System.out.println(s.toString()); }
    public void printArray(Shape[] array){ for(Shape s : array) System.out.println(s); }

}

