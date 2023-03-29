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
import xshape.model.shape.SystemToolBar;
import xshape.vue.AwtContext;
import xshape.model.Builder.menu.toolbar.ToolBarAwt;
import xshape.model.Builder.menu.toolbar.ToolBarDirector;
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
        JMenuBar jb = (JMenuBar) ((ToolBarDirector) jc._xshape).getToolBar();
        jb.setSize(new Dimension((int)(jc.getPreferredSize().getWidth()/100*SystemToolBar.getVw()), (int) component.getPreferredSize().getWidth()/100*SystemToolBar.getVh()));
        frame.setJMenuBar(jb);
        jc._xshape.toolBar().setWidth(jb.getPreferredSize().getWidth());
        jc._xshape.toolBar().setHeight(jb.getPreferredSize().getHeight() + 40);
        

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

        @Override public void mouseClicked(MouseEvent e) {/* 
            if ((e.getButton() == MouseEvent.BUTTON1) && _left_click_press) {
                _left_click_press = false;
                notifyObservers(new MouseClickedCommand(_xshape, e.getX(), e.getY()));
            }
            if ((e.getButton() == MouseEvent.BUTTON3) && _right_click_press) {
                _right_click_press = false;
                notifyObservers(new MouseRightClickClickedCommand(_xshape, e.getX(), e.getY()));
            }
        */}
    @Override public void mouseEntered(MouseEvent e) {/* if((e.getButton() == MouseEvent.BUTTON1)) notifyObservers(new MouseEnteredCommand(_xshape, e.getX(), e.getY())); */}
@Override public void mouseExited(MouseEvent e) {/* if((e.getButton() == MouseEvent.BUTTON1)) notifyObservers(new MouseExitedCommand(_xshape, e.getX(), e.getY())); */}
        @Override public void mousePressed(MouseEvent e) { /* 
            if(e.getButton() == MouseEvent.BUTTON1){
                _left_click_press = true;
            }
            if(e.getButton() == MouseEvent.BUTTON1 && e.isControlDown()) {}
            else if (e.getButton() == MouseEvent.BUTTON1){ 
                notifyObservers(new MouseLeftClickPressedCommand(_xshape, e.getX(), e.getY())); 
            }
            if(e.getButton() == MouseEvent.BUTTON3){
                _right_click_press = true;
            }*/

            _mouse.moved(false);
            if(e.getButton() == MouseEvent.BUTTON1) _mouse.leftPressed(true);
            if(e.getButton() == MouseEvent.BUTTON3) _mouse.rightPressed(true);
            notifyObservers(_mouse);
        }
        @Override public void mouseReleased(MouseEvent e) { /* 
            if((e.getButton() == MouseEvent.BUTTON1) && e.isControlDown() && _left_click_press){
                notifyObservers(new MouseShiftLeftClickClickedCommand(_xshape, e.getX(), e.getY()));
            }else if ((e.getButton() == MouseEvent.BUTTON1) && _left_click_press){
                notifyObservers(new MouseReleasedCommand(_xshape, e.getX(), e.getY()));
            }*/
            if(e.getButton() == MouseEvent.BUTTON1) _mouse.leftReleased(true);
            if(e.getButton() == MouseEvent.BUTTON3) _mouse.rightReleased(true);
            notifyObservers(_mouse);
            _mouse.moved(false);
        }
        @Override public void mouseDragged(MouseEvent e) { /*  notifyObservers(new MouseDraggedCommand(_xshape, e.getX(), e.getY())); */ }
        @Override public void mouseMoved(MouseEvent e) { 
            _mouse.moved(true);
            notifyObservers(_mouse);
            /*if((e.getButton() == MouseEvent.BUTTON1)) notifyObservers(new MouseMovedCommand(_xshape, e.getX(), e.getY())); */
        }
        @Override public void registerOberver(IInputObserver obs) { _xshape = (XShape) obs; }
        @Override public void unRegisterObserver(IInputObserver obs) { _xshape = null; }
        @Override public void notifyObservers(Command command) { _xshape.update(command); }

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

    @Override
    public void createToolBar() { 
        toolBar(new ToolBarAwt(this));
        toolBar().makeProduct(); 
    }

    @Override
    public void setPopUpMenu(Point2D pos, int selected, boolean grouped) {/* 
        createPopUpMenu();
        popUpMenu().init(pos, selected, grouped);
        popUpMenu().makeProduct();

        ((PopupMenu) popUpMenu().getProduct()).show(_frame, (int) popUpMenu().getPosX(), (int) popUpMenu().getPosY());
        _jc.add((PopupMenu) popUpMenu().getProduct());*/
    }

    @Override
    public void removePopUpMenu() {/* 
        if(_popup != null)
            _jc.remove(_popup);
        _popup = null;*/
    }
    @Override protected ShapeFactory createFactory() { return new ShapeFactoryAwt(); }
    @Override public void render() { _jc.repaint(); }
@Override public void createPopUpMenu() {/* popUpMenu(new PopUpMenuAwt(this, new Point2D.Double(0,0), 0, false, _frame)); */}

    @Override
    public boolean isInPopUpMenu(Point2D pos) {
        return false;
    }
}
