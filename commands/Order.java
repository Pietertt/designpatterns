package commands;

public interface Order {
      abstract void execute();
      abstract void undo();
      abstract void redo();
}