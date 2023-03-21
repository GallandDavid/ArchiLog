package xshape.vue;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.stage.Stage;
import xshape.controleur.FxApp;

public class FxApplication extends Application   {
    public static Group _root = new Group();
    private FxApp _fxapp;

     
    @Override
    public void init(){
        _fxapp = new FxApp(_root);
        _fxapp.run();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Platform.runLater(() -> {
            primaryStage.setTitle("XShape JavaFx Rendering");
            ToolBar tb = (ToolBar) _fxapp.getToolBar();
            _root.getChildren().add(tb);
            Scene scene = new Scene(_root, 500, 500);

            tb.prefWidthProperty().bind(scene.widthProperty().divide(100).multiply(xshape.model.ToolBar.getVw()));
            tb.prefHeightProperty().bind(scene.heightProperty().divide(100).multiply(xshape.model.ToolBar.getVh()));
            _fxapp._toolbar.setWidth(tb.getPrefWidth());
            _fxapp._toolbar.setHeight(tb.getPrefHeight());
            primaryStage.setScene(scene);
            primaryStage.show();
        });
    }
}