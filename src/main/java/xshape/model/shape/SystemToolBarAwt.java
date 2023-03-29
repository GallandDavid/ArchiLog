package xshape.model.shape;

import java.awt.geom.Point2D;

public class SystemToolBarAwt extends SystemToolBar{

    public SystemToolBarAwt(String title, Point2D pos, Point2D size, boolean selected) {
        super(pos, size, selected,
                new MenuAwt("Files", pos, size, false),
                new MenuAwt("Edit", pos, size, false),
                new MenuAwt("", pos, size, false));
    }

    @Override
    public void draw() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    }

    @Override
    public void remove() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public boolean isInside(Point2D pos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isInside'");
    }

    @Override
    public Object adapted() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'adapted'");
    }
    
}
