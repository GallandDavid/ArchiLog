package xshape.model.visitor;

import xshape.model.Command.DragSelectedCommand;
import xshape.model.Command.GroupCommand;
import xshape.model.Command.MouseClickedCommand;
import xshape.model.Command.MouseDraggedCommand;
import xshape.model.Command.MouseEnteredCommand;
import xshape.model.Command.MouseExitedCommand;
import xshape.model.Command.MouseMovedCommand;
import xshape.model.Command.MouseLeftClickPressedCommand;
import xshape.model.Command.MouseReleasedCommand;
import xshape.model.Command.MouseRightClickClickedCommand;
import xshape.model.Command.MouseShiftLeftClickClickedCommand;
import xshape.model.Command.RectPlaceCommand;
import xshape.model.Command.RectangleSelectedCommand;
import xshape.model.Command.RedoCommand;
import xshape.model.Command.ShapeDragCommand;
import xshape.model.Command.ShapeSelectCommand;
import xshape.model.Command.ShapeTranslateCommand;
import xshape.model.Command.TrashBinCommand;
import xshape.model.Command.UnGroupCommand;
import xshape.model.Command.UndoCommand;

public interface IInputVisitor {
    void visit(MouseClickedCommand mc);
    void visit(MouseDraggedCommand mc);
    void visit(MouseEnteredCommand mc);
    void visit(MouseExitedCommand mc);
    void visit(MouseMovedCommand mc);
    void visit(MouseLeftClickPressedCommand mc);
    void visit(MouseReleasedCommand mc);
    void visit(TrashBinCommand mc);
    void visit(MouseRightClickClickedCommand mouseRightClickReleasedCommand);
    void visit(DragSelectedCommand dragSelectedCommand);
    void visit(RectangleSelectedCommand rectangleSelectedCommand);
    void visit(RectPlaceCommand rectPlaceCommand);
    void visit(RedoCommand redoCommand);
    void visit(ShapeDragCommand shapeDragCommand);
    void visit(ShapeSelectCommand shapeSelectCommand);
    void visit(ShapeTranslateCommand shapeTranslateCommand);
    void visit(UndoCommand undoCommand);
    void visit(MouseShiftLeftClickClickedCommand mouseShiftRightClickClickedCommand);
    void visit(GroupCommand groupCommand);
    void visit(UnGroupCommand unGroupCommand);
}   
