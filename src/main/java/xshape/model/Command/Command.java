package xshape.model.Command;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import xshape.controleur.XShape;
import xshape.model.Shape;

public abstract class Command implements ICommand {
    public XShape _app;
    protected Object _editor;
    protected Object _backup;
    private static int count = 0;
    private int num;

    public Command(XShape app, Object editor){
        count ++;
        num = count;
        _app = app;
        _editor = editor;

    }

    @Override
    public void saveBackup() {
        Class<?> classe = null;
        Shape shape = null;
        try {
            classe = Class.forName (_editor.getClass().getName());
            Constructor<?> constructeur = classe.getConstructor (new Class [] {Class.forName ("xshape.model.Shape")});
            shape = (Shape) constructeur.newInstance (new Object [] {_editor});
        } 
        catch (ClassNotFoundException e) {  e.printStackTrace();    }
        catch (InstantiationException e) {  e.printStackTrace();    } 
        catch (IllegalAccessException e) {  e.printStackTrace();    } 
        catch (InvocationTargetException e) { e.printStackTrace();    } 
        catch (NoSuchMethodException e) { e.printStackTrace();    }
        _backup = shape;
    }

    @Override
    public String print(){
        return "" + num;
    }
    
}
