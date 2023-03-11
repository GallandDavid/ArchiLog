package xshape.model;

import java.awt.geom.Point2D;

public class ToolBarAwt extends ToolBar{

    public ToolBarAwt(double posX, double posY, double height, double width, Button rect_button) {
		super(new Point2D.Double(posX, posY),new Point2D.Double(width, height), rect_button);
    }

    public void draw() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    }
    
}
