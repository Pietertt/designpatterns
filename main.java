import shapes.rectangular;

import javax.swing.*;
import java.awt.*;

public class main extends JPanel {

      public static void main(String[] args) {
            JFrame jf = new JFrame();

            rectangular rect = new rectangular(50, 50, 100, 100);

            jf.add(new main());
            jf.setSize(500, 500);
            jf.setVisible(true);
      }
}