package xshape.model.Command;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

import xshape.controleur.XShape;
import xshape.model.shape.Shape;
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

    public Object instanceShape(Object editor, ArrayList<Shape> shapes){
        Class<?> classe = null;
        Object shape = null;
        try {
            Constructor<?> constructeur;
            classe = Class.forName (editor.getClass().getName());
            if(shapes != null){
                System.out.println(shapes.getClass());

                constructeur = classe.getConstructor (editor.getClass(), ArrayList.class );
                shape = constructeur.newInstance (new Object [] {editor}, shapes);
            }
            else{
                constructeur = classe.getConstructor (editor.getClass());
                shape = constructeur.newInstance (new Object [] {editor});
            }
        } 
        catch (ClassNotFoundException e) {  e.printStackTrace();    }
        catch (InstantiationException e) {  e.printStackTrace();    } 
        catch (IllegalAccessException e) {  e.printStackTrace();    } 
        catch (InvocationTargetException e) { e.printStackTrace();    } 
        catch (NoSuchMethodException e) { e.printStackTrace();    }
        return shape;
    }

    @Override
    public void saveBackup(ArrayList<Shape> shapes) {
        for (Object editor : _editor) {
            _backup.add(instanceShape(editor,shapes));
        }
        
    }

    @Override
    public String print(){
        return "" + num;
    }

    
}
