package View;

import Common.Cookie;
import Controler.UserCard_UserMessageControl;
import FACTORY.DAOFactory;
import FirstLevelCard.StudentCard;
import FirstLevelCard.UserCard;
import FirstLevelCard.WorkCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
    private JButton UserControl;
    private JButton StudentControl;
    private JButton ClassExtract;
    private JButton EXIT;
    private JFrame menuFrame;
    private JPanel TopPanel;
    private JLabel UserName;
    private static JPanel DoPanel;
    private static CardLayout cardLayout;
    private static UserCard usercard;
    private static StudentCard studentcard;
    private static WorkCard workcard;

    public Menu(String name) {
        // System.out.println(Cookie.getUserID());
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        int width = 1000;
        int height = 650;
        menuFrame = new JFrame("随课-SuiKe");
        menuFrame.setSize(width, height);
        menuFrame.setResizable(false);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TopPanel = new JPanel();
        DoPanel = new JPanel();
        UserName = new JLabel();
        cardLayout = new CardLayout();
        menuFrame.setLayout(null);
        TopPanel.setLayout(null);
        DoPanel.setLayout(cardLayout);
        Color titlecolor=new Color(240,240,240);
        TopPanel.setBackground(titlecolor);
        Color workbgcolor=new Color(220,220,220);
        DoPanel.setBackground(workbgcolor);
        //
        UserControl = new JButton("用户管理");
        ImageIcon userC = new ImageIcon("src\\main\\java\\Image\\USER.png");
        userC.setImage(userC.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        UserControl.setIcon(userC);
        UserControl.setFont(new java.awt.Font("Dialog", 1, 20));
        UserControl.setBounds(10, 10, 180, 80);
        UserControl.addActionListener(new MenuListener());
        UserControl.setActionCommand("user");
        TopPanel.add(UserControl);
        //
        StudentControl = new JButton("学生管理");
        ImageIcon stuC = new ImageIcon("src\\main\\java\\Image\\STU.png");
        stuC.setImage(stuC.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        StudentControl.setIcon(stuC);
        StudentControl.setFont(new java.awt.Font("Dialog", 1, 20));
        StudentControl.setBounds(200, 10, 180, 80);
        StudentControl.addActionListener(new MenuListener());
        TopPanel.add(StudentControl);
        StudentControl.setActionCommand("student");
        //
        ClassExtract = new JButton("课堂抽取");
        ImageIcon workC = new ImageIcon("src\\main\\java\\Image\\WORK.png");
        workC.setImage(workC.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        ClassExtract.setIcon(workC);
        ClassExtract.setFont(new java.awt.Font("Dialog", 1, 20));
        ClassExtract.setBounds(390, 10, 180, 80);
        ClassExtract.addActionListener(new MenuListener());
        ClassExtract.setActionCommand("work");
        TopPanel.add(ClassExtract);
        //
        EXIT = new JButton("退出登录");
        ImageIcon exit = new ImageIcon("src\\main\\java\\Image\\EXIT.png");
        exit.setImage(exit.getImage().getScaledInstance(60, 50, Image.SCALE_DEFAULT));
        EXIT.setIcon(exit);
        EXIT.setFont(new java.awt.Font("Dialog", 1, 20));
        EXIT.setBounds(580, 10, 180, 80);
        EXIT.addActionListener(new MenuListener());
        EXIT.setActionCommand("EXIT");
        TopPanel.add(EXIT);
        //
        String User = "用户:" + name;
        UserName = new JLabel(User);
        UserName.setFont(new java.awt.Font("Dialog", 1, 30));
        UserName.setBounds(770, 40, 230, 25);
        TopPanel.add(UserName);
        TopPanel.setBounds(0, 0, 1000, 100);
        AddCard(DoPanel);
        DoPanel.setBounds(30, 140, 920, 470);
        DoPanel.revalidate();
        menuFrame.setIconImage(new ImageIcon("src\\main\\java\\Image\\SK3.png").getImage());
        menuFrame.add(DoPanel);
        menuFrame.add(TopPanel);
        menuFrame.setVisible(true);
        menuFrame.setBounds((screenWidth - width) / 2, (screenHeight - height) / 2, width, height);
    }

    private void AddCard(JPanel doPanel) {
        usercard = UserCard.getInstance();
        doPanel.add(usercard, "usercard");
        // studentcard = new StudentCard();
        // doPanel.add(studentcard, "studentcard");
        // workcard = new WorkCard();
        // doPanel.add(workcard, "workcard");
    }

    public static UserCard Getusercard() {
        return usercard;
    }

    public static StudentCard Getstudentcard() {
        return studentcard;
    }

    public static WorkCard Getworkcard() {
        return workcard;
    }

    private class MenuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("user")) {
                UserCard_UserMessageControl.setUserCard_UserMessageControl();
                cardLayout.show(DoPanel, "usercard");
            } else if (command.equals("student")) {
                if (Cookie.getChoiceClass() == null) {
                    String Text = "请选择课程";
                    JOptionPane.showMessageDialog(null, Text, "警告", JOptionPane.WARNING_MESSAGE);
                } else {
                    studentcard= StudentCard.getInstance();
                    DoPanel.add(studentcard, "studentcard");
                    cardLayout.show(DoPanel, "studentcard");
                }
            } else if (command.equals("work")) {
                if (Cookie.getChoiceClass() == null) {
                    String Text = "请选择课程";
                    JOptionPane.showMessageDialog(null, Text, "警告", JOptionPane.WARNING_MESSAGE);
                } else {
                    try{
                        if(DAOFactory.getIstuDoDAO().RandomSampling(Cookie.getUserID(),Cookie.getChoiceClass(),0).equals(null)) {
                        }else{
                            workcard=WorkCard.getInstance();
                            DoPanel.add(workcard, "workcard");
                            cardLayout.show(DoPanel, "workcard");
                        }
                    }catch(Exception ex) {
                        String Text = "请添加学生";
                        JOptionPane.showMessageDialog(null, Text, "警告", JOptionPane.WARNING_MESSAGE);
                      //  ex.printStackTrace();
                    }
                }
            } else if (command.equals("EXIT")) {
                UserCard.closeInstance();
                StudentCard.closeInstance();
                WorkCard.closeInstance();
                new Login();
                Cookie.setUserID(null);
                Cookie.setChoiceClass(null);
                menuFrame.dispose();
            }
        }
    }
}
