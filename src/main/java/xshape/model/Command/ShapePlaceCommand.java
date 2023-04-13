package xshape.model.Command;
import java.util.ArrayList;

import xshape.controleur.XShape;
import xshape.model.shape.Shape;

public class ShapePlaceCommand extends Command{
    private double _mouse_x;
    private double _mouse_y;
    private boolean _god_place;

    public ShapePlaceCommand(XShape app, double x, double y) {
        super(app);
        _mouse_x = x;
        _mouse_y = y;
        _god_place = false;
    }

    public ShapePlaceCommand(XShape app, double x, double y, boolean god_place) {
        super(app);
        _mouse_x = x;
        _mouse_y = y;
        _god_place = god_place;
    }

    public ShapePlaceCommand(XShape app, ArrayList<Object> shape) {
        super(app, shape);
    }

    @Override
    public boolean execute() {
        if(_god_place){
            Shape shape = _app.factory().createRectangle(_mouse_x, _mouse_y,false);
            shape.selected(false);
            _app.addShape(shape);
            return false;
        }
        if(_editor.size() != 0){
            Shape shape = (Shape) instanceShape(_editor.get(0));
            shape.position(shape.visiblePosition());
            if(_app.whiteBoard().isInside(shape.position())){
                _app.addShape(shape);
                return true;
            }
            return false;
        }
        if(_mouse_y > _app.systemToolBar().size().getY()){
            Shape shape = _app.factory().createRectangle(_mouse_x, _mouse_y, false);
            shape.selected(true);
            _editor.add(shape);
            saveBackup();
            _app.addShape(shape);
            return true;
        }
        return false;
    }

    @Override
    public String print() { return "RectPlaceCommand" + super.print(); }


    @Override
    public void undo(){
        for (Object shape : _editor)
            _app.removeShape(((Shape) shape).getId());
        System.gc();
    }

    @Override
    public void backup(){
        if(_editor != null || !_editor.isEmpty())
            for (Object shape : _editor)
                _app.addShape((Shape) shape);
    }
}
