package xshape.vue;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.stage.Stage;
import xshape.model.ToolBarFx;

public class FxApplication extends Application {
    ToolBar _toolbar = new ToolBarFx().toolBar();
    public static Group _root;

    public ToolBar toolbar(ToolBar toolbar){
        _toolbar = toolbar;
        return _toolbar;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("XShape JavaFx Rendering");
        
        _root = new Group(_toolbar);
        Scene _scene = new Scene(_root, 500, 500);
        primaryStage.setScene(_scene);
        primaryStage.show();
    }
}