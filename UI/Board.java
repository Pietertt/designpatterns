package UI;

import javax.swing.JPanel;
import java.awt.Dimension;

public class Board extends JPanel {
      public Board(){
            super(null);
      }

      @Override
      public Dimension getPreferredSize() {
            return new Dimension(500, 500);
      }
}