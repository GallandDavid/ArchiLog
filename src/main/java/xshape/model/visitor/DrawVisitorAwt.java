package xshape.model.visitor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import xshape.model.shape.Group;
import xshape.model.shape.Rectangle;
import xshape.model.shape.Shape;
import xshape.model.shape.tools.Menu;
import xshape.model.shape.tools.PopUpMenu;
import xshape.model.shape.tools.WhiteBoard;
import xshape.model.shape.tools.toolbar.ShapeToolBar;
import xshape.model.shape.tools.toolbar.SystemToolBar;
import xshape.vue.AwtContext;

public class DrawVisitorAwt implements DrawVisitor{
    public void drawRectangle(Rectangle rect){
        Graphics2D g = (Graphics2D) AwtContext.instance().graphics();
		Point2D pos = rect.visiblePosition();
		Point2D size = rect.visibleSize();
        
		if(rect.selected()) g.setColor(new Color(100, 100, 255));
		else g.setColor(Color.BLUE);
        g.fillRect((int)(pos.getX() - size.getX()/2),
        (int)(pos.getY() - size.getY()/2),        
        (int)(size.getX()),
        (int)(size.getY()));
    }

    public void drawGroup(Group grp){
        for (Shape shape : grp.group())
            shape.accept(this);
    }

    public void drawWhiteBoard(WhiteBoard wtb){
        Graphics g = AwtContext.instance().graphics();
		Point2D pos = wtb.position();
		Point2D size = wtb.size();
        g.setColor(Color.WHITE);
        g.fillRect((int)(pos.getX() - size.getX()/2),
        (int)(pos.getY() - size.getY()/2),        
        (int)(size.getX()),
        (int)(size.getY()));
    }

    public void drawShapeToolBar(ShapeToolBar stb){
        Graphics2D g = (Graphics2D) AwtContext.instance().graphics();
		Point2D pos = stb.position();
		Point2D size = stb.size();
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect((int)(pos.getX() - size.getX()/2),
        (int)(pos.getY() - size.getY()/2),        
        (int)(size.getX()),
        (int)(size.getY()));
        for (int i = 0; i < stb.addons().size(); i ++) {
            stb.addons().get(i).accept(this);
            Graphics2D g2 = (Graphics2D) AwtContext.instance().graphics();
            Point2D p = stb.addons().get(i).visiblePosition();
            Point2D	s = stb.addons().get(i).visibleSize();
            g2.setColor(Color.GRAY);
            g2.fillRect((int)(p.getX()- ((s.getX()/2) + 3)),
            (int)(p.getY()- ((s.getY()/2) + 3)),        
            (int)(s.getX() + 6),
            (int)(s.getY() + 6));
        }

        stb.rect().accept(this);
        
    }

    public void drawSystemToolBar(SystemToolBar stb){
        Graphics g = AwtContext.instance().graphics();
		Point2D pos = stb.position();
		Point2D size = stb.size();
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect((int)(pos.getX() - size.getX()/2),
        (int)(pos.getY() - size.getY()/2),        
        (int)(size.getX()),
        (int)(size.getY()));
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

    public void drawMenu(Menu menu){
        Point2D pos = menu.position();
		Point2D size = menu.size();

        
        Graphics2D g = (Graphics2D) AwtContext.instance().graphics();
        g.setColor(Color.GRAY);
        g.fillRect((int)(pos.getX() - size.getX()/2),
        (int)(pos.getY() - size.getY()/2),        
        (int)(size.getX()),
        (int)(size.getY()));
        g.setColor(Color.BLACK);
        g.drawRect((int)(pos.getX() - size.getX()/2),
        (int)(pos.getY() - size.getY()/2 - 0.5),        
        (int)(size.getX()),
        (int)(size.getY()) - 1);

        g.setFont(new Font("Arial", Font.PLAIN, 13));
		g.drawString(menu.title(), (int)((pos.getX()- size.getX()/2) + 3), (int)(pos.getY() + 6));
    }

    public void drawPopUpMenu(PopUpMenu pum){
        Graphics2D g = (Graphics2D) AwtContext.instance().graphics();
		Point2D pos = pum.position();
		Point2D size = pum.size();
        g.setColor(Color.GRAY);
        g.fillRect((int)(pos.getX() - size.getX()/2),
        (int)(pos.getY() - size.getY()/2),        
        (int)(size.getX()),
        (int)(size.getY()));
        pum.edit().accept(this);
        if(pum.nbSelected() > 1) pum.group().accept(this);
        if(pum.grouped()) pum.ungroup().accept(this);
    }
}
