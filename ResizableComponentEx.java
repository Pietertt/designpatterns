import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import shapes.*;

public class ResizableComponentEx extends JFrame {

    private Rectangle res;

    public ResizableComponentEx() {

        initUI();
    }

    private void initUI() {

        var pnl = new JPanel(null);
        add(pnl);

        var area = new JPanel();
        area.setBackground(Color.white);

        res = new Rectangle(area);
        res.setBounds(50, 50, 200, 150);
        pnl.add(res);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {

                requestFocus();
                res.repaint();
            }
        });

        setSize(350, 300);
        setTitle("Resizable component");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            var ex = new ResizableComponentEx();
            ex.setVisible(true);
        });
    }
}