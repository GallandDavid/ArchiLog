package xshape.vue;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import xshape.controleur.FxApp;
import xshape.model.Command.Command;
import xshape.model.Command.MouseClickedCommand;
import xshape.model.Command.MouseDraggedCommand;
import xshape.model.Command.MouseEnteredCommand;
import xshape.model.Command.MouseExitedCommand;
import xshape.model.Command.MouseMovedCommand;
import xshape.model.Command.MousePressedCommand;
import xshape.model.Command.MouseReleasedCommand;
import xshape.model.observer.Iobservable;
import xshape.model.observer.Iobserver;

public class FxApplication extends Application implements Iobservable{
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
            scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    notifyObservers(new MousePressedCommand(_fxapp, e.getX(), e.getY()));
                }
            });
            scene.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    notifyObservers(new MouseClickedCommand(_fxapp, e.getX(), e.getY()));
                }
            });
            scene.addEventFilter(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    notifyObservers(new MouseReleasedCommand(_fxapp, e.getX(), e.getY()));
                }
            });
            scene.addEventFilter(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    notifyObservers(new MouseMovedCommand(_fxapp, e.getX(), e.getY()));
                }
            });
            scene.addEventFilter(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    notifyObservers(new MouseDraggedCommand(_fxapp, e.getX(), e.getY()));
                }
            });
            scene.addEventFilter(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    notifyObservers(new MouseEnteredCommand(_fxapp, e.getX(), e.getY()));
                }
            });
            scene.addEventFilter(MouseEvent.MOUSE_ENTERED_TARGET, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                }
            });

            scene.addEventFilter(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    notifyObservers(new MouseExitedCommand(_fxapp, e.getX(), e.getY()));
                }
            });

            scene.addEventFilter(MouseEvent.MOUSE_EXITED_TARGET, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                }
            });

            tb.prefWidthProperty().bind(scene.widthProperty().divide(100).multiply(xshape.model.toolbar.ToolBar.getVw()));
            tb.prefHeightProperty().bind(scene.heightProperty().divide(100).multiply(xshape.model.toolbar.ToolBar.getVh()));
            _fxapp._toolbar.setWidth(tb.getPrefWidth());
            _fxapp._toolbar.setHeight(tb.getPrefHeight());
            primaryStage.setScene(scene);
            primaryStage.show();
        });
    }

    @Override public void registerOberver(Iobserver obs) { _fxapp = (FxApp) obs; }
    @Override public void unRegisterObserver(Iobserver obs) { _fxapp = null; }
    @Override public void notifyObservers(Command command) { _fxapp.update(command); }
}