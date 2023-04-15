package xshape.controleur;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.SortedMap;
import java.util.TreeMap;

import xshape.model.Command.Command;
import xshape.model.Command.CommandHistory;
import xshape.model.Command.GroupCommand;
import xshape.model.Command.ICommand;
import xshape.model.Command.ShapePlaceCommand;
import xshape.model.Command.ShapeTranslateCommand;
import xshape.model.Command.TrashBinCommand;
import xshape.model.Command.UnGroupCommand;
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
import xshape.model.shape.tools.PopUpMenu;
import xshape.model.shape.tools.WhiteBoard;
import xshape.model.shape.tools.toolbar.ShapeToolBar;
import xshape.model.shape.tools.toolbar.SystemToolBar;
import xshape.model.visitor.DrawVisitor;

public abstract class XShape implements CommandHistory, IInputObserver, IMenuable, IShapeContenable, IPrintable, IRunnable, IClickable{
    private WhiteBoard _whiteBoard = null;
    private SystemToolBar _systemToolBar = null;
    private ShapeToolBar _shapesToolBar = null;
    private PopUpMenu _popUpMenu = null;
    private boolean _selection = false;
    private Shape _placed_shape = null;
    protected ShapeFactory _factory = new ShapeFactory();
    protected DrawVisitor _drawer = null;
    private Point2D _mouse_pos = null;
    Shape[] _shapes = null;
    LinkedList<ICommand> _history = new LinkedList<>();
    LinkedList<ICommand> _redos = new LinkedList<>();

    private void createScene() {
        Command command1 = new ShapePlaceCommand(this, 200,300, true);
        Command command2 = new ShapePlaceCommand(this, 150, 200, true);
        Command[] tmp = {command1,command2};
        for(Command cmd : tmp)
            cmd.execute();
    }

    @Override public SortedMap<Integer, Shape> orderShapes() {
        SortedMap<Integer, Shape> map = new TreeMap<Integer, Shape>( new Comparator<Integer>() {
            public int compare(Integer a, Integer b){ return -(b.compareTo(a)); } });
        for (int i = 0; i < _shapes.length; i ++)
            map.put(_shapes[i].deepth(), _shapes[i]);
        return map;
    }

    @Override public void whiteBoard(WhiteBoard rect){ _whiteBoard = rect; }
    @Override public WhiteBoard whiteBoard(){ return _whiteBoard; }
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
        clear();
        if (_shapes == null)    createScene();

        if(whiteBoard() != null) whiteBoard().accept(_drawer);
        if(_shapes != null){
            ArrayList<Shape> shapes = new ArrayList<>();
            for(Shape s : orderShapes().values()){
                shapes.add(s);
            }
            for(int i = shapes.size() - 1; i >= 0; i --){
                shapes.get(i).accept(_drawer);
            }
        }
        _shapesToolBar.accept(_drawer);
        if(placedShape() != null)
            placedShape().accept(_drawer);
        _systemToolBar.accept(_drawer);
        if(_popUpMenu != null) _popUpMenu.accept(_drawer);
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
            shape.visibleTranslate(mousVec(pos));
            shape.translate(mousVec(pos));
        }
    }

    private boolean shapeIsInsideRect(Rectangle rect, ArrayList<Shape> shapes){
        for (Shape shape : shapes) {
            if((!rect.isInside(new Point2D.Double(shape.visiblePosition().getX() - shape.size().getX() / 2, shape.visiblePosition().getY() - shape.size().getY() / 2))) ||
                (!rect.isInside(new Point2D.Double(shape.visiblePosition().getX() + shape.size().getX() / 2, shape.visiblePosition().getY() + shape.size().getY() / 2)))){
                return false;
            }
        }
        return true;
    }



    @Override
    public void update(double width, double height) {
        _shapesToolBar.size(new Point2D.Double(width, height));
    }

    @Override
    public void update(InputControl inputControleur) {
        Command cmd = null;
        //left pressed alone
        if(inputControleur.left().now() && inputControleur.leftPressed() && !inputControleur.rightPressed() && !inputControleur.mouseMoved() && !inputControleur.ctrlPressed()){
            mousePos(inputControleur.position());
            if(systemToolBar().isInside(inputControleur.position())){
            }else if(shapesToolBar().isInside(inputControleur.position())){
                if(shapesToolBar().isInItem(inputControleur.position())){
                    if(shapesToolBar().rect().isInside(inputControleur.position())){
                        Shape rect = shapesToolBar().rect();
                        mousePos(inputControleur.position());
                        addShapeToPlaced(factory().createRectangle(rect.position().getX() - mousVec(rect.position()).getX(), rect.position().getY() - mousVec(rect.position()).getY(), rect.size().getY(), rect.size().getX(),true));
                    }
                    for (Shape shape : getSelected()) {
                        shape.selected(false);
                    }
                }
            }else if(isPopUping() && popUpMenu().isInside(inputControleur.position())){
                if(popUpMenu().edit().isInside(inputControleur.position())){
                    popUpMenu(null);
                }
                if(popUpMenu().nbSelected() > 1 && popUpMenu().group().isInside(inputControleur.position())){
                    ArrayList<Object> shapes = new ArrayList<>();
                    for (Shape shape : getSelected()) { shapes.add(shape); }
                    cmd = new GroupCommand(this, shapes);
                    popUpMenu(null);
                }else if(popUpMenu().grouped() && popUpMenu().ungroup().isInside(inputControleur.position())){
                    ArrayList<Object> shapes = new ArrayList<>();
                    shapes.add(getSelected().get(0));
                    cmd = new UnGroupCommand(this,shapes);
                    popUpMenu(null);
                }
                //pop up press action
            }else if(isPopUping() && !popUpMenu().isInside(inputControleur.position())){
                popUpMenu(null);
            }else{
                Shape shape = topShape(inputControleur.position());
                if(shape != null){
                    if(!shape.selected()){
                    for (Shape s : getSelected())
                        s.selected(false);
                    }
                    shape.selected(true);
                    selection(true);
                }else{
                    whiteBoard().selected(true);
                }
                mousePos(inputControleur.position());
            }
        }
        //left pressed ctrl
        if(inputControleur.left().now() && inputControleur.leftPressed() && !inputControleur.rightPressed() && !inputControleur.mouseMoved() && inputControleur.ctrlPressed()){
            if(whiteBoard().isInside(inputControleur.position())){
                whiteBoard().selected(true);
                mousePos(inputControleur.position());
            }
        }
        //left clicked ctrl
        if(inputControleur.left().now() && inputControleur.leftReleased() && !inputControleur.rightPressed() && !inputControleur.mouseMoved() && inputControleur.ctrlPressed()){
            if(whiteBoard().isInside(inputControleur.position())){
                Shape shape = topShape(inputControleur.position());
                if(shape != null)
                    shape.selected(!shape.selected());
                if(getSelected().size() < 1) selection(false);
                else selection(true);
                mousePos(inputControleur.position());
            }
            whiteBoard().selected(false);
            systemToolBar().unSelect();
        }
        //right pressed alone
        if(inputControleur.right().now() && inputControleur.rightPressed() && !inputControleur.leftPressed() && !inputControleur.mouseMoved()){
            // nothing at this time 
        }
        //left mouse dragged
        if(inputControleur.mouseMoved() && inputControleur.leftPressed() && !inputControleur.rightPressed()){
            System.out.println("mouse grag");
            if(placedShape() != null){
                placedShape().visibleTranslate(mousVec(inputControleur.position()));
            }else if(whiteBoard().selected()){
                translateWhiteBoard(inputControleur.position());
            }else if(selection()){
                for (Shape shape : getSelected()) {
                    shape.visibleTranslate(mousVec(inputControleur.position()));                
                }
            }
            mousePos(inputControleur.position());
        }
        //left clicked (released)
        if(inputControleur.left().now() && inputControleur.leftReleased() && !inputControleur.rightPressed() && !inputControleur.mouseMoved() && !inputControleur.ctrlPressed()){
            if(placedShape() != null){
                addShapeToPlaced(null);
            }else if(whiteBoard().selected()){
                if(topShape(inputControleur.position()) == null)
                    for (Shape s : getSelected())
                        s.selected(false);
                whiteBoard().selected(false);
            }else if(systemToolBar().isInside(inputControleur.position())){
                if(systemToolBar().isInItem(inputControleur.position())){
                    if(systemToolBar().files().isInside(inputControleur.position())){
                        systemToolBar().selectFiles();
                    }else if(systemToolBar().edit().isInside(inputControleur.position())){
                        systemToolBar().selectEdit();
                    }else if(systemToolBar().trashbin().isInside(inputControleur.position())){
                        ArrayList<Object> shapes = new ArrayList<>();
                        for (Shape s : getSelected()){
                            System.out.println(s.toString());
                            s.visiblePosition(s.position());
                                shapes.add(s);
                        }
                        cmd = new TrashBinCommand(this, shapes);
                    }else if(systemToolBar().filesSelected()){
                        if(systemToolBar().save().isInside(inputControleur.position())){
                            //save
                        }else if(systemToolBar().load().isInside(inputControleur.position())){
                            //load
                        }
                    }else if(systemToolBar().editSelected()){
                        if(systemToolBar().undo().isInside(inputControleur.position())){
                            undo();
                        }else if(systemToolBar().redo().isInside(inputControleur.position())){
                            redo();
                        }
                    }
                }
            }
            if(!systemToolBar().isInItem(inputControleur.position())){
                systemToolBar().unSelect();
            }
            whiteBoard().selected(false);
        }
        //left dragg released (released)
        if(inputControleur.left().now() && inputControleur.leftReleased() && !inputControleur.rightPressed() && inputControleur.mouseMoved()){
            if(placedShape() != null){ 
                ArrayList<Shape> tmp = new ArrayList<Shape>();
                tmp.add(placedShape());
                if(shapeIsInsideRect(whiteBoard().rect, tmp)){ 
                    ArrayList<Object> shapes = new ArrayList<>();
                    shapes.add(placedShape());
                    cmd = new ShapePlaceCommand(this,shapes);
                    addShapeToPlaced(null);
                }else{
                    addShapeToPlaced(null);
                }
            }else if(systemToolBar().isInside(inputControleur.position())){
                if(systemToolBar().isInItem(inputControleur.position())){
                    if(systemToolBar().files().isInside(inputControleur.position())){
                        systemToolBar().selectFiles();
                    }else if(systemToolBar().edit().isInside(inputControleur.position())){
                        systemToolBar().selectEdit();
                    }else if(systemToolBar().trashbin().isInside(inputControleur.position())){
                        ArrayList<Object> shapes = new ArrayList<>();
                        for (Shape s : getSelected()){
                            s.visiblePosition(s.position());
                                shapes.add(s);
                        }
                        cmd = new TrashBinCommand(this, shapes);
                    }else if(systemToolBar().filesSelected()){
                        if(systemToolBar().save().isInside(inputControleur.position())){
                            //save
                        }else if(systemToolBar().load().isInside(inputControleur.position())){
                            //load
                        }
                    }else if(systemToolBar().editSelected()){
                        if(systemToolBar().undo().isInside(inputControleur.position())){
                            undo();
                        }else if(systemToolBar().redo().isInside(inputControleur.position())){
                            redo();
                        }
                    }
                }
            }else if(!whiteBoard().selected() && selection()){
                if(shapeIsInsideRect(whiteBoard().rect, getSelected())){
                    ArrayList<Object> shapes = new ArrayList<>();
                    shapes.addAll(getSelected());
                    cmd = new ShapeTranslateCommand(this, shapes);
                }else
                    for (Shape shape : getSelected()) {
                        shape.visiblePosition(shape.position());
                    }   
            }
            whiteBoard().selected(false);
            systemToolBar().unSelect();
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
            systemToolBar().unSelect();
        }
        if(cmd != null){
            if(cmd.execute()){
                push(cmd);
                clearRedo();
            }
            printSelectShape();
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

