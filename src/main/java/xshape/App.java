package xshape;

import xshape.controleur.XShape;
import xshape.vue.AwtApp;
import xshape.vue.FxApp;

public class App {

    public static void main(String[] args) {
        XShape appAwt = new AwtApp();
        appAwt.run();
        XShape appFx = new FxApp();
        appFx.run();

    }

}
