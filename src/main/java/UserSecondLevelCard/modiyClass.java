package UserSecondLevelCard;

import Common.Cookie;
import Controler.UserCard_UserClassControl;
import FACTORY.DAOFactory;
import VO.user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class modiyClass {
    private static JButton OK;
    private static JTextField classText;
    private static JTextArea miaoshu;
    private static JPanel panel;
    private static JFrame frame;
    private static JLabel cn;
    private static JLabel ep;
    private static String Cname;

    public modiyClass(String Cname) {
        modiyClass.Cname = Cname;
        user use = new user();
        try {
            use = DAOFactory.getIuserDAO().SecStuByName(Cookie.getUserID(), Cname);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        int width = 320;
        int height = 450;
        frame = new JFrame("修改课程");
        panel = new JPanel();
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // frame.setLayout(null);
        panel.setLayout(null);
        cn = new JLabel("课 程 名");
        cn.setFont(new java.awt.Font("Dialog", 1, 20));
        cn.setBounds(105, 10, 80, 25);
        ep = new JLabel("课程描述");
        ep.setFont(new java.awt.Font("Dialog", 1, 20));
        ep.setBounds(100, 100, 100, 25);
        panel.add(cn);
        panel.add(ep);
        classText = new JTextField(use.getclassname(), 20);
        classText.setBounds(50, 60, 200, 30);
        panel.add(classText);
        miaoshu = new JTextArea(use.getexplain(), 15, 10);
        miaoshu.setBounds(50, 140, 200, 100);
        panel.add(miaoshu);
        OK = new JButton("确   定");
        OK.setFont(new java.awt.Font("Dialog", 1, 20));
        OK.setBounds(50, 280, 200, 50);
        panel.add(OK);
        frame.setIconImage(new ImageIcon("src\\Image\\SK3.png").getImage());
        frame.add(panel);
        OK.setActionCommand("OK");
        OK.addActionListener(new ButtonClickListener());
        frame.setVisible(true);
        frame.setBounds((screenWidth - width) / 2, (screenHeight - height) / 2, width, height);
    }

    private static class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("OK")) {
                boolean next = true;
                String classname = classText.getText();
                String explain = miaoshu.getText();
                if (classname.equals("")) {
                    next = false;
                }
                if (explain.equals("")) {
                    next = false;
                }

                SimpleDateFormat sdf = new SimpleDateFormat();
                sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
                Date date = new Date();
                String DATE = sdf.format(date).toString();
                user u = new user();
                u.setclassname(classname);
                u.setbegintime(DATE);
                u.setexplain(explain);
                if (next) {
                    try {
                        DAOFactory.getIuserDAO().UpdateStu(u, Cookie.getUserID(), Cname);
                        UserCard_UserClassControl.setUserCard_UserClassControl();
                        frame.dispose();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    String Text = "请将所有信息输入完整";
                    JOptionPane.showMessageDialog(null, Text, "警告", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }
}
