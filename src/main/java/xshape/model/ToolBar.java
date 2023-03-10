package xshape.model;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Button;

public interface ToolBar{
    Button rectangleButton();
    ArrayList<?> buttons();
	void draw();
}
