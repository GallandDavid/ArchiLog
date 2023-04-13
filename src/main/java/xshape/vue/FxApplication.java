package xshape.vue;

import java.awt.geom.Point2D;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import xshape.controleur.FxApp;
import xshape.model.controlInput.InputControl;
import xshape.model.observer.IInputObservable;
import xshape.model.observer.IInputObserver;



public class FxApplication extends Application implements IInputObservable{
    
    public static Group _root = new Group();
    public double scene_width = 500;
    public double scene_height = 500;
    Canvas _canvas;
    private FxApp _fxapp;
    private InputControl _inputControleur = new InputControl();

    @Override public void registerOberver(IInputObserver obs) { _fxapp = (FxApp) obs; }
    @Override public void unRegisterObserver(IInputObserver obs) { _fxapp = null; }
    @Override public void notifyObservers(InputControl mouse) { _fxapp.update(mouse); }

    @Override
    public void init(){
        _fxapp = new FxApp(_root, this);
        _fxapp.run();
    }
    
    public Canvas canvas(){
        return _canvas;
    }

    public Canvas clearCanvas(){
        _root.getChildren().remove(canvas());
        _canvas = new Canvas(scene_width, scene_height);
        _root.getChildren().add(canvas());
        return canvas();
    }

    

    @Override
    public void start(Stage primaryStage) throws Exception {
        Platform.runLater(() -> {
            primaryStage.setTitle("XShape JavaFx Rendering");
            Scene scene = new Scene(_root, scene_width, scene_height);
            _canvas = new Canvas(scene_width, scene_height);
            _root.getChildren().add(_canvas);
            scene.setFill(Color.GRAY);
            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

                @Override
                public void handle(KeyEvent e) {
                    if(e.getCode() == KeyCode.CONTROL) {
                        _inputControleur.ctrl().now(true);
                        _inputControleur.ctrlPressed(true);
                        _inputControleur.ctrlReleased(false);
                    }
                }
            });
            scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent e) {
                    if(e.getCode() == KeyCode.CONTROL){
                        _inputControleur.ctrl().now(true);
                        _inputControleur.ctrlPressed(false);
                        _inputControleur.ctrlReleased(true);
                    } 
                }
            });
            scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    _inputControleur.position(e.getX(), e.getY());
                    _inputControleur.moved(false);
                    if(e.getButton() == MouseButton.PRIMARY) {
                        _inputControleur.leftPressed(true);
                        _inputControleur.left().now(true);
                    }
                    if(e.getButton() == MouseButton.SECONDARY) {
                        _inputControleur.rightPressed(true);
                        _inputControleur.right().now(true);
                    }
                    notifyObservers(_inputControleur);
                    if(_inputControleur.ctrl().now()) _inputControleur.ctrl().now(false);
                    _inputControleur.left().now(false);
                    _inputControleur.right().now(false);
                }
            });
            scene.addEventFilter(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    _inputControleur.position(e.getX(), e.getY());
                    if(e.getButton() == MouseButton.PRIMARY) {
                        _inputControleur.leftReleased(true);
                        _inputControleur.leftPressed(false);
                        _inputControleur.left().now(true);
                    }
                    if(e.getButton() == MouseButton.SECONDARY) {
                        _inputControleur.rightReleased(true);
                        _inputControleur.rightPressed(false);
                        _inputControleur.right().now(true);
                    }
                    notifyObservers(_inputControleur);
                    _inputControleur.leftReleased(false);
                    _inputControleur.rightReleased(false);
                    _inputControleur.moved(false);
                    if(_inputControleur.ctrl().now()) _inputControleur.ctrl().now(false);
                    _inputControleur.left().now(false);
                    _inputControleur.right().now(false);
                }
            });
            scene.addEventFilter(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    _inputControleur.position(e.getX(), e.getY());
                    _inputControleur.moved(true);
                    notifyObservers(_inputControleur);
                    if(_inputControleur.ctrl().now()) _inputControleur.ctrl().now(false);  
                }
            });
            primaryStage.setScene(scene);
            primaryStage.show();
        });
    }
}