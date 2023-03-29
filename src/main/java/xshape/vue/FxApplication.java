package xshape.vue;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import xshape.controleur.FxApp;
import xshape.model.Command.Command;
import xshape.model.controlInput.InputControl;
import xshape.model.observer.IInputObservable;
import xshape.model.observer.IInputObserver;



public class FxApplication extends Application implements IInputObservable{
    public static Group _root = new Group();
    private FxApp _fxapp;
    private InputControl _inputControleur = new InputControl();

     
    @Override
    public void init(){
        _fxapp = new FxApp(_root, this);
        _fxapp.run();
    }
    

    @Override
    public void start(Stage primaryStage) throws Exception {
        Platform.runLater(() -> {
            primaryStage.setTitle("XShape JavaFx Rendering");
            ToolBar tb = (ToolBar) _fxapp.getToolBar();
            _root.getChildren().add(tb);
            Scene scene = new Scene(_root, 500, 500);
            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

                @Override
                public void handle(KeyEvent event) {
                    if(event.getCode() == KeyCode.CONTROL) {
                        _inputControleur.ctrlPressed(true);
                        _inputControleur.ctrlReleased(false);
                    }
                }
                
            });
            scene.setOnKeyReleased(new EventHandler<KeyEvent>() {

                @Override
                public void handle(KeyEvent event) {
                    if(event.getCode() == KeyCode.CONTROL){
                        _inputControleur.ctrlPressed(false);
                        _inputControleur.ctrlReleased(true);
                    } 
                }
                
            });
            scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    _inputControleur.moved(false);
                    if(e.isPrimaryButtonDown()) _inputControleur.leftPressed(true);
                    if(e.isSecondaryButtonDown()) _inputControleur.rightPressed(true);
                    notifyObservers(_inputControleur);
                    /*
                    if(e.isPrimaryButtonDown()){
                        _left_click_press = true;
                    }
                    if(e.isPrimaryButtonDown() && e.isControlDown()) {}
                    else if (e.isPrimaryButtonDown()){
                       notifyObservers(new MouseLeftClickPressedCommand(_fxapp, e.getX(), e.getY())); 
                    }
                    else if(e.isSecondaryButtonDown()) {
                        _right_click_press = true;
                    }
                    */
                }
            });/*
            scene.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    
                    if (!e.isPrimaryButtonDown() && _left_click_press){
                        _left_click_press = false;
                        notifyObservers(new MouseClickedCommand(_fxapp, e.getX(), e.getY()));
                    }
                    if (!e.isSecondaryButtonDown() && _right_click_press){
                        _right_click_press = false;
                         notifyObservers(new MouseRightClickClickedCommand(_fxapp, e.getX(), e.getY()));
                    }
                }
            });*/
            scene.addEventFilter(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    if(e.isPrimaryButtonDown()) _inputControleur.leftReleased(true);
                    if(e.isSecondaryButtonDown()) _inputControleur.rightReleased(true);
                    notifyObservers(_inputControleur);
                    _inputControleur.moved(false);
                    /*if(!e.isPrimaryButtonDown() && e.isControlDown() && _left_click_press) {
                        _left_click_press = false;
                        notifyObservers(new MouseShiftLeftClickClickedCommand(_fxapp, e.getX(), e.getY()));
                    }
                    else if (!e.isPrimaryButtonDown() && _left_click_press){
                        notifyObservers(new MouseReleasedCommand(_fxapp, e.getX(), e.getY()));
                        _left_click_press = false;
                    }*/
                }
            });
            scene.addEventFilter(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    _inputControleur.moved(true);
                    notifyObservers(_inputControleur);
                    /*
                    if (e.isPrimaryButtonDown()) notifyObservers(new MouseMovedCommand(_fxapp, e.getX(), e.getY()));
                    */
                }
            });/*
            scene.addEventFilter(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    if (e.isPrimaryButtonDown()) notifyObservers(new MouseDraggedCommand(_fxapp, e.getX(), e.getY()));
                }
            });
            scene.addEventFilter(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    if (e.isPrimaryButtonDown()) notifyObservers(new MouseEnteredCommand(_fxapp, e.getX(), e.getY()));
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
                    if (e.isPrimaryButtonDown()) notifyObservers(new MouseExitedCommand(_fxapp, e.getX(), e.getY()));
                }
            });

            scene.addEventFilter(MouseEvent.MOUSE_EXITED_TARGET, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                }
            });*/

            tb.prefWidthProperty().bind(scene.widthProperty().divide(100).multiply(xshape.model.shape.SystemToolBar.getVw()));
            tb.prefHeightProperty().bind(scene.heightProperty().divide(100).multiply(xshape.model.shape.SystemToolBar.getVh()));
            _fxapp.toolBar().setWidth(tb.getPrefWidth());
            _fxapp.toolBar().setHeight(tb.getPrefHeight());
            primaryStage.setScene(scene);
            primaryStage.show();
        });
    }

    @Override public void registerOberver(IInputObserver obs) { _fxapp = (FxApp) obs; }
    @Override public void unRegisterObserver(IInputObserver obs) { _fxapp = null; }
    @Override public void notifyObservers(Command command) { _fxapp.update(command); }
    @Override public void notifyObservers(InputControl mouse) { _fxapp.update(mouse); }

}