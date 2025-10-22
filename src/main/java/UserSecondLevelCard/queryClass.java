package UserSecondLevelCard;

import Common.Cookie;
import Controler.UserCard_UserClassControl;
import FACTORY.DAOFactory;
import FirstLevelCard.StudentCard;
import FirstLevelCard.UserCard;
import VO.user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class queryClass {
    private static JButton KEEP;
    private static JButton SELECT;
    private static JButton MOVE;
    private static JLabel cn;
    private static JLabel ep;
    private static JPanel panel;
    private static JFrame frame;
    private static JTextField classText;
    private static JTextArea miaoshu;
    private static String queryname;

    public queryClass(String queryname) {
        queryClass.queryname = queryname;
        user use = new user();
        try {
            use = DAOFactory.getIuserDAO().SecStuByName(Cookie.getUserID(), queryname);
        } catch (Exception ex) {
            String Text = "查无此课程";
            JOptionPane.showMessageDialog(null, Text, "警告", JOptionPane.WARNING_MESSAGE);
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
        KEEP = new JButton("确   定");
        KEEP.setFont(new java.awt.Font("Dialog", 1, 20));
        KEEP.setBounds(50, 270, 200, 40);
        panel.add(KEEP);
        MOVE = new JButton("删   除");
        MOVE.setFont(new java.awt.Font("Dialog", 1, 20));
        MOVE.setBounds(50, 320, 200, 40);
        panel.add(MOVE);
        SELECT = new JButton("选   择");
        SELECT.setFont(new java.awt.Font("Dialog", 1, 20));
        SELECT.setBounds(50, 370, 200, 40);
        panel.add(SELECT);
        frame.setIconImage(new ImageIcon("src\\Image\\SK3.png").getImage());
        frame.add(panel);
        KEEP.setActionCommand("OK");
        MOVE.setActionCommand("move");
        SELECT.setActionCommand("select");
        KEEP.addActionListener(new ButtonClickListener());
        MOVE.addActionListener(new ButtonClickListener());
        SELECT.addActionListener(new ButtonClickListener());
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

                SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
                sdf.applyPattern("yyyy-MM-dd HH:mm:ss");// a为am/pm的标记
                Date date = new Date();// 获取当前时间
                String DATE = sdf.format(date).toString();
                user u = new user();
                u.setclassname(classname);
                u.setbegintime(DATE);
                u.setexplain(explain);
                if (next) {
                    try {
                        DAOFactory.getIuserDAO().UpdateStu(u, Cookie.getUserID(), queryname);
                        UserCard_UserClassControl.setUserCard_UserClassControl();
                        frame.dispose();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    String Text = "请将所有信息输入完整";
                    JOptionPane.showMessageDialog(null, Text, "警告", JOptionPane.WARNING_MESSAGE);
                }
            } else if (command.equals("move")) {
                try {
                    DAOFactory.getIuserDAO().DelStu(queryname, Cookie.getUserID());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                UserCard_UserClassControl.setUserCard_UserClassControl();
                frame.dispose();
            } else if (command.equals("select")) {
                Cookie.setChoiceClass(queryname);
                try {
                    int allnumber = DAOFactory.getIstuDAO().getCount(Cookie.getUserID(), Cookie.getChoiceClass());
                    // System.out.println(allnumber);
                    ArrayList<Double> mark = new ArrayList<Double>();
                    for (int i = 0; i < allnumber; i++) {
                        mark.add(10.0);
                    }
                    Cookie.setMark(mark);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                String Text = "课程名: '" + queryname + "' 选择成功！";
                StudentCard.RestartInstance();
                JOptionPane.showMessageDialog(null, Text, "提示", JOptionPane.PLAIN_MESSAGE);
                frame.dispose();
            }
        }
    }
}
