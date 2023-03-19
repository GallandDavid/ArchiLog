package xshape.vue;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.stage.Stage;
import xshape.controleur.FxApp;

public class FxApplication extends Application   {
    public static Group _root = new Group();
    private FxApp fxapp = new FxApp(_root);

     
    @Override
    public void init(){
        fxapp.run();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Platform.runLater(() -> {
            primaryStage.setTitle("XShape JavaFx Rendering");
            _root.getChildren().add((ToolBar) fxapp.getToolBar());
            Scene _scene = new Scene(_root, 500, 500);
            primaryStage.setScene(_scene);
            primaryStage.show();
        });
    }
}