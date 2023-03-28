package xshape.model.Command;

import java.awt.geom.Point2D;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import xshape.controleur.XShape;
import xshape.model.observer.Iobserver;
import xshape.model.shape.Group;
import xshape.model.shape.Shape;
import xshape.model.visitor.IInputVisitor;

public class GroupCommand extends Command{

    public GroupCommand(XShape app) {
        super(app);
    }

    @Override
    public void backup() {
        _app.addShape((Shape)_backup.get(0));
        for(Object s : _editor)
            _app.removeShape(((Shape) s).getId());
    }

    @Override
    public boolean execute() {
        System.out.println("execute group cmd");
        saveBackup(null);
        ArrayList<Shape> array_backup = new ArrayList<>();
        for (Object s : _backup) {
            Shape shape = (Shape) s;
            array_backup.add(shape);
            _app.removeShape(shape.getId());
        }
        Group grp = _app.factory().createGroup(new Point2D.Double(0,0), new Point2D.Double(0,0), false, _app, array_backup);
        saveBackup(null);
        _backup = new ArrayList<>();
        ArrayList<Shape> groupe_array_backup = new ArrayList<>();
        for (Object s : _backup) {
            Shape shape = (Shape) s;
            groupe_array_backup.add(shape);
        }
        grp.selected(true);
        System.out.println(grp.toString());
        instanceShape(grp, groupe_array_backup);
        _backup.add((Object) grp);
        _app.addShape(grp);
        return true;
    }

    @Override
    public void undo() {
        _app.removeShape(((Shape)_backup.get(0)).getId());
        for(Object s : _editor)
            _app.addShape((Shape) s);
    }

    @Override
    public void accept(IInputVisitor visitor) {
        visitor.visit(this);
    }
    
}
