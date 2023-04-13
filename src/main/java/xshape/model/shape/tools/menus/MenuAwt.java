package xshape.model.shape.tools.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import xshape.vue.AwtContext;

public class MenuAwt extends Menu{

    public MenuAwt(String title, Point2D pos, Point2D size, boolean selected) {
        super(title, pos, size, selected);
    }

    @Override
    public void draw() {
		Point2D pos = visiblePosition();
		Point2D size = visibleSize();

        
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
		g.drawString(title(), (int)((pos.getX()- size.getX()/2) + 5), (int)(size.getY() - 5));
        
    } 
    @Override
    public Object adapted() {
        return null;
    }
}
