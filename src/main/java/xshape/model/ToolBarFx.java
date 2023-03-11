package xshape.model;
import xshape.model.Button;

import java.awt.geom.Point2D;
import javafx.scene.Group;

public class ToolBarFx extends ToolBar{
    javafx.scene.control.ToolBar _adapted;

    public ToolBarFx(double posX, double posY, double height, double width, Button rect_button) {
		super(new Point2D.Double(posX, posY),new Point2D.Double(width, height), rect_button);
    }
    
    public javafx.scene.control.ToolBar draw() {
        this._adapted = new javafx.scene.control.ToolBar(new javafx.scene.control.Button(super.rectButton().title()));
       return _adapted;
    }
    
}
