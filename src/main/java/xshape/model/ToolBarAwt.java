package xshape.model;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ToolBarAwt extends ToolBar {

    public ToolBarAwt() {

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
        mb.add(new JMenuItem(getRectButton().title()));
        setProduct(mb);
    }

}
