package xshape.model.Command;

import java.util.ArrayList;

import xshape.controleur.XShape;
import xshape.model.shape.Shape;
import xshape.model.visitor.IInputVisitor;

public class TrashBinCommand extends Command{

    public TrashBinCommand(XShape app, ArrayList<Object> editor) {
        super(app, editor);
    }

    @Override
    public void backup() {
        for(Object shape : _backup)
            _app.removeShape(((Shape) shape).getId());
    }

    @Override
    public boolean execute() {
        saveBackup(null);
        ArrayList<String> tmp = new ArrayList<>();
        for(Object shape : _editor){
            System.out.println((Shape) shape);
            tmp.add(((Shape) shape).getId());
        }
        for(String id : tmp)
            _app.removeShape(id);
        return false;
    }

    @Override
    public void undo() {
        for(Object shape : _backup)
            _app.addShape((Shape) shape);
    }

    @Override public void accept(IInputVisitor visitor) { visitor.visit(this); }
    
}
