package xshape.controleur;

import java.awt.*;
import javax.swing.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import xshape.model.abstractFactory.ShapeFactory;
import xshape.model.abstractFactory.ShapeFactoryAwt;
import xshape.model.observer.Iobservable;
import xshape.model.observer.Iobserver;
import xshape.model.toolbar.ToolBar;
import xshape.model.toolbar.ToolBarAwt;
import xshape.vue.AwtContext;
import xshape.model.Builder.ToolBarDirector;
import xshape.model.Command.Command;
import xshape.model.Command.MouseClickedCommand;
import xshape.model.Command.MouseDraggedCommand;
import xshape.model.Command.MouseEnteredCommand;
import xshape.model.Command.MouseExitedCommand;
import xshape.model.Command.MouseMovedCommand;
import xshape.model.Command.MousePressedCommand;
import xshape.model.Command.MouseReleasedCommand;
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
        frame.setJMenuBar((JMenuBar) ((ToolBarDirector) jc._xshape).getToolBar());

        frame.addWindowListener(wa);
        frame.getContentPane().add(component);
        frame.pack();
        frame.setVisible(true);
    }
}

public class AwtApp extends XShape implements ToolBarDirector{
    protected ToolBar _toolBar = new ToolBarAwt(this);
    JCanvas _jc = null;
    class JCanvas extends JPanel implements MouseListener, MouseMotionListener, Iobservable  {
        XShape _xshape = null;

        public JCanvas(XShape xs) {
            registerOberver(xs);
            ToolBarDirector tb = (ToolBarDirector) _xshape;
            tb.createToolBar();
        }

        public void paint(Graphics g) {
            super.paint(g);
            AwtContext.instance().graphics(g);
            _xshape.draw();
        }

        @Override public void mouseClicked(MouseEvent e) { notifyObservers(new MouseClickedCommand(_xshape, e.getX(), e.getY())); }
        @Override public void mouseEntered(MouseEvent e) { notifyObservers(new MouseEnteredCommand(_xshape, e.getX(), e.getY())); }
        @Override public void mouseExited(MouseEvent e) { notifyObservers(new MouseExitedCommand(_xshape, e.getX(), e.getY())); }
        @Override public void mousePressed(MouseEvent e) { notifyObservers(new MousePressedCommand(_xshape, e.getX(), e.getY())); }
        @Override public void mouseReleased(MouseEvent e) { notifyObservers(new MouseReleasedCommand(_xshape, e.getX(), e.getY())); }
        @Override public void mouseDragged(MouseEvent e) { notifyObservers(new MouseDraggedCommand(_xshape, e.getX(), e.getY())); }
        @Override public void mouseMoved(MouseEvent e) { notifyObservers(new MouseMovedCommand(_xshape, e.getX(), e.getY())); }

        @Override public void registerOberver(Iobserver obs) { _xshape = (XShape) obs; }
        @Override public void unRegisterObserver(Iobserver obs) { _xshape = null; }
        @Override public void notifyObservers(Command command) { _xshape.update(command); }
    }

    @Override protected ShapeFactory createFactory() { return new ShapeFactoryAwt(); }

    @Override
    public void run() {
        JCanvas jc = new JCanvas(this);
        _jc = jc;
        jc.setBackground(Color.WHITE);
        jc.setPreferredSize(new Dimension(500, 500));
        GUIHelper.showOnFrame(jc, "XShape Swing/AWT Rendering");
    }

    @Override
    public void createToolBar() { _toolBar.makeProduct(); }

    @Override
    public Object getToolBar() {
        return _toolBar.getProduct();
    }
    @Override
    public ToolBar toolBar() {
        return _toolBar;
    }

    @Override
    void render() {
        _jc.repaint();
    }
}
