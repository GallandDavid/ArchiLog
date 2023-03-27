package xshape.model.Command;

import xshape.controleur.XShape;
import xshape.model.shape.Shape;
import xshape.model.visitor.IInputVisitor;

public class MouseLeftClickPressedCommand extends MouseCommand{

    public MouseLeftClickPressedCommand(XShape app, double mouse_x, double mouse_y) {
        super(app, mouse_x, mouse_y);
    }

    @Override
    public void backup() {
    }

    @Override
    public boolean execute() {
        for(Object shape : _editor){
            ((Shape) shape).setMovable(true);
            ((Shape) shape).selected(true);
            ((Shape) shape).setPrevMouse(_mouse_x, _mouse_y);
        }
        return false;
    }

    @Override
    public void undo() {
    }

    @Override public void accept(IInputVisitor visitor) { visitor.visit(this); }
    
}