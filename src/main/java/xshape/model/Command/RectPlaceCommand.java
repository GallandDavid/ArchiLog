package xshape.model.Command;
import xshape.controleur.XShape;
import xshape.model.Builder.ToolBarDirector;

public class RectPlaceCommand extends Command{
    private double _mouse_x;
    private double _mouse_y;

    public RectPlaceCommand(XShape app, Object editor, double x, double y) {
        super(app, editor);
        _mouse_x = x;
        _mouse_y = y;
    }

    public RectPlaceCommand(XShape app, Object editor) {
        super(app, editor);
    }

    @Override
    public boolean execute() {
        if(_editor == null){
            _app.addShape(_app.factory().createRectangle(_app));
            return false;
        }
        _app._selected_item.remove();
        _app._selected_item = null;
        System.gc();
        
        if(_mouse_y > ((ToolBarDirector)_app).toolBar().getHeight()){
            _app.addShape(_app.factory().createRectangle(_mouse_x, _mouse_y, _app));
            return true;
        }
        return false;
    }
    
    
}
