package xshape.Command;


public interface CommandHistory {

    void push(ICommand command);
     void pop();
}
