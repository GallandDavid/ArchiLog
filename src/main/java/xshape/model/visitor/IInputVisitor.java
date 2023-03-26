package xshape.model.visitor;

import xshape.model.Command.MouseClickedCommand;
import xshape.model.Command.MouseDraggedCommand;
import xshape.model.Command.MouseEnteredCommand;
import xshape.model.Command.MouseExitedCommand;
import xshape.model.Command.MouseMovedCommand;
import xshape.model.Command.MousePressedCommand;
import xshape.model.Command.MouseReleasedCommand;

public interface IInputVisitor {
    void visit(MouseClickedCommand mc);
    void visit(MouseDraggedCommand mc);
    void visit(MouseEnteredCommand mc);
    void visit(MouseExitedCommand mc);
    void visit(MouseMovedCommand mc);
    void visit(MousePressedCommand mc);
    void visit(MouseReleasedCommand mc);
}   
