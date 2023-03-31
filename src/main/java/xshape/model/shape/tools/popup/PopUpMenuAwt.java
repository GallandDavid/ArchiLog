package xshape.model.shape.tools.popup;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import xshape.model.shape.tools.menus.Menu;
import xshape.model.shape.tools.menus.MenuAwt;
import xshape.vue.AwtContext;

public class PopUpMenuAwt extends PopUpMenu{

    public PopUpMenuAwt(Point2D pos, int selected, boolean grouped, Menu edit, Menu group, Menu ungroup) {
        super(pos, selected, grouped, 
                new MenuAwt("Edit", new Point2D.Double(pos.getX(), pos.getY() - (_size.getY() / 3)), new Point2D.Double(_size.getX(), _size.getY() / 3), grouped),
                new MenuAwt("Group", pos, new Point2D.Double(_size.getX(), _size.getY() / 3), grouped),
                new MenuAwt("Un-Group", new Point2D.Double(pos.getX(), pos.getY() + (_size.getY() / 3)), new Point2D.Double(_size.getX(), _size.getY() / 3), grouped)
                );
    }

    @Override
    public void draw() {
        edit().draw();
        group().draw();
        ungroup().draw();
        Graphics2D g = (Graphics2D) AwtContext.instance().graphics();
		Point2D pos = visiblePosition();
		Point2D size = visibleSize();
        g.setColor(Color.GRAY);
        g.fillRect((int)(pos.getX() - size.getX()/2),
        (int)(pos.getY() - size.getY()/2),        
        (int)(size.getX()),
        (int)(size.getY()));
    }

    @Override public void remove() {}
	@Override public Object adapted() { return null;}

    
}
