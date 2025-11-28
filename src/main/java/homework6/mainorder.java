package homework6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainorder extends JFrame {
    JDesktopPane desktopPane;
    InternalFrame pFrame = null;
    public static void main(String args[]) {
        mainorder frame = new mainorder();
        frame.setVisible(true);
    }
    public mainorder() {
        super();
        setTitle("单例模式实验");
        setBounds(100, 100, 570, 470);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        desktopPane = new JDesktopPane();
        getContentPane().add(desktopPane, BorderLayout.CENTER);
        JPanel panel = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
        panel.setLayout(flowLayout);
        getContentPane().add(panel, BorderLayout.NORTH);
        JButton personnelButton = new JButton();
        personnelButton.setText("打开窗体");
        personnelButton.addActionListener(new BtListener(pFrame, "子窗体"));
        panel.add(personnelButton);
    }

    private class BtListener implements ActionListener {
        InternalFrame inFrame=null;
        String title;
        public BtListener(InternalFrame inFrame, String title) {
            this.inFrame = inFrame;
            this.title = title;
        }
        public void actionPerformed(ActionEvent e) {
            if (inFrame == null || inFrame.isClosed()) {
                inFrame = new InternalFrame(title);
                inFrame.setBounds(10, 10, 250, 180);
                inFrame.setVisible(true);
                desktopPane.add(inFrame);
            }
        }
    }
    private class InternalFrame extends JInternalFrame {
        public InternalFrame(String title) {
            super();
            setTitle(title);
            setResizable(true);
            setClosable(true);
            setIconifiable(true);
            setMaximizable(true);
        }
    }
}