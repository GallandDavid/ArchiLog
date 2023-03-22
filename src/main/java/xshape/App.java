package xshape;

import xshape.controleur.AwtApp;
import xshape.controleur.XShape;
import xshape.vue.FxApplication;

public class App {

    public static void main(String[] args) {
        XShape appAwt = new AwtApp();
        appAwt.run();
        FxApplication.launch(FxApplication.class);

    }

}
