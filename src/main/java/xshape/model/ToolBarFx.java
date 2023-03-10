package xshape.model;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;

public class ToolBarFx implements xshape.model.ToolBar{
    javafx.scene.control.ToolBar _adapted;

    public ToolBarFx(){
        Label label = new Label("Toolbar");
        _adapted = new javafx.scene.control.ToolBar(label);
        Group group = new Group(rectangleButton());

        _adapted.getItems().add(group);
        _adapted.setOrientation(Orientation.VERTICAL);

    }

    @Override
    public void draw() {
    }

    @Override
    public Button rectangleButton() {
        return new Button("Rectangle");
    }

    @Override
    public ArrayList<Node> buttons() {
        ArrayList<Node> buttons =  new ArrayList<>();
        buttons.add(rectangleButton());
        return buttons();
    }

    public ToolBar toolBar(){
        return _adapted;
    }
    
}
