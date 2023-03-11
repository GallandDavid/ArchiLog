package xshape.vue;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class FxApplication extends Application {
    public static Group _root;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("XShape JavaFx Rendering");
        
        _root = new Group(new ToolBar(new Button(getParameters().getRaw().get(0))).getItems().addListener((ListChangeListener<Node>) change -> {
    System.out.println("Toolbar items changed: " + change.getList());
}););
        Scene _scene = new Scene(_root, 500, 500);
        primaryStage.setScene(_scene);
        primaryStage.show();
    }
}