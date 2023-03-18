package xshape.vue;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.stage.Stage;
import xshape.controleur.FxApp;

public class FxApplication extends Application   {
    public static Group _root;
    private ToolBar _toolBar;

    
    @Override
    public void init(){
        try {
            Class<?> clazz = Class.forName(getParameters().getRaw().get(0));
            Constructor<?> cons = clazz.getConstructor();
            FxApp toolBar = (FxApp) cons.newInstance();
            toolBar.createToolBar();
            _toolBar = (ToolBar) toolBar.getToolBar();
        } catch (ClassNotFoundException|
                NoSuchMethodException|
                SecurityException|
                InstantiationException|
                IllegalAccessException|
                InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("XShape JavaFx Rendering");

        _root = new Group(_toolBar);
        Scene _scene = new Scene(_root, 500, 500);
        primaryStage.setScene(_scene);
        primaryStage.show();
    }
}