import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class App {

      private static void init() {
            try {
                  JFrame frame = new JFrame();
                  JPanel UI = new JPanel();
                  JButton rectangle = new JButton();
                  JButton ellipse = new JButton();
                  JButton undo = new JButton();
                  JButton redo = new JButton();

                  Board board = new Board();

                  rectangle.setIcon(new ImageIcon(ImageIO.read(new File("img/rectangle.png"))));
                  ellipse.setIcon(new ImageIcon(ImageIO.read(new File("img/ellipse.png"))));
                  undo.setIcon(new ImageIcon(ImageIO.read(new File("img/undo.png"))));
                  redo.setIcon(new ImageIcon(ImageIO.read(new File("img/redo.png"))));

                  rectangle.setBorderPainted(false);
                  rectangle.setFocusPainted(false);
                  rectangle.setContentAreaFilled(false);

                  ellipse.setBorderPainted(false);
                  ellipse.setFocusPainted(false);
                  ellipse.setContentAreaFilled(false);

                  undo.setBorderPainted(false);
                  undo.setFocusPainted(false);
                  undo.setContentAreaFilled(false);

                  redo.setBorderPainted(false);
                  redo.setFocusPainted(false);
                  redo.setContentAreaFilled(false);

                  UI.add(rectangle);
                  UI.add(ellipse);
                  UI.add(undo);
                  UI.add(redo);

                  undo.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                              board.invoker.undo();
                              board.repaint();
                        }
                  });

                  redo.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                              board.invoker.redo();
                              board.repaint();
                        }
                  });


                  frame.getContentPane().add(UI, BorderLayout.SOUTH);
                  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                  frame.add(board);
                  frame.pack();
                  frame.setVisible(true);
            } catch(IOException e){
                  
            }
      }

      public static void main(String[] args) {
            SwingUtilities.invokeLater(new Runnable() {
                  public void run() {
                        init();
                  }
            });
      }
}