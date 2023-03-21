package xshape.model.Command;
import xshape.controleur.XShape;
import xshape.model.Rectangle;
import xshape.model.Shape;
import xshape.model.Builder.ToolBarDirector;

public class RectPlaceCommand extends Command{
    private double _mouse_x;
    private double _mouse_y;
    private boolean _god_place;

    public RectPlaceCommand(XShape app, double x, double y) {
        super(app, null);
        _mouse_x = x;
        _mouse_y = y;
        _god_place = false;
    }

    public RectPlaceCommand(XShape app, boolean god_place) {
        super(app, null);
        _mouse_x = -5000;
        _mouse_y = -5000;
        _god_place = god_place;
    }

    public RectPlaceCommand(XShape app, double x, double y, boolean god_place) {
        super(app, null);
        _mouse_x = x;
        _mouse_y = y;
        _god_place = god_place;
    }

    @Override
    public boolean execute() {
        if(_god_place){
            Shape shape = _app.factory().createRectangle(_mouse_x, _mouse_y,false, _app);
            _editor = shape;
            _app.addShape(shape);
            return false;
        }
        _app._selected_item.remove();
        _app._selected_item = null;
        System.out.println("y = " + _mouse_y + "   /   tb height : " + ((ToolBarDirector)_app).toolBar().getHeight() + "\n y > tb : " + (_mouse_y > ((ToolBarDirector)_app).toolBar().getHeight()));
        if(_mouse_y > ((ToolBarDirector)_app).toolBar().getHeight()){
            Shape shape = _app.factory().createRectangle(_mouse_x, _mouse_y, false, _app);
            _editor = shape;
            _app.addShape(shape);
            return true;
        }

        
        return false;
    }

    @Override
    public void print() {
        System.out.println("RectPlaceCommand");
    }

    @Override
    public void undo(){
        _app.removeShape(((Rectangle) _editor).getId());
    }
    
    
}
