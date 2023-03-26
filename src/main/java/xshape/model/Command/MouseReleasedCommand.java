package xshape.model.Command;

import java.awt.geom.Point2D;

import xshape.controleur.XShape;
import xshape.model.Builder.ToolBarDirector;
import xshape.model.shape.Shape;
import xshape.model.visitor.IInputVisitor;

public class MouseReleasedCommand extends MouseCommand{

    public MouseReleasedCommand(XShape app, double mouse_x, double mouse_y) {
        super(app, mouse_x, mouse_y);
    }

    @Override
    public boolean execute() {
        for (Object shape : _editor) {
            ((Shape) shape).setSelected(false);
            Point2D tmp = (Point2D) ((Shape) shape).visiblePosition();
            ((Shape) shape).visiblePosition((Point2D) ((Shape) shape).position());
            saveBackup();
            if(_mouse_y < ((ToolBarDirector)_app).toolBar().getHeight()) return false;
            ((Shape) shape).position((Point2D)tmp.clone());
            ((Shape) shape).visiblePosition((Point2D)tmp.clone());
        }
        return false;
    }

    @Override
    public void backup(){
        undo();
    }

    @Override
    public void undo(){
        for (int i = 0; i < _editor.size(); i ++) {
            Shape tmp = (Shape) instanceShape((Shape)_editor.get(i));
            ((Shape)_editor.get(i)).duplicate((Shape)_backup.get(i));
            ((Shape)_editor.get(i)).duplicate(tmp);
        }
    }

    @Override public void accept(IInputVisitor visitor) { visitor.visit(this); }
    
}
