package xshape.model.Command;


public interface CommandHistory {

    void push(ICommand command);
    void pushRedo(ICommand command);
    ICommand pop();
    ICommand popRedo();
    void clearRedo();
    void undo();
    void redo();
}
