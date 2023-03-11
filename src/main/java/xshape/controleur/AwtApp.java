package xshape.controleur;

import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JComponent;
import javax.swing.JFrame;

import xshape.model.ShapeFactory;
import xshape.model.ShapeFactoryAwt;
import xshape.model.ToolBar;
import xshape.model.Button;
import xshape.model.ToolBarAwt;
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
        XShape xshape = jc._xshape;
        ToolBarAwt toolbar = (ToolBarAwt) xshape.toolBar();
        frame.setJMenuBar(toolbar.draw());
        
        frame.addWindowListener(wa);
        frame.getContentPane().add(component);
        frame.pack();
        frame.setVisible(true);
    }
}

public class AwtApp extends XShape {
    class JCanvas extends JPanel {
        XShape _xshape = null;
        public JCanvas(XShape xs) {
            _xshape = xs;
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
    protected ToolBar createToolBar() {
        return new ToolBarAwt(400.0, 0.0, 100.0, 500.0, new Button(420.0,240.0,60.0,20.0,"Rectangle"));
    }

    @Override
    public void run() {
        super.toolBar(createToolBar());
        JCanvas jc = new JCanvas(this);
        jc.setBackground(Color.WHITE);
        jc.setPreferredSize(new Dimension(500, 500));
        GUIHelper.showOnFrame(jc, "XShape Swing/AWT Rendering");        
    }
}

