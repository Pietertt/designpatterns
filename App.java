import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class App {

      private static void init(){
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new Board());
            frame.pack();
            frame.setVisible(true);
      }

      public static void main(String[] args){
            SwingUtilities.invokeLater(new Runnable() {
                  public void run(){
                        init();
                  }
            });
      }
}