package xshape.model.toolbar;

import java.awt.event.MouseListener;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import xshape.controleur.XShape;
import xshape.model.Command.RectangleSelectedCommand;
import xshape.observer.Iobserver;

public class ToolBarAwt extends ToolBar {

    public ToolBarAwt(Iobserver obs) {
        super(obs);
    }

    @Override
    public void makeProduct() {
        createToolBar();
        createRectButton();
    }

    @Override
    public void createToolBar() {
        setProduct(new JMenuBar());
    }

    @Override
    public void createRectButton() {
        JMenuBar mb = (JMenuBar) getProduct();
        JMenuItem br = new JMenuItem(getRectButton().title());
        br.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                notifyObservers(new RectangleSelectedCommand((XShape ) _app, br, e.getX(), e.getY()));
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
            }
        });
        mb.add(br);
        
        setProduct(mb);

    }


    @Override
    public void createRedoButton() {
    }

    @Override
    public void createUndoButton() {
    }

}
