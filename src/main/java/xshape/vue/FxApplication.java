package xshape.vue;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.stage.Stage;
import xshape.model.ToolBarFx;
import xshape.model.Builder.ToolBarDirector;

public class FxApplication extends Application implements ToolBarDirector {
    public static Group _root;
    private xshape.model.ToolBar _toolBar = new ToolBarFx();

    @Override
    public void init() {
        createToolBar();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("XShape JavaFx Rendering");

        _root = new Group((ToolBar) _toolBar.getProduct());
        Scene _scene = new Scene(_root, 500, 500);
        primaryStage.setScene(_scene);
        primaryStage.show();
    }

    @Override
    public void createToolBar() {
        _toolBar.makeProduct();
    }
}