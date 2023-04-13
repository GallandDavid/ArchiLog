package xshape.model.shape.tools.toolbar.systemtb;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;

import xshape.model.shape.tools.menus.MenuAwt;
import xshape.vue.AwtContext;

public class SystemToolBarAwt extends SystemToolBar{
    private static double _width = 60;

    public SystemToolBarAwt(Point2D pos, Point2D size, boolean selected) {
        super(pos, size, selected,
        new MenuAwt("Files", new Point2D.Double(_width / 2, size.getY() / 2), new Point2D.Double(_width, size.getY()), false),
        new MenuAwt("Save", new Point2D.Double(_width / 2, (size.getY() / 2) + (size.getY())), new Point2D.Double(_width, size.getY()), false),
        new MenuAwt("Load", new Point2D.Double(_width / 2, (size.getY() / 2) + (size.getY() * 2)), new Point2D.Double(_width, size.getY()), false),
        new MenuAwt("Edit", new Point2D.Double(_width + (_width / 2), size.getY() / 2), new Point2D.Double(_width, size.getY()), false),
        new MenuAwt("Undo", new Point2D.Double(_width / 2, (size.getY() / 2) + size.getY()), new Point2D.Double(_width, size.getY()), false),
        new MenuAwt("Redo", new Point2D.Double(_width / 2, (size.getY() / 2) + (size.getY() * 2)), new Point2D.Double(_width, size.getY()), false),
        new MenuAwt("Bin", new Point2D.Double(2 * _width + (_width / 2), size.getY() / 2), new Point2D.Double(_width, size.getY()), false));
}

    @Override
    public void draw() {
        Graphics g = AwtContext.instance().graphics();
		Point2D pos = position();
		Point2D size = size();
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect((int)(pos.getX() - size.getX()/2),
        (int)(pos.getY() - size.getY()/2),        
        (int)(size.getX()),
        (int)(size.getY()));
        files().draw();
        edit().draw();
        trashbin().draw();
        if(filesSelected()){
            load().draw();
            save().draw();
        }
        if(editSelected()){
            undo().draw();
            redo().draw();
        }
    }
    @Override public Object adapted() { return null; }
}
