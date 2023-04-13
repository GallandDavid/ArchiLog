package xshape.controleur;

import xshape.model.abstractFactory.*;
import xshape.vue.FxApplication;

import java.awt.geom.Point2D;

import javafx.scene.Group;
import javafx.scene.control.MenuBar;

public class FxApp extends XShape  {
    public Point2D _scene_size = new Point2D.Double(500, 500);
    public Point2D _syst_tool_pos = new Point2D.Double(250,11);
    public Point2D _syst_tool_size = new Point2D.Double(500,22);
    public Point2D _shape_tool_pos = new Point2D.Double(30,261);
    public Point2D _shape_tool_size = new Point2D.Double(60,478);
    Group _root;
    MenuBar _popup = null;
    FxApplication _fx = null;

    public FxApp(Group root, FxApplication fx){
        _root = root;
        createFactory();
    }
    
    @Override public void createFactory() { _factory = new ShapeFactoryFx(_root); }
    @Override public void run() { 
        systemToolBar(factory().createSystemToolBar(_syst_tool_pos, _syst_tool_size, false));
        shapesToolBar(factory().createShapeToolBar(_shape_tool_pos, _shape_tool_size, false, null));
        whiteBoard(factory().createWhiteBoard(_scene_size.getX()/2 + _shape_tool_size.getX()/2, _scene_size.getY()/2 + _syst_tool_size.getY()/2, _scene_size.getY() - _syst_tool_size.getY(), _scene_size.getX() - _shape_tool_size.getX(), false));
        draw(); }
    @Override public void render() { }
}