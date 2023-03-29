package xshape.model.visitor;

import java.awt.geom.Point2D;

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
import xshape.model.shape.Shape;

public class InputCommandVisitor implements IInputVisitor{



    @Override
    public void visit(GroupCommand mc) {
        for (Shape  s : mc._app.getShapes()) {
            if(s.selected()){
                mc.add(s);
            }
        }
    }

    @Override
    public void visit(UnGroupCommand mc) {
        for (Shape  s : mc._app.getShapes()) {
            if(s.selected())
                mc.add(s);
        }
    }
}
