package WorkSecondLevelCard;

import Common.Cookie;
import Common.speekName;
import FACTORY.DAOFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class sxExtractControl extends JPanel {
    private static JButton OK;
    private static JLabel Name;
    private  int num = -1;

    public sxExtractControl() {
        setLayout(null);
        Color workbgcolor=new Color(240,240,240);
        setBackground(workbgcolor);
        OK = new JButton("开  始  抽  取");
        OK.setFont(new java.awt.Font("Dialog", 1, 20));
        OK.setBounds(285, 320, 200, 100);
        Name = new JLabel("点击开始抽取", JLabel.CENTER);
        Name.setFont(new java.awt.Font("Dialog", 1, 60));
        Name.setBounds(0, 10, 770, 300);
        // Name.setVerticalTextPosition(JLabel.TOP);
        Name.setHorizontalTextPosition(JLabel.CENTER);
        add(OK);
        add(Name);
        OK.setActionCommand("OK");
        OK.addActionListener(new ButtonClickListener());
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("OK")) {
                try {
                    int count = DAOFactory.getIstuDAO().getCount(Cookie.getUserID(), Cookie.getChoiceClass());
                    num++;
                    if (num >= count) {
                        num = 0;
                    }
                    String name = DAOFactory.getIstuDoDAO().RandomSampling(Cookie.getUserID(), Cookie.getChoiceClass(),
                            num);
                    new speekName(name).start();
                    Name.setText(name);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
