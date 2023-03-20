package xshape.controleur;

import java.awt.*;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import xshape.model.abstractFactory.ShapeFactory;
import xshape.model.abstractFactory.ShapeFactoryAwt;
import xshape.vue.AwtContext;
import xshape.model.ToolBar;
import xshape.model.ToolBarAwt;
import xshape.model.Builder.ToolBarDirector;
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
    class JCanvas extends JPanel  {
        XShape _xshape = null;

        public JCanvas(XShape xs) {
            _xshape = xs;
            ToolBarDirector tb = (ToolBarDirector) _xshape;
            tb.createToolBar();
        }

        public void paint(Graphics g) {
            super.paint(g);
            AwtContext.instance().graphics(g);
            _xshape.draw();
        }

        
    }

    @Override
    protected ShapeFactory createFactory() {
        return new ShapeFactoryAwt();
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
        _toolBar.makeProduct();
    }

    @Override
    public Object getToolBar() {
        return _toolBar.getProduct();
    }
    @Override
    public ToolBar toolBar() {
        return _toolBar;
    }
}
