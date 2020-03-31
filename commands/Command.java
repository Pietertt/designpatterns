package commands;

import java.awt.*;
import java.util.ArrayList;
import shapes.Shape;

public interface Command {
      abstract void execute(); 
      abstract Shape get();
}