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
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import xshape.model.abstractFactory.ShapeFactory;
import xshape.model.abstractFactory.ShapeFactoryAwt;
import xshape.model.controlInput.InputControl;
import xshape.model.observer.IInputObservable;
import xshape.model.observer.IInputObserver;
import xshape.model.shape.ShapeToolBarAwt;
import xshape.model.shape.SystemToolBar;
import xshape.model.shape.SystemToolBarAwt;
import xshape.vue.AwtContext;
import xshape.model.Command.Command;
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
        InputControl _mouse = new InputControl();
        public Point2D _syst_tool_pos = new Point2D.Double(250,20);
        public Point2D _syst_tool_size = new Point2D.Double(500,40);
        public Point2D _shape_tool_pos = new Point2D.Double(30,270);
        public Point2D _shape_tool_size = new Point2D.Double(60,460);

        public JCanvas(XShape xs) {
            registerOberver(xs);
            _xshape.systemToolBar(new SystemToolBarAwt(_syst_tool_pos, _syst_tool_size, false));
            _xshape.shapesToolBar(new ShapeToolBarAwt(_syst_tool_pos, _syst_tool_size, false, null));
            
        }

        public void paint(Graphics g) {
            super.paint(g);
            AwtContext.instance().graphics(g);
            _xshape.draw();
        }

        @Override public void mouseClicked(MouseEvent e) { }
        @Override public void mouseEntered(MouseEvent e) { }
        @Override public void mouseExited(MouseEvent e) { }
        @Override public void mousePressed(MouseEvent e) { 
            _mouse.moved(false);
            if(e.getButton() == MouseEvent.BUTTON1) _mouse.leftPressed(true);
            if(e.getButton() == MouseEvent.BUTTON3) _mouse.rightPressed(true);
            notifyObservers(_mouse);
        }
        @Override public void mouseReleased(MouseEvent e) {
            if(e.getButton() == MouseEvent.BUTTON1) _mouse.leftReleased(true);
            if(e.getButton() == MouseEvent.BUTTON3) _mouse.rightReleased(true);
            notifyObservers(_mouse);
            _mouse.moved(false);
        }
        @Override public void mouseDragged(MouseEvent e) { }
        @Override public void mouseMoved(MouseEvent e) { 
            _mouse.moved(true);
            notifyObservers(_mouse);
        }
        @Override public void registerOberver(IInputObserver obs) { _xshape = (XShape) obs; }
        @Override public void unRegisterObserver(IInputObserver obs) { _xshape = null; }

        @Override
        public void notifyObservers(InputControl mouse) {
            _xshape.update(mouse);
        }
    }

    @Override
    public void run() {
        JCanvas jc = new JCanvas(this);
        _jc = jc;
        jc.setBackground(Color.WHITE);
        jc.setPreferredSize(new Dimension(500, 500));
        GUIHelper.showOnFrame(jc, "XShape Swing/AWT Rendering");
    }

    @Override protected ShapeFactory createFactory() { return new ShapeFactoryAwt(); }
    @Override public void render() { _jc.repaint(); }
}
