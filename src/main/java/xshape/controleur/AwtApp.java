package xshape.controleur;

import java.awt.*;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import xshape.model.abstractFactory.ShapeFactory;
import xshape.model.abstractFactory.ShapeFactoryAwt;
import xshape.observer.Iobserver;
import xshape.vue.AwtContext;
import xshape.model.RectangleAwt;
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
        frame.setJMenuBar((JMenuBar) jc.getToolBar());

        frame.addWindowListener(wa);
        frame.getContentPane().add(component);
        frame.pack();
        frame.setVisible(true);
    }
}

public class AwtApp extends XShape {
    class JCanvas extends JPanel implements ToolBarDirector, Iobserver {
        XShape _xshape = null;
        protected ToolBar _toolBar = null;

        public JCanvas(XShape xs) {
            _toolBar = new ToolBarAwt(this);
            _xshape = xs;
            createToolBar();
        }

        public void paint(Graphics g) {
            super.paint(g);
            AwtContext.instance().graphics(g);
            _xshape.draw();
        }

        @Override
        public void createToolBar() {
            _toolBar.makeProduct();
        }

        @Override
        public void update(String code) {
        }

        @Override
        public Object getToolBar() {
            return _toolBar.getProduct();
        }

        @Override
        public void update(String code, int X, int Y) {
            switch(code){
                case "rect selected":
                System.out.println("ok");
                xshape.model.Shape rect = (xshape.model.Shape) new RectangleAwt(X,Y,true);
                    /*_xshape._shapes.add(rect);*/
                    break;
                default:
                    break;
            }
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
        jc.setBackground(Color.WHITE);
        jc.setPreferredSize(new Dimension(500, 500));
        GUIHelper.showOnFrame(jc, "XShape Swing/AWT Rendering");
    }

    @Override
    public void pop() {
    }

}
