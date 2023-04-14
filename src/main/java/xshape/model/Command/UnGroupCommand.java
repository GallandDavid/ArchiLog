package xshape.model.Command;

import java.util.ArrayList;

import xshape.controleur.XShape;
import xshape.model.shape.Group;
import xshape.model.shape.Shape;

public class UnGroupCommand extends Command{

    public UnGroupCommand(XShape app, ArrayList<Object> editor) {
        super(app, editor);
    }

    @Override
    public void backup() {
        _app.removeShape(((Shape)_backup.get(0)).getId());
        for (Object shape : _editor) {
            _app.addShape((Shape)shape);
        }
    }

    @Override
    public boolean execute() {
        _app.printShapes();
        Group grp = (Group) _editor.get(0);
        _backup.add(grp);
        _app.removeShape(grp.getId());
        _editor = new ArrayList<>();
        for (Shape shape : grp.group()) {
            Shape s = (Shape) instanceShape(shape, null);
            _editor.add(s);
            _app.addShape(s);
        }

        _app.printShapes();
        return true;
    }

    @Override
    public void undo() {
        for (Object shape : _editor) {
            _app.removeShape(((Shape)shape).getId());
        }
        _app.addShape((Shape)_backup.get(0));
    }
    
}
