package xshape.model.Command;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import xshape.controleur.XShape;

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

    public Command(XShape app){
        count ++;
        num = count;
        _app = app;
    }

    public Object instanceShape(Object editor){
        Class<?> classe = null;
        Object shape = null;
        try {
            classe = Class.forName (editor.getClass().getName());
            Constructor<?> constructeur = classe.getConstructor (editor.getClass());
            shape = constructeur.newInstance (new Object [] {editor});
        } 
        catch (ClassNotFoundException e) {  e.printStackTrace();    }
        catch (InstantiationException e) {  e.printStackTrace();    } 
        catch (IllegalAccessException e) {  e.printStackTrace();    } 
        catch (InvocationTargetException e) { e.printStackTrace();    } 
        catch (NoSuchMethodException e) { e.printStackTrace();    }
        return shape;
    }

    @Override
    public void saveBackup() {
        _backup = instanceShape(_editor);
    }

    @Override
    public String print(){
        return "" + num;
    }
    
}
