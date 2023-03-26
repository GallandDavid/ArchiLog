package xshape.model.visitor;

import java.lang.reflect.Array;
import java.util.ArrayList;

import xshape.model.Command.MouseClickedCommand;
import xshape.model.Command.MouseDraggedCommand;
import xshape.model.Command.MouseEnteredCommand;
import xshape.model.Command.MouseExitedCommand;
import xshape.model.Command.MouseMovedCommand;
import xshape.model.Command.MousePressedCommand;
import xshape.model.Command.MouseReleasedCommand;
import xshape.model.Command.TrashBinCommand;
import xshape.model.shape.Shape;

public class InputCommandVisitor implements IInputVisitor{

    @Override
    public void visit(MouseClickedCommand mc) {
    }

    @Override
    public void visit(MouseDraggedCommand mc) {
        for (Shape s : mc._app.orderShapes().values()) {
            if(s.isSelected())
                mc.add(s);
        }

    }

    @Override
    public void visit(MouseEnteredCommand mc) {
    }

    @Override
    public void visit(MouseExitedCommand mc) {
    }

    @Override
    public void visit(MouseMovedCommand mc) {
    }

    @Override
    public void visit(MousePressedCommand mc) {
        boolean breake = false;
        boolean inside = false;
        if(mc._app.selection())
            for (Shape s : mc._app.orderShapes().values()){
                if(breake) break;
                if(s.isInside(new java.awt.geom.Point2D.Double(mc.mouseX(), mc.mouseY()))){
                    inside = true;
                    if(s.isSelected()){
                        for (Shape shape : mc._app.orderShapes().values())
                            if(shape.isSelected())
                                mc.add(shape);
                        breake = true;
                    }else{
                        for (Shape shape : mc._app.orderShapes().values())
                            if(shape.isSelected())
                                shape.setSelected(false);
                        mc.add(s);
                        breake = true;

                    }
                }
            if(!inside) mc._app.selection(false);
            }
        for (Shape s : mc._app.orderShapes().values()) {
            if(s.isInside(new java.awt.geom.Point2D.Double(mc.mouseX(), mc.mouseY()))){
                mc._app.selection(true);
                mc.add(s);
                break;
            }
        }

    }

    @Override
    public void visit(MouseReleasedCommand mc) {

        if(mc.mouseY() > mc._app.toolBar().getHeight())
            for (Shape shape : mc._app.orderShapes().values())
                if(shape.isSelected())
                    mc.add(shape);
        else
            if(mc.mouseX() > mc._app.toolBar().getTrashBinPosX() && mc.mouseX() - mc._app.toolBar().getTrashBinSizeX() / 2 < mc._app.toolBar().getTrashBinPosX() + mc._app.toolBar().getTrashBinSizeX() && mc.mouseY() / 2 > mc._app.toolBar().getTrashBinPosY() - mc._app.toolBar().getTrashBinSizeY() && mc.mouseY() / 2 < mc._app.toolBar().getTrashBinPosY() + mc._app.toolBar().getTrashBinSizeY() / 2){
                System.out.println("ok");
                mc._app.update(new TrashBinCommand(mc._app));
            }
    }

    @Override
    public void visit(TrashBinCommand mc) {
        if(mc._app.selection())
            for (Shape s : mc._app.getShapes())
                if(s.isSelected())
                    mc.add(s);   
    }
}
