package xshape.model.visitor;

import xshape.model.Command.GroupCommand;
import xshape.model.Command.RectPlaceCommand;
import xshape.model.Command.ShapeTranslateCommand;
import xshape.model.Command.TrashBinCommand;
import xshape.model.Command.UnGroupCommand;

public interface IInputVisitor {
    void visit(TrashBinCommand mc);
    void visit(RectPlaceCommand rectPlaceCommand);
    void visit(ShapeTranslateCommand shapeTranslateCommand);
    void visit(GroupCommand groupCommand);
    void visit(UnGroupCommand unGroupCommand);
}   
