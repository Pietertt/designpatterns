package commands;

public interface Order {
    void execute();
    void undo();
    void redo();
}
