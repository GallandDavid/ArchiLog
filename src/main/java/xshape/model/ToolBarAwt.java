package xshape.model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Button;

public class ToolBarAwt implements ToolBar{

    public ToolBarAwt(){
        
    }

    @Override
    public void draw() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    }

    @Override
    public Button rectangleButton() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'rectangleButton'");
    }

    @Override
    public ArrayList<Button> buttons() {
        ArrayList<Button> buttons =  new ArrayList<>();
        buttons.add(rectangleButton());
        return buttons();
    }
    
}
