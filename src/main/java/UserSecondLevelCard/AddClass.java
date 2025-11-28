package UserSecondLevelCard;

import Common.Cookie;
import Controler.UserCard_UserClassControl;
import FACTORY.DAOFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddClass {
    private static JButton OK;
    private static JTextField classText;
    private static JTextArea miaoshu;
    private static JPanel panel;
    private static JFrame frame;

    public AddClass() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        int width = 320;
        int height = 450;
        frame = new JFrame("增加课程");
        panel = new JPanel();
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // frame.setLayout(null);
        panel.setLayout(null);
        classText = new JTextField("课程名...", 20);
        classText.setBounds(50, 80, 200, 30);
        panel.add(classText);
        miaoshu = new JTextArea("描述...",15 ,10);
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
                if (classname.equals("课程名...") || classname.equals("")) {
                    next = false;
                }
                if (explain.equals("描述...") || explain.equals("")) {
                    next = false;
                }
                SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
                sdf.applyPattern("yyyy-MM-dd HH:mm:ss");// a为am/pm的标记
                Date date = new Date();// 获取当前时间
                if (next) {
                    try {
                        DAOFactory.getIuserDAO().AddStu(Cookie.getUserID(), classname, sdf.format(date).toString(),
                                explain);
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
