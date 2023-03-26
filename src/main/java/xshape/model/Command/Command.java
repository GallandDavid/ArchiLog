package xshape.model.Command;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import xshape.controleur.XShape;
import xshape.model.visitor.IInputVisitor;
import xshape.model.visitor.IVisitable;

public abstract class Command implements ICommand, IVisitable{
    public XShape _app;
    protected ArrayList<Object> _editor = new ArrayList<>();
    protected ArrayList<Object> _backup = new ArrayList<>();
    private static int count = 0;
    private int num;

    public Command(XShape app, Object editor){
        count ++;
        num = count;
        _app = app;
        _editor.add(editor);
    }

    public Command(XShape app){
        count ++;
        num = count;
        _app = app;
    }

    @Override
    public void add(Object obj){
        _editor.add(obj);
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
        for (Object editor : _editor) {
            _backup.add(instanceShape(editor));
        }
        
    }

    @Override
    public String print(){
        return "" + num;
    }

    
}
