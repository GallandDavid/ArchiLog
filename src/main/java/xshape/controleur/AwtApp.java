package xshape.controleur;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.PopupMenu;
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
import xshape.model.observer.Iobservable;
import xshape.model.observer.Iobserver;
import xshape.model.shape.Shape;
import xshape.vue.AwtContext;
import xshape.model.Builder.popupmenu.PopUpMenuAwt;
import xshape.model.Builder.toolbar.ToolBar;
import xshape.model.Builder.toolbar.ToolBarAwt;
import xshape.model.Builder.toolbar.ToolBarDirector;
import xshape.model.Command.Command;
import xshape.model.Command.MouseClickedCommand;
import xshape.model.Command.MouseDraggedCommand;
import xshape.model.Command.MouseEnteredCommand;
import xshape.model.Command.MouseExitedCommand;
import xshape.model.Command.MouseMovedCommand;
import xshape.model.Command.MouseLeftClickPressedCommand;
import xshape.model.Command.MouseReleasedCommand;
import xshape.model.Command.MouseRightClickClickedCommand;
import xshape.model.Command.MouseShiftLeftClickClickedCommand;
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
            System.out.println("Size : (" + jc.getPreferredSize().getWidth() + ", " + component.getHeight()/100*ToolBar.getVh() + ")   |   ");
        jb.setSize(new Dimension((int)(jc.getPreferredSize().getWidth()/100*ToolBar.getVw()), (int) component.getPreferredSize().getWidth()/100*ToolBar.getVh()));
        frame.setJMenuBar(jb);
        jc._xshape.toolBar().setWidth(jb.getPreferredSize().getWidth());
        jc._xshape.toolBar().setHeight(jb.getPreferredSize().getHeight() + 40);
        

        frame.addWindowListener(wa);
        frame.getContentPane().add(component);
        frame.pack();
        frame.setVisible(true);
        ((AwtApp) jc._xshape).setFrame(frame);
    }
}

public class AwtApp extends XShape{
    JCanvas _jc = null;
    PopupMenu _popup = null;
    private boolean _right_click_press = false;
    private boolean _left_click_press = false;
    private JFrame _frame = null;

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

        @Override public void mouseClicked(MouseEvent e) {
            if ((e.getButton() == MouseEvent.BUTTON1) && _left_click_press) {
                _left_click_press = false;
                notifyObservers(new MouseClickedCommand(_xshape, e.getX(), e.getY()));
            }
            if ((e.getButton() == MouseEvent.BUTTON3) && _right_click_press) {
                _right_click_press = false;
                notifyObservers(new MouseRightClickClickedCommand(_xshape, e.getX(), e.getY()));
            }
        }
        @Override public void mouseEntered(MouseEvent e) { if((e.getButton() == MouseEvent.BUTTON1)) notifyObservers(new MouseEnteredCommand(_xshape, e.getX(), e.getY())); }
        @Override public void mouseExited(MouseEvent e) { if((e.getButton() == MouseEvent.BUTTON1)) notifyObservers(new MouseExitedCommand(_xshape, e.getX(), e.getY())); }
        @Override public void mousePressed(MouseEvent e) { 
            if(e.getButton() == MouseEvent.BUTTON1){
                _left_click_press = true;
            }
            if(e.getButton() == MouseEvent.BUTTON1 && e.isControlDown()) {}
            else if (e.getButton() == MouseEvent.BUTTON1){ 
                notifyObservers(new MouseLeftClickPressedCommand(_xshape, e.getX(), e.getY())); 
            }
            if(e.getButton() == MouseEvent.BUTTON3){
                _right_click_press = true;
            }
        }
        @Override public void mouseReleased(MouseEvent e) { 
            if((e.getButton() == MouseEvent.BUTTON1) && e.isControlDown() && _left_click_press){
                notifyObservers(new MouseShiftLeftClickClickedCommand(_xshape, e.getX(), e.getY()));
            }else if ((e.getButton() == MouseEvent.BUTTON1) && _left_click_press){
                notifyObservers(new MouseReleasedCommand(_xshape, e.getX(), e.getY()));
            }
        }
        @Override public void mouseDragged(MouseEvent e) { 
            notifyObservers(new MouseDraggedCommand(_xshape, e.getX(), e.getY())); 
        }
        @Override public void mouseMoved(MouseEvent e) { if((e.getButton() == MouseEvent.BUTTON1)) notifyObservers(new MouseMovedCommand(_xshape, e.getX(), e.getY())); }
        @Override public void registerOberver(Iobserver obs) { _xshape = (XShape) obs; }
        @Override public void unRegisterObserver(Iobserver obs) { _xshape = null; }
        @Override public void notifyObservers(Command command) { _xshape.update(command); }
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
    public void setPopUpMenu(Point2D pos, int selected, boolean grouped) {
        createPopUpMenu();
        popUpMenu().init(pos, selected, grouped);
        popUpMenu().makeProduct();

        ((PopupMenu) popUpMenu().getProduct()).show(_frame, (int) popUpMenu().getPosX(), (int) popUpMenu().getPosY());
        _jc.add((PopupMenu) popUpMenu().getProduct());
    }

    @Override
    public void removePopUpMenu() {
        if(_popup != null)
            _jc.remove(_popup);
        _popup = null;
    }
    public void setFrame(JFrame frame){ _frame = frame; }
    @Override protected ShapeFactory createFactory() { return new ShapeFactoryAwt(); }
    @Override public void render() { _jc.repaint(); }
    @Override public void createPopUpMenu() { popUpMenu(new PopUpMenuAwt(this, new Point2D.Double(0,0), 0, false, _frame)); }

    @Override
    public boolean isInPopUpMenu(Point2D pos) {
        return false;
    }
}
