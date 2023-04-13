package xshape.model.Command;


public interface ICommand {
    void saveBackup();
    void backup();
    boolean execute();
    void undo();
    String print();
    void add(Object obj);
}
