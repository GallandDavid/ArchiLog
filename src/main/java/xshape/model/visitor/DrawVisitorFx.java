package xshape.model.visitor;

import java.awt.geom.Point2D;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import xshape.model.shape.Group;
import xshape.model.shape.Rectangle;
import xshape.model.shape.Shape;
import xshape.model.shape.tools.Menu;
import xshape.model.shape.tools.PopUpMenu;
import xshape.model.shape.tools.WhiteBoard;
import xshape.model.shape.tools.toolbar.ShapeToolBar;
import xshape.model.shape.tools.toolbar.SystemToolBar;

public class DrawVisitorFx implements DrawVisitor{
    Canvas _cvs = null;

    public DrawVisitorFx(){}

    public void canvas(Canvas cvs){
        _cvs = cvs;
    }

    public void drawGroup(Group grp){
        for (Shape shape : grp.group())
            shape.accept(this);
    }

    @Override
    public void drawRectangle(Rectangle rect) {
        Point2D p = rect.position();
		Point2D	s = rect.size();
        GraphicsContext gc = _cvs.getGraphicsContext2D();
        if(rect.selected()) gc.setFill(Color.color(0.40, 0.4, 1.0));
		else gc.setFill(Color.BLUE);
        gc.fillRect(p.getX()- s.getX()/2,
                    p.getY()- s.getY()/2,
                    s.getX(),
                    s.getY());
    }

    @Override
    public void drawWhiteBoard(WhiteBoard wtb) {
        Point2D p = wtb.position();
		Point2D	s = wtb.size();
        GraphicsContext gc = _cvs.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(p.getX()- s.getX()/2,
                    p.getY()- s.getY()/2,
                    s.getX(),
                    s.getY());
    }

    @Override
    public void drawShapeToolBar(ShapeToolBar stb) {
        Point2D p = stb.position();
		Point2D	s = stb.size();
        GraphicsContext gc = _cvs.getGraphicsContext2D();
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(p.getX()- s.getX()/2,
                    p.getY()- s.getY()/2,
                    s.getX(),
                    s.getY());
        
        for (int i = 0; i < stb.addons().size(); i ++) {
            stb.addons().get(i).accept(this);
        }
        stb.rect().accept(this);
    }

    @Override
    public void drawSystemToolBar(SystemToolBar stb) {
        Point2D p = stb.position();
		Point2D	s = stb.size();
        GraphicsContext gc = _cvs.getGraphicsContext2D();
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(p.getX()- s.getX()/2,
                    p.getY()- s.getY()/2,
                    s.getX(),
                    s.getY());
        stb.files().accept(this);
        stb.edit().accept(this);
        stb.trashbin().accept(this);
        if(stb.filesSelected()){
            stb.load().accept(this);
            stb.save().accept(this);
        }
        if(stb.editSelected()){
            stb.undo().accept(this);
            stb.redo().accept(this);
        }
    }

    @Override
    public void drawMenu(Menu menu) {
        Point2D p = menu.position();
		Point2D	s = menu.size();
        GraphicsContext gc = _cvs.getGraphicsContext2D();
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(p.getX()- s.getX()/2,
                    p.getY()- s.getY()/2,
                    s.getX(),
                    s.getY());
        gc.setStroke(Color.BLACK);
        gc.strokeRect(p.getX()- s.getX()/2,
                    p.getY()- s.getY()/2,
                    s.getX(),
                    s.getY());
        gc.setFill(Color.BLACK);
        gc.setFont(Font.font("Arial", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 13));
        gc.fillText(menu.title(), (p.getX()- s.getX()/2) + 5, p.getY() + 6);
    }

    @Override
    public void drawPopUpMenu(PopUpMenu pum) {
        Point2D p = pum.position();
		Point2D	s = pum.size();
        GraphicsContext gc = _cvs.getGraphicsContext2D();
        gc.setFill(Color.GRAY);
        gc.fillRect(p.getX()- s.getX()/2,
                    p.getY()- s.getY()/2,
                    s.getX(),
                    s.getY());
        pum.edit().accept(this);
        if(pum.nbSelected() > 1) pum.group().accept(this);
        if(pum.grouped()) pum.ungroup().accept(this);
    }
}
