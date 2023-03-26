package xshape.model.Command;

import java.awt.geom.Point2D;

import xshape.controleur.XShape;
import xshape.model.shape.Shape;
import xshape.model.visitor.IInputVisitor;

public class MouseDraggedCommand extends MouseCommand{

    public MouseDraggedCommand(XShape app, double mouse_x, double mouse_y) {
        super(app, mouse_x, mouse_y);
    }

    @Override
    public boolean execute() {
        for (Object shape : _editor) {
            ((Shape) shape).visibleTranslate((Point2D)  ((Shape) shape).getMouseVec(_mouse_x,_mouse_y));
            ((Shape)shape).setPrevMouse(_mouse_x,_mouse_y);
        }
        return false;
    }

    @Override public void undo() {}
    @Override public void backup() {}

    @Override public void accept(IInputVisitor visitor) { visitor.visit(this); }
    
}
