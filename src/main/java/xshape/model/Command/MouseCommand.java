package xshape.model.Command;

import xshape.controleur.XShape;

public abstract class MouseCommand extends Command{
    protected double _mouse_x;
    protected double _mouse_y;

    public MouseCommand(XShape app, double mouse_x, double mouse_y) {
        super(app);
        _mouse_x = mouse_x;
        _mouse_y = mouse_y;
    }

    public double mouseX(){
        return _mouse_x;
    }

    public double mouseY(){
        return _mouse_y;
    }
    
}
