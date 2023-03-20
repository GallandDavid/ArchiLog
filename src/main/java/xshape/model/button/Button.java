package xshape.model.button;


public abstract class Button { 
    private String _title;
    
    public Button(){
        _title = "Button";
    }

    public Button(String title){
        this._title = title;
    }

    public String title(){
        return _title;
    }
}
