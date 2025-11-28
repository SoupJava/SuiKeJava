package StudentSecondLevelCard;

import Common.Cookie;
import Controler.StudentCard_ClassStudentControl;
import FACTORY.DAOFactory;
import VO.stu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddStudent {
    private static JButton OK;
    private static JTextField StuId;
    private static JTextField StuName;
    private static JTextField StuSex;
    private static JPanel panel;
    private static JFrame frame;

    public AddStudent() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        int width = 320;
        int height = 450;
        frame = new JFrame("增加学生");
        panel = new JPanel();
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // frame.setLayout(null);
        panel.setLayout(null);
        StuId = new JTextField("学号...", 20);
        StuId.setBounds(50, 80, 200, 30);
        panel.add(StuId);
        StuName = new JTextField("姓名...", 50);
        StuName.setBounds(50, 140, 200, 30);
        panel.add(StuName);
        StuSex = new JTextField("性别...");
        StuSex.setBounds(50, 200, 200, 30);
        panel.add(StuSex);
        OK = new JButton("确   定");
        OK.setFont(new java.awt.Font("Dialog", 1, 20));
        OK.setBounds(50, 280, 200, 50);
        panel.add(OK);
        frame.add(panel);
        OK.setActionCommand("OK");
        OK.addActionListener(new ButtonClickListener());
        frame.setIconImage(new ImageIcon("src\\Image\\SK3.png").getImage());
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
                if (studentid.equals("学号...") || studentid.equals("")) {
                    next = false;
                }
                if (studentname.equals("姓名...") || studentname.equals("")) {
                    next = false;
                }
                if (studentsex.equals("性别...") || studentsex.equals("")) {
                    next = false;
                }
                if (next) {
                    stu s = new stu();
                    s.setStuId(studentid);
                    s.setStuName(studentname);
                    s.setStuSex(studentsex);
                    s.settwtime(0);
                    s.setkqtime(0);
                    s.setqqtime(0);
                    s.setallgrade(60);
                    try {
                        DAOFactory.getIstuDAO().addstudent(Cookie.getUserID(), Cookie.getChoiceClass(), s);
                        StudentCard_ClassStudentControl.setStudentCard_ClassStudentControl();
                        int allnumber = DAOFactory.getIstuDAO().getCount(Cookie.getUserID(), Cookie.getChoiceClass());
                        // System.out.println(allnumber);
                        ArrayList<Double> mark = new ArrayList<Double>();
                        for (int i = 0; i < allnumber; i++) {
                            mark.add(10.0);
                        }
                        Cookie.setMark(mark);
                        frame.dispose();
                    } catch (Exception ex) {
                        // StudentCard_ClassStudentControl.setStudentCard_ClassStudentControl();
                        // frame.dispose();
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
