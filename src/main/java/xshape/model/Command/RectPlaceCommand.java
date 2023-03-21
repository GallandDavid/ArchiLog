package xshape.model.Command;
import xshape.controleur.XShape;
import xshape.model.Rectangle;
import xshape.model.Shape;
import xshape.model.Builder.ToolBarDirector;

public class RectPlaceCommand extends Command{
    private double _mouse_x;
    private double _mouse_y;

    public RectPlaceCommand(XShape app, double x, double y) {
        super(app, null);
        _mouse_x = x;
        _mouse_y = y;
    }

    @Override
    public boolean execute() {
        if(_editor == null){
            _app.addShape(_app.factory().createRectangle(_app));
            return false;
        }
        _app.printSelectShape();
        _app._selected_item.remove();
        _app._selected_item = null;
        System.gc();
        
        if(_mouse_y > ((ToolBarDirector)_app).toolBar().getHeight()){
            Shape shape = _app.factory().createRectangle(_mouse_x, _mouse_y, _app);
            _editor = shape;
            _app.addShape(shape);
            _app.draw();
            return true;
        }

        _app.draw();
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
