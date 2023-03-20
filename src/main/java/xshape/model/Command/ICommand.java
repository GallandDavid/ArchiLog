package xshape.model.Command;

public interface ICommand {
    void saveBackup();
    boolean execute();
    void undo();
}
