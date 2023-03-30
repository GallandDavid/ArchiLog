package xshape.model.visitor;

import xshape.model.Command.GroupCommand;
import xshape.model.Command.RectPlaceCommand;
import xshape.model.Command.ShapeTranslateCommand;
import xshape.model.Command.TrashBinCommand;
import xshape.model.Command.UnGroupCommand;
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

    @Override
    public void visit(TrashBinCommand mc) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit'");
    }

    @Override
    public void visit(RectPlaceCommand rectPlaceCommand) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit'");
    }

    @Override
    public void visit(ShapeTranslateCommand shapeTranslateCommand) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit'");
    }
}
