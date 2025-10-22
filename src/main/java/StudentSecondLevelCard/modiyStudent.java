package StudentSecondLevelCard;

import Common.Cookie;
import Controler.StudentCard_ClassStudentControl;
import FACTORY.DAOFactory;
import VO.stu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class modiyStudent {
    private static JButton OK;
    private static JTextField StuId;
    private static JTextField StuName;
    private static JTextField StuSex;
    private static JTextField TWTIME;
    private static JTextField KQTIME;
    private static JTextField QQTIME;
    private static JTextField ALLGRADE;
    private static JLabel stuid;
    private static JLabel stuname;
    private static JLabel stusex;
    private static JLabel twtime;
    private static JLabel kqtime;
    private static JLabel qqtime;
    private static JLabel allgrade;
    private static JPanel panel;
    private static JFrame frame;
    private static String SId;

    public modiyStudent(String getId) {
        modiyStudent.SId = getId;
        stu u = new stu();
        try {
            u = DAOFactory.getIstuDAO().findstudentByID(Cookie.getUserID(), Cookie.getChoiceClass(), SId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        int width = 320;
        int height = 450;
        frame = new JFrame("修改学生信息");
        panel = new JPanel();
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // frame.setLayout(null);
        panel.setLayout(null);
        stuid = new JLabel("学  生   学  号");
        stuid.setFont(new java.awt.Font("Dialog", 1, 20));
        stuid.setBounds(20, 20, 150, 25);
        panel.add(stuid);
        StuId = new JTextField(u.getStuId(), 20);
        StuId.setBounds(150, 20, 150, 30);
        panel.add(StuId);
        stuname = new JLabel("学  生   姓  名");
        stuname.setFont(new java.awt.Font("Dialog", 1, 20));
        stuname.setBounds(20, 70, 150, 25);
        panel.add(stuname);
        StuName = new JTextField(u.getStuName(), 20);
        StuName.setBounds(150, 70, 150, 30);
        panel.add(StuName);
        stusex = new JLabel("学  生   性  别");
        stusex.setFont(new java.awt.Font("Dialog", 1, 20));
        stusex.setBounds(20, 120, 150, 25);
        panel.add(stusex);
        StuSex = new JTextField(u.getStuSex(), 20);
        StuSex.setBounds(150, 120, 150, 30);
        panel.add(StuSex);
        twtime = new JLabel("提问被抽次数");
        twtime.setFont(new java.awt.Font("Dialog", 1, 20));
        twtime.setBounds(20, 170, 150, 25);
        panel.add(twtime);
        TWTIME = new JTextField(Integer.toString(u.gettwtime()), 20);
        TWTIME.setBounds(150, 170, 150, 30);
        panel.add(TWTIME);
        kqtime = new JLabel("考勤被抽次数");
        kqtime.setFont(new java.awt.Font("Dialog", 1, 20));
        kqtime.setBounds(20, 220, 150, 25);
        panel.add(kqtime);
        KQTIME = new JTextField(Integer.toString(u.getkqtime()), 20);
        KQTIME.setBounds(150, 220, 150, 30);
        panel.add(KQTIME);
        qqtime = new JLabel("缺  勤   次  数");
        qqtime.setFont(new java.awt.Font("Dialog", 1, 20));
        qqtime.setBounds(20, 270, 150, 25);
        panel.add(qqtime);
        QQTIME = new JTextField(Integer.toString(u.getqqtime()), 20);
        QQTIME.setBounds(150, 270, 150, 30);
        panel.add(QQTIME);
        allgrade = new JLabel("总体得分评价");
        allgrade.setFont(new java.awt.Font("Dialog", 1, 20));
        allgrade.setBounds(20, 320, 150, 25);
        panel.add(allgrade);
        ALLGRADE = new JTextField(Double.toString(u.getallgrade()), 20);
        ALLGRADE.setBounds(150, 320, 150, 30);
        panel.add(ALLGRADE);
        OK = new JButton("确   定");
        OK.setFont(new java.awt.Font("Dialog", 1, 20));
        OK.setBounds(50, 360, 200, 50);
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
                String studentid = StuId.getText();
                String studentname = StuName.getText();
                String studentsex = StuSex.getText();
                String stwtime = TWTIME.getText();
                String skqtime = KQTIME.getText();
                String sqqtime = QQTIME.getText();
                String sallgrade = ALLGRADE.getText();
                if (studentid.equals("")) {
                    next = false;
                }
                if (studentname.equals("")) {
                    next = false;
                }
                if (studentsex.equals("")) {
                    next = false;
                }
                if (stwtime.equals("")) {
                    next = false;
                }
                if (skqtime.equals("")) {
                    next = false;
                }
                if (sqqtime.equals("")) {
                    next = false;
                }
                if (sallgrade.equals("")) {
                    next = false;
                }
                if (next) {
                    stu s = new stu();
                    s.setStuId(studentid);
                    s.setStuName(studentname);
                    s.setStuSex(studentsex);
                    s.settwtime(Integer.parseInt(stwtime));
                    s.setkqtime(Integer.parseInt(skqtime));
                    s.setqqtime(Integer.parseInt(sqqtime));
                    s.setallgrade(Double.parseDouble(sallgrade));
                    try {
                        DAOFactory.getIstuDAO().updatestudent(Cookie.getUserID(), Cookie.getChoiceClass(), s, SId);
                        StudentCard_ClassStudentControl.setStudentCard_ClassStudentControl();
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
