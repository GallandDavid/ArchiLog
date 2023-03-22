package xshape.model.Command;
import xshape.controleur.XShape;
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
            saveBackup();
            _app.addShape(shape);
            return false;
        }
        _app._selected_item.remove();
        _app._selected_item = null;
        if(_mouse_y > ((ToolBarDirector)_app).toolBar().getHeight()){
            Shape shape = _app.factory().createRectangle(_mouse_x, _mouse_y, false, _app);
            _editor = shape;
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
        _app.removeShape(((Shape) _editor).getId());
        System.gc();
    }

    @Override
    public void backup(){
        Shape s = (Shape)_backup;
        if(s != null)
            _app.addShape(s);
        else    
            System.out.println("backup null");
    }
    
}
