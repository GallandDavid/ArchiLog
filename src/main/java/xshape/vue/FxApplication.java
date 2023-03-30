package xshape.vue;

import java.awt.geom.Point2D;

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
import xshape.model.shape.ShapeToolBarFx;
import xshape.model.shape.SystemToolBarFx;



public class FxApplication extends Application implements IInputObservable{
    public Point2D _scene_size = new Point2D.Double(500, 500);
    public Point2D _syst_tool_pos = new Point2D.Double(250,11);
    public Point2D _syst_tool_size = new Point2D.Double(500,22);
    public Point2D _shape_tool_pos = new Point2D.Double(30,261);
    public Point2D _shape_tool_size = new Point2D.Double(60,478);
    public static Group _root = new Group();
    private FxApp _fxapp;
    private InputControl _inputControleur = new InputControl();

     
    @Override
    public void init(){
        _fxapp = new FxApp(_root, this);
        _fxapp.systemToolBar(new SystemToolBarFx(_syst_tool_pos, _syst_tool_size, false, _root));
        _fxapp.shapesToolBar(new ShapeToolBarFx(_shape_tool_pos, _shape_tool_size, false, null, _root));
        _fxapp.run();
    }
    

    @Override
    public void start(Stage primaryStage) throws Exception {
        Platform.runLater(() -> {
            primaryStage.setTitle("XShape JavaFx Rendering");
            SystemToolBarFx tb =  (SystemToolBarFx) _fxapp.systemToolBar();
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
                }
            });
            scene.addEventFilter(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    if(e.isPrimaryButtonDown()) _inputControleur.leftReleased(true);
                    if(e.isSecondaryButtonDown()) _inputControleur.rightReleased(true);
                    notifyObservers(_inputControleur);
                    _inputControleur.moved(false);
                }
            });
            scene.addEventFilter(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    _inputControleur.moved(true);
                    notifyObservers(_inputControleur);
                    }
            });
            primaryStage.setScene(scene);
            primaryStage.show();
        });
    }

    @Override public void registerOberver(IInputObserver obs) { _fxapp = (FxApp) obs; }
    @Override public void unRegisterObserver(IInputObserver obs) { _fxapp = null; }
    @Override public void notifyObservers(InputControl mouse) { _fxapp.update(mouse); }

}