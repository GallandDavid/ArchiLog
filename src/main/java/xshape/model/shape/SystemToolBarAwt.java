package xshape.model.shape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import xshape.vue.AwtContext;

public class SystemToolBarAwt extends SystemToolBar{

    public SystemToolBarAwt(Point2D pos, Point2D size, boolean selected) {
        super(pos, size, selected,
                new MenuAwt("Files", pos, size, false),
                new MenuAwt("Edit", pos, size, false),
                new MenuAwt("Bin", pos, size, false));
    }

    @Override
    public void draw() {
        Graphics2D g = (Graphics2D) AwtContext.instance().graphics();
		Point2D pos = visiblePosition();
		Point2D size = visibleSize();
        g.setColor(Color.GRAY);
        g.fillRect((int)(pos.getX() - size.getX()/2),
        (int)(pos.getY() - size.getY()/2),        
        (int)(size.getX()),
        (int)(size.getY()));
        files().draw();
        edit().draw();
        trashbin().draw();
    }

    @Override public void remove() { }
    @Override public boolean isInside(Point2D pos){ return pos.getX() > position().getX() - size().getX() / 2 && pos.getX() < position().getX() + size().getX() / 2 && pos.getY() - 8 > position().getY() && pos.getY() - 8 <= position().getY() + size().getY();}
    @Override public Object adapted() { return null; }
    
}
