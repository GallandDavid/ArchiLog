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
    public void visit(MouseClickedCommand mc) {
    }

    @Override
    public void visit(MouseDraggedCommand mc) {
        for (Shape s : mc._app.orderShapes().values()) {
            if(s.isMovable())
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
    public void visit(MouseLeftClickPressedCommand mc) {
        boolean breake = false;
        boolean inside = false;
        if(!mc._app.isInPopUpMenu(new Point2D.Double(mc.mouseX(),mc.mouseY()))){
            if(mc.mouseY() > mc._app.toolBar().getHeight()){
                if(mc._app.selection())
                    for (Shape s : mc._app.orderShapes().values()){
                        if(breake) break;
                        if(s.isInside(new Point2D.Double(mc.mouseX(), mc.mouseY()))){
                            inside = true;
                            if(s.selected()){
                                for (Shape shape : mc._app.orderShapes().values())
                                    if(shape.selected())
                                        mc.add(shape);
                                breake = true;
                            }else{
                                for (Shape shape : mc._app.orderShapes().values())
                                    if(shape.selected())
                                        shape.selected(false);
                                mc.add(s);
                                breake = true;

                            }
                        }
                        if(!inside){
                            for (Shape shape : mc._app.orderShapes().values())
                                    if(shape.selected())
                                        shape.selected(false);
                            mc._app.selection(false);
                        }
                    }
                else{
                    for (Shape s : mc._app.orderShapes().values()) {
                        if(s.selected())
                            s.selected(false);
                    }
                    for (Shape s : mc._app.orderShapes().values()) {
                        if(s.isInside(new Point2D.Double(mc.mouseX(), mc.mouseY()))){
                            mc._app.selection(true);
                            mc.add(s);
                            break;
                        }
                    }
                }
            }
        }

    }

    @Override
    public void visit(MouseReleasedCommand mc) {

        if(!mc._app.isInPopUpMenu(new Point2D.Double(mc.mouseX(),mc.mouseY()))){
            mc._app.removePopUpMenu();
            if(mc.mouseY() > mc._app.toolBar().getHeight()){
                for (Shape shape : mc._app.orderShapes().values())
                    if(shape.isMovable())
                        mc.add(shape);
            }
            else{
                if(mc.mouseX() > mc._app.toolBar().getTrashBinPosX() && mc.mouseX() - mc._app.toolBar().getTrashBinSizeX() / 2 < mc._app.toolBar().getTrashBinPosX() + mc._app.toolBar().getTrashBinSizeX() && mc.mouseY() / 2 > mc._app.toolBar().getTrashBinPosY() - mc._app.toolBar().getTrashBinSizeY() && mc.mouseY() / 2 < mc._app.toolBar().getTrashBinPosY() + mc._app.toolBar().getTrashBinSizeY() / 2){
                    TrashBinCommand tb = new TrashBinCommand(mc._app);
                    mc._app.update(tb);
                }else{
                    for (Shape s : mc._app.orderShapes().values())
                        if(s.isMovable() || s.selected()){
                            s.visiblePosition(s.position());
                            s.setMovable(false);
                        }
                }
            }
        }
    }

    @Override
    public void visit(TrashBinCommand mc) {
        if(mc._app.selection())
            for (Shape s : mc._app.getShapes()){
                System.out.println(s);
                if(s.isMovable() || s.selected())
                    mc.add(s);  
            }
    }

    @Override public void visit(MouseRightClickClickedCommand mc) {
        int selected = 0;
        boolean grouped = true;
        for (Shape shape : mc._app.orderShapes().values())
            if(shape.selected()){
                if(!shape.grouped())
                    grouped = false;
                selected ++;
            }
        mc._app.setPopUpMenu(new Point2D.Double(mc.mouseX(), mc.mouseY()), selected, grouped);
    }
    @Override public void visit(DragSelectedCommand mc) {}
    @Override public void visit(RectangleSelectedCommand mc) {
        for (Shape shape : mc._app.orderShapes().values())
            if(shape.selected())
                shape.selected(false);
        mc._app.selection(false);
    }

    @Override public void visit(RectPlaceCommand mc) { }
    @Override public void visit(RedoCommand mc) { }
    @Override public void visit(ShapeDragCommand mc) { }
    @Override public void visit(ShapeSelectCommand mc) { }
    @Override public void visit(ShapeTranslateCommand mc) { }
    @Override public void visit(UndoCommand mc) { }

    @Override
    public void visit(MouseShiftLeftClickClickedCommand mc) {
        for (Shape s : mc._app.orderShapes().values()){
            if(s.isInside(new Point2D.Double(mc.mouseX(), mc.mouseY()))){
                if(s.selected()){
                    s.selected(false);
                    boolean selected = false;
                    for (Shape shape : mc._app.getShapes()){
                        if(shape.selected())
                            selected = true;
                    }
                    if(!selected)
                        mc._app.selection(false);
                }
                else{
                    mc.add(s);
                    mc._app.selection(true);
                }
            }
        }
        
    }

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
