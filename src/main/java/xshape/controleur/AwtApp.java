package xshape.controleur;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Point2D;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import xshape.model.abstractFactory.ShapeFactory;
import xshape.model.abstractFactory.ShapeFactoryAwt;
import xshape.model.controlInput.InputControl;
import xshape.model.observer.IInputObservable;
import xshape.model.observer.IInputObserver;
import xshape.vue.AwtContext;
import xshape.controleur.AwtApp.JCanvas;

class GUIHelper {
    public static void showOnFrame(JComponent component, String frameName) {
        JFrame frame = new JFrame(frameName);
        WindowAdapter wa = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        JCanvas jc = (JCanvas) component;
        frame.addMouseListener(jc);
        frame.addMouseMotionListener(jc);
        frame.addWindowListener(wa);
        frame.getContentPane().add(component);
        frame.pack();
        frame.setVisible(true);
    }
}

public class AwtApp extends XShape{
    JCanvas _jc = null;

    class JCanvas extends JPanel implements MouseListener, MouseMotionListener, IInputObservable  {
        XShape _xshape = null;
        final InputControl  _inputControleur = new InputControl();
        public final Point2D _scene_size = new Point2D.Double(500, 500);
        public final Point2D _syst_tool_pos = new Point2D.Double(250,11);
        public final Point2D _syst_tool_size = new Point2D.Double(500,22);
        public final Point2D _shape_tool_pos = new Point2D.Double(30,261);
        public final Point2D _shape_tool_size = new Point2D.Double(60,478);

        public JCanvas(XShape xs) {
            registerOberver(xs);
            _xshape.systemToolBar(_xshape.factory().createSystemToolBar(_syst_tool_pos, _syst_tool_size, false));
            _xshape.shapesToolBar(_xshape.factory().createShapeToolBar(_shape_tool_pos, _shape_tool_size, false, null));
            _xshape.whiteBoard(_xshape.factory().createWhiteBoard(_scene_size.getX()/2 + _shape_tool_size.getX()/2, _scene_size.getY()/2 + _syst_tool_size.getY()/2, _scene_size.getY() - _syst_tool_size.getY(), _scene_size.getX() - _shape_tool_size.getX(), false));

        }

        public void paint(Graphics g) {
            super.paint(g);
            AwtContext.instance().graphics(g);
            _xshape.draw();
        }
        @Override public void registerOberver(IInputObserver obs) { _xshape = (XShape) obs; }
        @Override public void unRegisterObserver(IInputObserver obs) { _xshape = null; }
        @Override public void notifyObservers(InputControl mouse) { _xshape.update(mouse); }
        @Override public void mouseClicked(MouseEvent e) { }
        @Override public void mouseEntered(MouseEvent e) { }
        @Override public void mouseExited(MouseEvent e) { }
        @Override public void mouseMoved(MouseEvent e) { }
        @Override public void mousePressed(MouseEvent e) { 
            _inputControleur.position(e.getX(), e.getY() + 20);
            _inputControleur.moved(false);
            if(e.getButton() == MouseEvent.BUTTON1) {
                _inputControleur.leftPressed(true);
                _inputControleur.left().now(true);
            }
            if(e.getButton() == MouseEvent.BUTTON3) {
                _inputControleur.rightPressed(true);
                _inputControleur.right().now(true);
            }
            notifyObservers(_inputControleur);
            if(_inputControleur.ctrl().now()) _inputControleur.ctrl().now(false);
            _inputControleur.left().now(false);
            _inputControleur.right().now(false);
        }
        @Override public void mouseReleased(MouseEvent e) {
            _inputControleur.position(e.getX(), e.getY() + 20);
            if(e.getButton() == MouseEvent.BUTTON1) {
                _inputControleur.leftReleased(true);
                _inputControleur.leftPressed(false);
                _inputControleur.left().now(true);
            }
            if(e.getButton() == MouseEvent.BUTTON3) {
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
        @Override public void mouseDragged(MouseEvent e) {
            _inputControleur.position(e.getX(), e.getY() + 20);
            _inputControleur.moved(true);
            notifyObservers(_inputControleur);
            if(_inputControleur.ctrl().now()) _inputControleur.ctrl().now(false);  
        }
    }

    @Override public void run() {
        createFactory();
        JCanvas jc = new JCanvas(this);
        _jc = jc;
        jc.setBackground(Color.WHITE);
        jc.setPreferredSize(new Dimension(500, 500));
        GUIHelper.showOnFrame(jc, "XShape Swing/AWT Rendering");
    }

    @Override public void createFactory() {_factory = new ShapeFactoryAwt(); }
    @Override public void render() { _jc.repaint(); }
}
