package xshape.model.visitor;

import javafx.geometry.Point2D;
import xshape.model.Command.MouseClickedCommand;
import xshape.model.Command.MouseDraggedCommand;
import xshape.model.Command.MouseEnteredCommand;
import xshape.model.Command.MouseExitedCommand;
import xshape.model.Command.MouseMovedCommand;
import xshape.model.Command.MousePressedCommand;
import xshape.model.Command.MouseReleasedCommand;
import xshape.model.shape.Shape;

public class InputCommandVisitor implements IInputVisitor{

    @Override
    public void visit(MouseClickedCommand mc) {
    }

    @Override
    public void visit(MouseDraggedCommand mc) {
        for (Shape s : mc._app.orderShapes().values()) {
            if(s.isSelected())
                mc.add(s);
        }

    }

    @Override
    public void visit(MouseEnteredCommand mc) {
    }

    @Override
    public void visit(MouseExitedCommand mc) {
    }

    @Override
    public void visit(MouseMovedCommand mc) {
    }

    @Override
    public void visit(MousePressedCommand mc) {
        if(mc._app.selection())
            for (Shape s : mc._app.orderShapes().values())
                if(s.isInside(new java.awt.geom.Point2D.Double(mc.mouseX(), mc.mouseY()))){
                    if(s.isSelected()){
                        for (Shape shape : mc._app.orderShapes().values())
                            if(shape.isSelected())
                                mc.add(shape);
                    }else{
                        for (Shape shape : mc._app.orderShapes().values())
                            if(shape.isSelected())
                                shape.setSelected(false);
                        s.setSelected(true);
                        mc.add(s);
                    }
                }
        for (Shape s : mc._app.orderShapes().values()) {
            if(s.isInside(new java.awt.geom.Point2D.Double(mc.mouseX(), mc.mouseY()))){
                s.setSelected(true);
                mc.add(s);
                break;
            }
        }

    }

    @Override
    public void visit(MouseReleasedCommand mc) {
        for (Shape shape : mc._app.orderShapes().values())
            if(shape.isSelected())
                mc.add(shape);
    }
    
}
