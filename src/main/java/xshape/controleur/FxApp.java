package xshape.controleur;

import xshape.model.shape.tools.WhiteBoard;
import xshape.model.shape.tools.toolbar.ShapeToolBar;
import xshape.model.shape.tools.toolbar.SystemToolBar;
import xshape.model.visitor.DrawVisitorFx;
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
        _fx = fx;
        _root = root;
        createDrawer();
    }
    
    @Override public void render() { }
    @Override public void clear() { ((DrawVisitorFx)_drawer).canvas(_fx.clearCanvas());}
    @Override public void run() { 
        systemToolBar(new SystemToolBar(_syst_tool_pos, _syst_tool_size, false));
        shapesToolBar(new ShapeToolBar(_shape_tool_pos, _shape_tool_size, false, null));
        whiteBoard(new WhiteBoard(_scene_size.getX()/2 + _shape_tool_size.getX()/2, _scene_size.getY()/2 + _syst_tool_size.getY()/2, _scene_size.getY() - _syst_tool_size.getY(), _scene_size.getX() - _shape_tool_size.getX(), false));
        draw(); }

    @Override
    public void createDrawer() {
        _drawer = new DrawVisitorFx();
    }
}