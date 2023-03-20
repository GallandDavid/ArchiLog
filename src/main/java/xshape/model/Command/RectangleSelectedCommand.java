package xshape.model.Command;

import xshape.controleur.XShape;
import xshape.model.Shape;

public class RectangleSelectedCommand extends Command{
    private double _mouse_x;
    private double _mouse_y;

    public RectangleSelectedCommand(XShape app, Object editor, double x, double y) {
        super(app, editor);
        _mouse_x = x;
        _mouse_y = y;
    }

    @Override
    public boolean execute() {
        _app.addSelectedShape((Shape) _app.factory().createRectangle(_mouse_x, _mouse_y, false,_app));
        return false;
    }
    
}
