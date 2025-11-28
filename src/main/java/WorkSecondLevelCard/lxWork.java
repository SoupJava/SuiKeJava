package WorkSecondLevelCard;

import Common.speekName;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class lxWork {
    ArrayList<lxExtractControl.StuName> sn;
    private static JButton NEXT;
    private static JLabel Name;
    private static JFrame frame;
    private static JPanel panel;
    private int INDEX=0;
    public lxWork(ArrayList<lxExtractControl.StuName> sn){
        this.sn=sn;
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        int width = 420;
        int height = 300;
        frame = new JFrame("乱序抽取");
        panel = new JPanel();
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        panel.setLayout(null);
        NEXT = new JButton("下 一 个");
        NEXT.setFont(new java.awt.Font("Dialog", 1, 20));
        NEXT.setBounds(135, 200, 150, 40);
        panel.add(NEXT);
        Name = new JLabel("BEGIN", JLabel.CENTER);
        Name.setFont(new java.awt.Font("Dialog", 1, 40));
        Name.setBounds(0, 10, 420, 200);
        Name.setHorizontalTextPosition(JLabel.CENTER);
        panel.add(Name);
        frame.setIconImage(new ImageIcon("src\\Image\\SK3.png").getImage());
        frame.add(panel);
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);
        frame.setBounds((screenWidth - width) / 2, (screenHeight - height) / 2, width, height);
        NEXT.setActionCommand("next");
        NEXT.addActionListener(new ButtonClickListener());
    }
    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("next")) {
                if(INDEX+1<= sn.size()) {
                    Name.setText(sn.get(INDEX).getName());
                    new speekName(sn.get(INDEX).getName()).start();
                    INDEX++;
                }else{
                    frame.dispose();
                }
            }
        }
    }
}
