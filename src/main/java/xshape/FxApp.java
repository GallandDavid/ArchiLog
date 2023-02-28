package xshape;

public class FxApp extends XShape {

    @Override
    protected ShapeFactory createFactory() {
        return new ShapeFactoryFx(FxApplication._root);
    }
    @Override
    public void run() {
        draw();
        FxApplication.launch(FxApplication.class);
    }
}