package xshape.model.Command;
import xshape.controleur.XShape;
import xshape.model.Builder.toolbar.ToolBarDirector;
import xshape.model.shape.Shape;
import xshape.model.visitor.IInputVisitor;

public class RectPlaceCommand extends Command{
    private double _mouse_x;
    private double _mouse_y;
    private boolean _god_place;

    public RectPlaceCommand(XShape app, double x, double y) {
        super(app);
        _mouse_x = x;
        _mouse_y = y;
        _god_place = false;
    }

    public RectPlaceCommand(XShape app, boolean god_place) {
        super(app);
        _mouse_x = -5000;
        _mouse_y = -5000;
        _god_place = god_place;
    }

    public RectPlaceCommand(XShape app, double x, double y, boolean god_place) {
        super(app);
        _mouse_x = x;
        _mouse_y = y;
        _god_place = god_place;
    }

    @Override
    public boolean execute() {
        if(_god_place){
            Shape shape = _app.factory().createRectangle(_mouse_x, _mouse_y,false, _app);
            shape.selected(false);
            _app.addShape(shape);
            return false;
        }
        _app._selected_item.remove();
        _app._selected_item = null;
        if(_mouse_y > ((ToolBarDirector)_app).toolBar().getHeight()){
            Shape shape = _app.factory().createRectangle(_mouse_x, _mouse_y, false, _app);
            shape.selected(true);
            _editor.add(shape);
            saveBackup(null);
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

    @Override
    public void accept(IInputVisitor visitor) {
        visitor.visit(this);
    }
    
}
