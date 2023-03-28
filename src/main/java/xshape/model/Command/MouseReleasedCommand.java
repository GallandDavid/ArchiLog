package xshape.model.Command;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import xshape.controleur.XShape;
import xshape.model.Builder.toolbar.ToolBarDirector;
import xshape.model.shape.Shape;
import xshape.model.visitor.IInputVisitor;

public class MouseReleasedCommand extends MouseCommand{

    public MouseReleasedCommand(XShape app, double mouse_x, double mouse_y) {
        super(app, mouse_x, mouse_y);
    }

    @Override
    public boolean execute() {
        if(_editor.isEmpty()) return false;
        if(_mouse_y < ((ToolBarDirector)_app).toolBar().getHeight()) return false;
        ArrayList<Point2D> tmp = new ArrayList<>();
        for (int i = 0; i < _editor.size(); i++) {
            tmp.add(((Shape) _editor.get(i)).visiblePosition());
            ((Shape) _editor.get(i)).setMovable(false);
            ((Shape) _editor.get(i)).visiblePosition((Point2D) ((Shape) _editor.get(i)).position());
        }
        saveBackup(null);
        for (int i = 0; i < _editor.size(); i++) {
            ((Shape) _editor.get(i)).position((Point2D)tmp.get(i).clone());
            ((Shape) _editor.get(i)).visiblePosition((Point2D)tmp.get(i).clone());
        }
        return true;
    }

    @Override
    public void backup(){
        undo();
    }

    @Override
    public void undo(){
        for (int i = 0; i < _editor.size(); i ++) {
            Shape tmp = (Shape) instanceShape((Shape)_editor.get(i), null);
            ((Shape)_editor.get(i)).duplicate((Shape)_backup.get(i));
            ((Shape)_backup.get(i)).duplicate(tmp);
        }
    }

    @Override public void accept(IInputVisitor visitor) { visitor.visit(this); }
    
}
