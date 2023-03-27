package xshape.model.Command;

import java.awt.geom.Point2D;

import xshape.controleur.XShape;
import xshape.model.shape.Shape;
import xshape.model.visitor.IInputVisitor;

public class ShapeDragCommand extends Command{
    private double _mouse_x;
    private double _mouse_y;

    public ShapeDragCommand(XShape app, Object editor, double x, double y) {
        super(app, editor);
        _mouse_x = x;
        _mouse_y = y;
    }

    @Override
    public boolean execute() {
        for (Object shape : _editor) 
            ((Shape) shape).visibleTranslate((Point2D)  ((Shape) shape).getMouseVec(_mouse_x,_mouse_y).clone());
        return false;
    }

    @Override
    public String print() { return "ShapeDragCommand" + super.print(); }



    @Override
    public void saveBackup(){
    }

    @Override
    public void backup() {
    }

    @Override
    public void undo() {
    }

    @Override
    public void accept(IInputVisitor visitor) {
        visitor.visit(this);
    }
}
