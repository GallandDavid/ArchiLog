package xshape.model.Command;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import xshape.controleur.XShape;
import xshape.model.shape.Shape;
import xshape.model.shape.group.Group;

public abstract class Command implements ICommand{
    public XShape _app;
    protected ArrayList<Object> _editor = new ArrayList<>();
    protected ArrayList<Object> _backup = new ArrayList<>();
    private static int count = 0;
    private int num;

    public Command(XShape app, ArrayList<Object> editor){
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

    @Override
    public void add(Object obj){
        _editor.add(obj);
    }

    public Object instanceShape(Object editor,ArrayList<Shape> shapes){
        Class<?> classe = null;
        Object shape = null;
        try {
            Constructor<?> constructeur;
            classe = Class.forName (editor.getClass().getName());
            if(editor instanceof Group){
                constructeur = classe.getConstructor ();
                shape = constructeur.newInstance ();
                ((Group) shape).add(shapes);
                System.out.println(shape.toString());
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
            _backup.add(instanceShape(editor, shapes));
        }
        
    }

    @Override
    public String print(){
        return "" + num;
    }

    
}
